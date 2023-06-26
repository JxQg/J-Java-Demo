package top.jxqggg.demo.service.hutool;

import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.CacheControl;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : JiangQiang
 * @className : FileDemo
 * @description : TODO 类描述
 * @date :  2022/3/5
 **/
@Slf4j
public class FileDemo {

    public static void main(String[] args) {
        Map<String, String> query = new HashMap<>(2);
        query.put("bizkey", "cloudflow");
        query.put("fileId", "625d4373af910600014e373d");
        FileDemo demo = new FileDemo();
        HttpResponse httpResponse = demo.invokerFileGet(query);
        try {
            HttpResponse httpResponse1 = demo.invokerFilePost(httpResponse.bodyStream(), "测试.pdf");
            if (httpResponse1 != null) {
                System.out.println(httpResponse1);
            }
//            String s = demo.invokeUploadPDF(httpResponse.bodyStream());
//            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HttpResponse invokerFilePost(InputStream inputStream, String fileName) throws IOException {
        UrlBuilder urlBuilder = generateIntactUrlBuilder("/docrest/doc/file/uploadfile");
//        File tempFile = FileUtil.writeFromStream(inputStream, FileUtil.newFile(FileUtil.getTmpDirPath() + fileName));
        byte[] fileByte = IoUtil.readBytes(inputStream, true);
        HttpRequest httpRequest = HttpRequest.of(urlBuilder.build())
                .method(Method.POST)
                .charset(CharsetUtil.CHARSET_UTF_8)
                .keepAlive(true)
                .form("bizkey", "cloudflow")
                .form("file", fileByte, fileName)
                .header(Header.CONTENT_TYPE.getValue(), ContentType.MULTIPART.getValue())
                .header(Header.CACHE_CONTROL.getValue(), CacheControl.noCache().getHeaderValue())
                .header("x-accessToken", "9k94AIMdff7Dj8nKXajFRRkEbp3FIwop");
        HttpResponse response;
        try {
            response = httpRequest.execute();
        } catch (IORuntimeException e) {
            log.error("云之家上传文件失败，请求接口{}，上传文件为{}", urlBuilder.build(), fileName);
            throw e;
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
//            if (FileUtil.isNotEmpty(tempFile)) {
//                FileUtil.del(tempFile);
//            }
        }
        return response;
    }


    /**
     * 调用文件获取get接口
     *
     * @param query 查询参数
     * @return response
     */
    public HttpResponse invokerFileGet(Map<String, String> query) {
        UrlBuilder urlBuilder = generateIntactUrlBuilder("/docrest/doc/user/downloadfile");
        if (MapUtil.isNotEmpty(query)) {
            query.forEach(urlBuilder::addQuery);
        }
        HttpRequest httpRequest = new HttpRequest(urlBuilder.build())
                .method(Method.GET)
                .charset(CharsetUtil.CHARSET_UTF_8)
                .header("x-accessToken", "9k94AIMdff7Dj8nKXajFRRkEbp3FIwop")
                .header("Cache-Control", "no-cache");

        HttpResponse response;
        try {
            response = httpRequest.execute();
        } catch (IORuntimeException e) {
            log.error("云之家下载文件请求失败，请求接口{}，请求参数为{}", urlBuilder.build(), query);
            throw e;
        }
        return response;
    }

    /**
     * 生成完整的请求路径
     *
     * @param apiPath api路径
     * @return url构建器
     */
    protected UrlBuilder generateIntactUrlBuilder(String apiPath) {
        return UrlBuilder.create()
                .setCharset(CharsetUtil.CHARSET_UTF_8)
                .setScheme("https")
                .setHost("yunzhijia.com")
                .setPort(443)
                .addPath(apiPath);
    }

    public String invokeUploadPDF(InputStream fileInput) throws Exception {
        String url = "http://116.62.100.65:8086/V1/files/upload";
        String responseStr = this.uploadPDFFile(fileInput, url);
        return responseStr;
    }

    private String uploadPDFFile(InputStream inputStream, String uploadUrl) throws Exception {
        StringBuilder strResponse = new StringBuilder();
        OutputStream out = null;
        InputStreamReader readerInput = null;
        InputStream resin = null;
        // 换行符
        final String newLine = "\r\n";
        final String boundaryPrefix = "--";
        // 定义数据分隔线
        String boundary = "========7d4a6d158c9";
        try {
            // 服务器地址
            URL url = new URL(uploadUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 设置为POST情
            conn.setRequestMethod("POST");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求头参数
            conn.setRequestProperty("X-timevale-project-id", "1001001");
            conn.setRequestProperty("X-timevale-signature", "e297921433b26b1c1352cc83898616ea");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charsert", "UTF-8");
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

            out = conn.getOutputStream();
            StringBuilder sb = new StringBuilder();
            sb.append(boundaryPrefix).append(boundary).append(newLine);
            String fn = RandomUtil.randomString(16);
            // 文件参数,photo参数名可以随意修改
            sb.append("Content-Disposition: form-data;name=\"file\";filename=\"").append(fn).append("\"")
                    .append(newLine)
                    .append("Content-Type:application/octet-stream")
                    // 参数头设置完以后需要两个换行，然后才是参数内容
                    .append(newLine)
                    .append(newLine);

            // 将参数头的数据写入到输出流中
            out.write(sb.toString().getBytes());

            byte[] bufferOut = new byte[1024];
            int bytes;
            // 每次读1KB数据,并且将文件数据写入到输出流中
            while ((bytes = inputStream.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
            }
            // 最后添加换行
            out.write(newLine.getBytes());

            // 定义最后数据分隔线，即--加上BOUNDARY再加上--。
            byte[] endData = (newLine + boundaryPrefix + boundary + boundaryPrefix + newLine).getBytes();
            // 写上结尾标识
            out.write(endData);

            resin = conn.getInputStream();
            readerInput = new InputStreamReader(resin);
            BufferedReader reader = new BufferedReader(readerInput);
            // 定义BufferedReader输入流来读取URL的响应
            String strLine = "";
            while ((strLine = reader.readLine()) != null) {
                strResponse.append(strLine).append("\n");
            }
        } catch (Exception e) {
            log.error("E签宝上传文件调用异常：", e);
            throw e;
        } finally {

            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }

            if (null != readerInput) {
                try {
                    readerInput.close();
                } catch (IOException e) {
                }
            }

            if (null != resin) {
                try {
                    resin.close();
                } catch (IOException e) {
                }
            }

            if (null != out) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {

                }
            }
        }
        return strResponse.toString();
    }
}
