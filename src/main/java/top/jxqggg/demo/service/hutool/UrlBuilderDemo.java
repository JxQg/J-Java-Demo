package top.jxqggg.demo.service.hutool;

import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.core.util.CharsetUtil;

/**
 * TODO
 *
 * @author JiangQiang 2022/9/9
 */
public class UrlBuilderDemo {
    private static final String SERVER_URI = "https://yunzhijia.com:443";

    private static final String OA_API_SCHEME = "https";
    private static final String OA_API_HOST = "yunzhijia.com";
    private static final int OA_API_PORT = 443;

    public static void main(String[] args) {
        String path = "/index";
        String url = UrlBuilder.of(SERVER_URI, CharsetUtil.CHARSET_UTF_8).addPath(path).build();
        UrlBuilder urlBuilder = UrlBuilder.create()
                .setCharset(CharsetUtil.CHARSET_UTF_8)
                .setScheme(OA_API_SCHEME)
                .setHost(OA_API_HOST)
                .setPort(OA_API_PORT)
                .addPath(path);
        System.out.println(url);
        System.out.println(urlBuilder);
    }
}
