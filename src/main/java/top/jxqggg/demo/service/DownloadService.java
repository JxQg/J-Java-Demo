package top.jxqggg.demo.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.Method;

import java.io.InputStream;
import java.util.logging.Logger;

/**
 * TODO
 *
 * @author JiangQiang 2022/10/28
 */
public class DownloadService {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(DownloadService.class.getName());
        String downloadLink = "https://csi-web-dev.oss-cn-shanghai-finance-1-pub.aliyuncs.com/static/html/csindex/public/uploads/file/autofile/indicator/000969indicator.xls";
        DownloadService downloadService = new DownloadService();
        downloadService.downloadFileByDownloadLink(downloadLink);
        logger.info("end");
    }

    public void downloadFileByDownloadLink(String downloadLink) {
        HttpRequest httpRequest = HttpRequest.of(UrlBuilder.of(downloadLink).build())
                .method(Method.GET)
                .charset(CharsetUtil.CHARSET_UTF_8)
                .header(Header.CACHE_CONTROL.getValue(), "no-cache");
        try (HttpResponse response = httpRequest.execute()) {
            InputStream inputStream = response.bodyStream();
            FileUtil.writeFromStream(inputStream, "D://000969indicator.xls");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
