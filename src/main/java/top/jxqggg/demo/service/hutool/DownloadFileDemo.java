package top.jxqggg.demo.service.hutool;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author : JiangQiang
 * @className : DownloadFileDemo
 * @description : TODO 类描述
 * @date :  2022/2/25
 **/
public class DownloadFileDemo {

    private static final String OA_EID = "15452095";

    private static final String OA_SECRET = "97BOXkGmUS6bzDS1XfuSc1K71uHISZUZ";

    public static void main(String[] arg) {
        String url = "https://yunzhijia.com/docrest/doc/user/downloadfile?bizkey=cloudflow&fileId=61a8515b74c73a0001d2ea4f";
        downloadFile(url,"D:/123");
    }

    /**
     * @param urlPath      下载路径
     * @param fileSavePath 下载存放目录,包含文件名
     * @throws Exception
     */
    public static void downloadFile(String urlPath, String fileSavePath) {

        File file = null;
        BufferedInputStream bin = null;
        OutputStream out = null;
        try {
            // 统一资源
            URL url = new URL(urlPath);
            // 连接类的父类，抽象类
            URLConnection urlConnection = url.openConnection();
            // http的连接类
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            // 设定请求的方法，默认是GET
            httpURLConnection.setRequestMethod("GET");
            // 设置字符编码
            httpURLConnection.setRequestProperty("Charset", "UTF-8");

            httpURLConnection.setRequestProperty("x-accessToken","fpcQFIt82b20lNHkP1Gq0N4J597tneeJ");
            // 打开到此 URL 引用的资源的通信链接（如果尚未建立这样的连接）。
            httpURLConnection.connect();
            /*String a = httpURLConnection.getHeaderField("Content-Disposition");
            System.out.println(a);*/
            Map<String, List<String>> map = httpURLConnection.getHeaderFields();
            Set<String> keySet = map.keySet();
            for (String key : keySet) {
                String value = map.get(key).toString();
                System.out.println(key + "==" + value);
            }

            // 文件大小
            int fileLength = httpURLConnection.getContentLength();

            // 文件名
            String filePathUrl = httpURLConnection.getURL().getFile();
            String fileFullName = filePathUrl.substring(filePathUrl.lastIndexOf(File.separatorChar) + 1);
            fileFullName = fileFullName.substring(fileFullName.lastIndexOf("/") + 1);
            System.out.println("fileFullName:" + fileFullName);
            System.out.println("开始下载文件：" + fileFullName);
            System.out.println("file length---->" + fileLength);
            url.openConnection();

            bin = new BufferedInputStream(httpURLConnection.getInputStream());

            String path = fileSavePath;
            file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            out = new FileOutputStream(file);
            int size = 0;
            int len = 0;
            byte[] buf = new byte[1024];
            while ((size = bin.read(buf)) != -1) {
                len += size;
                out.write(buf, 0, size);
               /* // 打印下载百分比
                if ((len * 100 / fileLength)>50&&(len * 100 / fileLength)<55) {
                    logger.info("下载了-------> " + len * 100 / fileLength + "%");
                }else    if ((len * 100 / fileLength)>=98) {
                    logger.info("下载了-------> " + len * 100 / fileLength + "%");
                }*/
                //logger.info("下载了-------> " + len * 100 / fileLength + "%");
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (bin != null) {
                try {
                    bin.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

}
