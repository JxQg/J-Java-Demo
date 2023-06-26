package top.jxqggg.demo.service.hutool;

import org.springframework.http.HttpStatus;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * @author tsign
 * @date 2019/9/23
 */
public class AllExample {

    //private static final String host = "192.168.2.74";
    //private static final int port = 8088;
    private static final String host = "8.136.184.62";
    private static final int port = 8088;
    private static final  String fileName = "test.doc";
    private static final  String pdfFileName = "test.pdf";
    private static final  String srcFilePath = "C:\\Users\\Shangyuan-IT-001\\Desktop\\人力资源资料20220225\\离职证明.docx";
    private static final  String srcFilePath1 = "C:\\Users\\Shangyuan-IT-001\\Desktop\\人力资源资料20220225\\保密竞业协议.doc";
    private static final  String dstFilePath = "C:\\Users\\Shangyuan-IT-001\\Desktop\\人力资源资料20220225\\限期到岗通知（擅自离岗).pdf";
    private static final int timeOut=20000;

    public static void main(String[] args) {
        /**--------------------只支持word转pdf----------------------------------**/
        //word2PdfByPutReturnFileKey();
        //word2PdfByPutReturnBytes();
        /**-----------------------只支持word转pdf-----------------------------**/
        //pdf2PngByIndex();
        //pdf2JpegByIndex();
//        pdf2pngReturnZip();

        /**--------------------支持所有的office格式转pdf----------------------------------**/
        //文档转pdf 支持传文件列表 合并成1个pdf文档，也支持就传1个文档转换成pdf
        file2PdfReturnStringByPost();
//        file2PdfReturnFileByPost();
        //file2PdfReturnFileUseWpsByPost();
        //file2PdfReturnStringUseWpsByPost();
        /**-----------------------支持所有的office格式转pdf-----------------------------**/
    }




    /**
     * 文档转PDF返回文件Key(PUT)
     */
    private static void word2PdfByPutReturnFileKey()
    {
        String convertUrl = "http://" + host + ":" + port + "/word2pdf/" + fileName;
        /**---------如果文件有密码，没有的话headers为null就行------------**/
        Map<String,String> headers=new HashMap<>();
        headers.put("filePwd","1234");
        /**---------end------------**/
        System.out.println("返回结果："+putFileReturnString(srcFilePath,headers,convertUrl));
    }

    /**
     * 文档转PDF返回文件流(PUT)
     */
    private static void word2PdfByPutReturnBytes()
    {
        String convertUrl = "http://" + host + ":" + port + "/simple/word2pdf/" + fileName;
        /**---------如果文件有密码，没有的话headers为null就行------------**/
        Map<String,String> headers=new HashMap<>();
        headers.put("filePwd","1234");
        /**---------end------------**/
        putFileReturnFile(srcFilePath,dstFilePath,headers,convertUrl);

    }

    /**
     * 转换指定pdf页为png,pdf页码从0开始
     */
    private static void pdf2PngByIndex()
    {
        int index=0;
        String convertUrl = "http://" + host + ":" + port + "/simple/pdf2png/" + pdfFileName+"/"+index;
        String pdfSrcFilePath="D:\\temp\\pdf\\test.pdf";
        String pngDesFilePath="D:\\temp\\png\\"+index+".png";
       putFileReturnFile(pdfSrcFilePath,pngDesFilePath,null,convertUrl);
    }

    /**
     * 转换指定pdf页为jpeg,pdf页码从0开始
     */
    private static void pdf2JpegByIndex()
    {
        int index=0;
        String convertUrl = "http://" + host + ":" + port + "/simple/pdf2jpeg/" + pdfFileName+"/"+index;
        String pdfSrcFilePath="D:\\temp\\pdf\\test.pdf";
        String pngDesFilePath="D:\\temp\\png\\"+index+".jpeg";
        putFileReturnFile(pdfSrcFilePath,pngDesFilePath,null,convertUrl);
    }

    /**
     * 转换pdf未png，并返回所有图片打包好的zip
     */
    private static void pdf2pngReturnZip()
    {
        String convertUrl = "http://" + host + ":" + port + "/simple/pdf2pngzip/" + pdfFileName;
        String pdfSrcFilePath="D:\\12.pdf";
        String pngDesFilePath="D:\\temp\\png\\test.zip";
        putFileReturnFile(pdfSrcFilePath,pngDesFilePath,null,convertUrl);
    }

    /**
     * 文件列表转pdf并合并,返回fileKey,文挡转换使用office组件
     */
   private static  void file2PdfReturnStringByPost()
   {
       String convertUrl = "http://" + host + ":" + port + "/files2pdf";
        List<String> fileList=new ArrayList<>();
       fileList.add(srcFilePath);
       System.out.println("返回结果："+postFileReturnString(fileList,null,convertUrl));
   }

    /**
     * 文件列表转pdf并合并,返回文件流,文挡转换使用office组件
     */
    private static  void file2PdfReturnFileByPost()
    {
        String convertUrl = "http://" + host + ":" + port + "/simple/files2pdf";
        List<String> fileList=new ArrayList<>();
        fileList.add("D:\\temp\\doc\\1.xlsx");
        //fileList.add("D:\\temp\\doc\\test1.docx");
        postFileReturnFile(fileList,null,dstFilePath,convertUrl);
    }

    /**
     * 文件列表转pdf并合并,返回fileKey,文挡转换使用wps组件，支持docx/doc/wps/xls/et/xlsx/jpg/gif/jpeg/bmp/png/tif/tiff/ppt/dps/pptx/htm/html/pdf
     */
    private static  void file2PdfReturnStringUseWpsByPost()
    {
        String convertUrl = "http://" + host + ":" + port + "/simple/wps/files2pdf";
        List<String> fileList=new ArrayList<>();
        fileList.add("D:\\temp\\doc\\test.docx");
        fileList.add("D:\\temp\\doc\\test1.docx");
        System.out.println("返回结果："+postFileReturnString(fileList,null,convertUrl));
    }

    /**
     * 文件列表转pdf并合并,返回文件流,文挡转换使用wps组件，支持docx/doc/wps/xls/et/xlsx/jpg/gif/jpeg/bmp/png/tif/tiff/ppt/dps/pptx/htm/html/pdf
     */
    private static  void file2PdfReturnFileUseWpsByPost()
    {
        String convertUrl = "http://" + host + ":" + port + "/wps/files2pdf";
        List<String> fileList=new ArrayList<>();
       // fileList.add("D:\\temp\\doc\\1.xlsx");
        fileList.add("D:\\temp\\doc\\test1.docx");
        Map<String,String> paramMap=new HashMap();
        paramMap.put("fileScaleType","FitToPagesWide");//缩放类型：FitToPagesWide缩放至一页 Auto 100%的A4切开分页 此项参数只对excel转pdf起作用
        paramMap.put("orientationType","Widthwise"); //打印方向：Widthwise横向 Longitudinal 纵向 此项参数只对excel转pdf起作用
        postFileReturnFile(fileList,paramMap,dstFilePath,convertUrl);
    }


    private static String putFileReturnString(String srcFilePath,Map<String,String> headers, String convertUrl) {
        String result=null;
        ByteArrayOutputStream byteArrayOutputStream=null;
        try {
            byteArrayOutputStream=new ByteArrayOutputStream();
            putFileCore(srcFilePath,byteArrayOutputStream,headers,convertUrl);
            result=byteArrayOutputStream.toString();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        } finally {
            try {
                if (null != byteArrayOutputStream) {
                    byteArrayOutputStream.close();
                }
            } catch (IOException e) {
                System.out.println("close stream failed." + e.getMessage());
            }
        }
    }


    private static void putFileReturnFile(String srcFilePath, String dstFilePath,Map<String,String> headers, String convertUrl) {

        File dstFile = new File(dstFilePath);
        FileOutputStream fileOutputStream =null;
        try {
            fileOutputStream = new FileOutputStream(dstFile);
            putFileCore(srcFilePath,fileOutputStream,headers,convertUrl);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {

                if (null != fileOutputStream) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                System.out.println("close stream failed." + e.getMessage());
            }
        }
    }
/*
文件上传PUT方法
 */
    private static void putFileCore(String srcFilePath,OutputStream outputStream,Map<String,String> headers,String convertUrl)throws Exception
    {
        HttpURLConnection connection;
        OutputStream out = null;
        InputStream in = null;
        BufferedOutputStream bos = null;
        try {
            URL url = new URL(convertUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/octet-stream");
            connection.setRequestProperty("Accept-Charset", "utf-8");
            connection.setReadTimeout(timeOut);
            connection.setConnectTimeout(timeOut);
            // text
            if (headers != null) {
                Iterator<Map.Entry<String, String>> iter = headers.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry<String, String> entry = iter.next();
                    String inputName = entry.getKey();
                    String inputValue = entry.getValue();
                    connection.setRequestProperty(inputName,inputValue);
                }
            }


            connection.connect();
            out = connection.getOutputStream();
            File file = new File(srcFilePath);
            FileInputStream fileInputStream = new FileInputStream(file);
            int bytes = 0;
            byte[] buffer = new byte[1024];
            while ((bytes = fileInputStream.read(buffer)) != -1) {
                out.write(buffer, 0, bytes);
            }
            out.flush();
            int status = connection.getResponseCode();
            if (HttpStatus.OK.value() == status) {
                in = connection.getInputStream();
                bos = new BufferedOutputStream(outputStream);
                int b = 0;
                byte[] byArr = new byte[1024];
                while ((b = in.read(byArr)) != -1) {
                    bos.write(byArr, 0, b);
                }
                bos.flush();
            } else {
                System.out.println("convert failed.");
                throw new Exception("convert failed.");
                // 出错处理， 打印相关错误信息
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (null != out) {
                    out.close();
                }

                if (null != in) {
                    in.close();
                }

                if (null != bos) {
                    bos.close();
                }
            } catch (IOException e) {
                System.out.println("close stream failed." + e.getMessage());
                throw e;
            }
        }
    }


    public static String postFileReturnString(List<String> fileList,Map<String,String> textMap,String convertUrl) {
        String result=null;
        ByteArrayOutputStream byteArrayOutputStream=null;
        try {
            byteArrayOutputStream=new ByteArrayOutputStream();
            uploadFileByPostCore(fileList,textMap,byteArrayOutputStream,convertUrl);
            result=byteArrayOutputStream.toString();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        } finally {
            try {
                if (null != byteArrayOutputStream) {
                    byteArrayOutputStream.close();
                }
            } catch (IOException e) {
                System.out.println("close stream failed." + e.getMessage());
            }
        }
    }


    public static void postFileReturnFile(List<String> fileList,Map<String,String> textMap,String desFilePath,String convertUrl) {

        File dstFile = new File(desFilePath);
        FileOutputStream fileOutputStream =null;
        try {
            fileOutputStream = new FileOutputStream(dstFile);
            uploadFileByPostCore(fileList,textMap,fileOutputStream,convertUrl);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {

                if (null != fileOutputStream) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                System.out.println("close stream failed." + e.getMessage());
            }
        }
    }




    /**
     * POST方式上传
     * @param fileList
     * @param textMap
     * @param outputStream
     * @param convertUrl
     * @return
     */
    public static void uploadFileByPostCore( List<String> fileList,Map<String, String> textMap ,OutputStream outputStream,String convertUrl) throws Exception{
        HttpURLConnection conn;
        String BOUNDARY = "---------------------------123821742118716"; //boundary就是request头和上传文件内容的分隔符
        InputStream in = null;
        BufferedOutputStream bos = null;
        OutputStream out=null;
        try {
            URL url = new URL(convertUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(timeOut);
            conn.setReadTimeout(timeOut);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
            out = new DataOutputStream(conn.getOutputStream());
            // text
            if (textMap != null) {
                StringBuffer strBuf = new StringBuffer();
                Iterator<Map.Entry<String, String>> iter = textMap.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry<String, String> entry = iter.next();
                    String inputName = entry.getKey();
                    String inputValue = entry.getValue();
                    if (inputValue == null) {
                        continue;
                    }
                    strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
                    strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"\r\n\r\n");
                    strBuf.append(inputValue);
                }
                out.write(strBuf.toString().getBytes());
            }

            for(String filePath:fileList)
            {
                    File file = new File(filePath);
                    String filename = file.getName();
                    StringBuffer strBuf = new StringBuffer();
                    strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
                    strBuf.append("Content-Disposition: form-data; name=\"" + "file" + "\"; filename=\"").append(filename).append("\"\r\n");
                    strBuf.append("Content-Type:" + "application/msword" + "\r\n\r\n");
                    out.write(strBuf.toString().getBytes());
                    DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
                    int bytes = 0;
                    byte[] bufferOut = new byte[1024];
                    while ((bytes = dataInputStream.read(bufferOut)) != -1) {
                        out.write(bufferOut, 0, bytes);
                    }
                dataInputStream.close();
            }
            byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
            out.write(endData);
            out.flush();
            int status = conn.getResponseCode();
            if (HttpStatus.OK.value() == status) {
                in = conn.getInputStream();
                InputStreamReader readerInput = new InputStreamReader(in);
                BufferedReader reader = new BufferedReader(readerInput);
                // 定义BufferedReader输入流来读取URL的响应
                String strLine = "";
                String strResponse= "";
                while ((strLine = reader.readLine()) != null) {
                    strResponse += strLine + "\n";
                }
                System.out.println(strResponse);
//                bos = new BufferedOutputStream(outputStream);
//                int b = 0;
//                byte[] byArr = new byte[1024];
//                while ((b = in.read(byArr)) != -1) {
//                    bos.write(byArr, 0, b);
//                }
//                bos.flush();
            } else {
                // 出错处理， 打印相关错误信息
                System.out.println("convert failed.");
                throw new Exception("convert failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (null != out) {
                    out.close();
                }

                if (null != in) {
                    in.close();
                }

                if (null != bos) {
                    bos.close();
                }
            } catch (IOException e) {
                System.out.println("close stream failed." + e.getMessage());
                throw e;
            }
        }
    }

}
