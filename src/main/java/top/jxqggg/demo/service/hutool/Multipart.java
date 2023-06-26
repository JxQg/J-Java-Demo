package top.jxqggg.demo.service.hutool;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * 请求多部份组件
 *
 * @author : JiangQiang
 * @date :  2022/3/6
 **/
public class Multipart {
    private static final String LINE_FEED = "\r\n";
    private final String boundary;
    private final HttpURLConnection httpConn;
    private final String charset;
    private final OutputStream outputStream;
    private final PrintWriter writer;

    public Multipart(String requestURL, String charset, String accessToken) throws IOException {
        this.charset = charset;
        this.boundary = "===" + System.currentTimeMillis() + "===";
        URL url = new URL(requestURL);
        this.httpConn = (HttpURLConnection) url.openConnection();
        this.httpConn.setUseCaches(false);
        this.httpConn.setDoOutput(true);
        this.httpConn.setDoInput(true);
        this.httpConn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + this.boundary);
        this.httpConn.setRequestProperty("x-accessToken", accessToken);
        this.outputStream = this.httpConn.getOutputStream();
        this.writer = new PrintWriter(new OutputStreamWriter(this.outputStream, charset), true);
    }

    public void addFormField(String name, String value) {
        this.writer.append("--").append(this.boundary).append("\r\n");
        this.writer.append("Content-Disposition: form-data; name=\"").append(name).append("\"").append("\r\n");
        this.writer.append("Content-Type: text/plain; charset=").append(this.charset).append("\r\n");
        this.writer.append("\r\n");
        this.writer.append(value).append("\r\n");
        this.writer.flush();
    }

    public void addFilePart(String fieldName, File uploadFile) throws IOException {
        String fileName = uploadFile.getName();
        this.writer.append("--").append(this.boundary).append("\r\n");
        this.writer.append("Content-Disposition: form-data; name=\"").append(fieldName).append("\"; filename=\"").append(fileName).append("\"").append("\r\n");
        this.writer.append("Content-Type: ").append(URLConnection.guessContentTypeFromName(fileName)).append("\r\n");
        this.writer.append("Content-Transfer-Encoding: binary").append("\r\n");
        this.writer.append("\r\n");
        this.writer.flush();
        FileInputStream inputStream = new FileInputStream(uploadFile);
        byte[] buffer = new byte[4096];

        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            this.outputStream.write(buffer, 0, bytesRead);
        }

        this.outputStream.flush();
        inputStream.close();
        this.writer.append("\r\n");
        this.writer.flush();
    }

    public void addHeaderField(String name, String value) {
        this.writer.append(name).append(": ").append(value).append("\r\n");
        this.writer.flush();
    }

    public String finish() throws IOException {
        this.writer.append("\r\n").flush();
        this.writer.append("--").append(this.boundary).append("--").append("\r\n");
        this.writer.close();
//        return IOUtil.read(this.httpConn);
        return "";
    }
}