package top.jxqggg.demo.service.smb;

import jcifs.CIFSContext;
import jcifs.context.SingletonContext;
import jcifs.smb.NtlmPasswordAuthenticator;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * SMB 请求管理客户端
 *
 * @author JiangQiang 2023/7/5 12:04
 */
@Slf4j
public class SambaClient {

    private final SambaConfig sambaConfig;

    public SambaClient(SambaConfig sambaConfig) {
        this.sambaConfig = sambaConfig;
    }


    private String getSmbUrl(String remoteFilePath) {
        return "smb://" + sambaConfig.getHost() + "/" + remoteFilePath;
    }

    private String getSmbUrl(String remoteFilePath, String fileName) {
        return getSmbUrl(remoteFilePath) + "/" + fileName;
    }

    private CIFSContext getAuthContext() {
        return SingletonContext.getInstance().withCredentials(
                new NtlmPasswordAuthenticator(sambaConfig.getDomain(), sambaConfig.getUserName(), sambaConfig.getPassword()
                )
        );
    }

    /**
     * 上传文件到远程地址
     *
     * @param localFilePath  本地地址
     * @param remoteFilePath 远程地址
     * @param fileName       文件名称
     */
    public void uploadFile(String localFilePath, String remoteFilePath, String fileName) {
        try (InputStream inputStream = Files.newInputStream(Paths.get(localFilePath))) {
            this.uploadFile(inputStream, remoteFilePath, fileName);
        } catch (IOException e) {
            log.error("读取文件地址出现异常！", e);
            // throw new IllegalStateException
        }
    }

    /**
     * 上传文件流到远程地址
     * inputStream 需要在外面关闭
     *
     * @param inputStream    文件流
     * @param remoteFilePath 远程地址
     * @param fileName       文件名称
     */
    public void uploadFile(InputStream inputStream, String remoteFilePath, String fileName) {
        try (SmbFile folder = new SmbFile(getSmbUrl(remoteFilePath), getAuthContext())) {
            if (!folder.exists()) {
                folder.mkdirs();
            }
            try (SmbFile file = new SmbFile(getSmbUrl(remoteFilePath, fileName), getAuthContext())) {
                if (file.exists() && file.isFile()) {
                    return;
                }
                try (SmbFileOutputStream outputStream = new SmbFileOutputStream(file)) {
                    // 从本地文件复制数据到 SMB 文件
                    IOUtils.copy(inputStream, outputStream);
                } catch (IOException e) {
                    log.error("上传文件失败!", e);
                    // throw new IllegalStateException
                }
            }
        } catch (MalformedURLException | SmbException e) {
            log.error(e.getMessage(), e);
            // throw new IllegalStateException
        }
    }

}
