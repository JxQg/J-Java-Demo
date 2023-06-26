package top.jxqggg.demo.service.html;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import top.jxqggg.demo.service.DownloadService;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

/**
 * HTML服务接口
 *
 * @author JiangQiang 2022/10/28
 */
public class HtmlService {

    private final static Logger logger = Logger.getLogger(HtmlService.class.getName());

    private final Map<String, String> finalCookies = getCookies();

    public static void main(String[] args) {
        Logger logger = Logger.getGlobal();
        HtmlService htmlService = new HtmlService();
        String url = "https://www.csindex.com.cn/index.html#/indices/family/detail?indexCode=000969";
        String url1 = "https://element.eleme.cn/#/zh-CN";
        Document htmlDocument = htmlService.getHtmlDocumentByUrl(url1);
        List<String> downloadLinksByHtmlDocument = htmlService.getDownloadLinksByPageDocument(htmlDocument);
        if (CollUtil.isNotEmpty(downloadLinksByHtmlDocument)) {
            logger.info("link size:" + downloadLinksByHtmlDocument.size() + "." + downloadLinksByHtmlDocument.get(0));
        }
        logger.info("end");
    }

    private Map<String, String> getCookies() {
        String cookies = "ssxmod_itna=Yq0x2Q0=DQeWqOxl4iwr8D9jxgDI2h3khdwb+urRxGNwTDZDiqAPGhDCb+I34=owwPo7onqKtqFBf2bqoHRgod8eAbDweDHxY=DUgODIQbDeetD5xGoDPxDeDADYoXDAqiOD7qDdUEwNNBDi3Db2=Di4D+Cq=DmqG0DDtOB4G2D7tyA7hPCdrU8lU3Luwe=0DyD0UdxBd576uYkZcPYmPnDB=CxBjlUuNXfeDH+MNZYW+xAGPpySdDYqHDUdTCDG3yQ7xhHDG1CLdXQgkD0ueoIAPql0D4B7TNtVWt/7h0CiPNbG4NByx=BiFI0PFDDG+D7geziDD===; ssxmod_itna2=Yq0x2Q0=DQeWqOxl4iwr8D9jxgDI2h3khdwb+uxA=cxXrD/lQFDF22wf9=9ivAFIx0Dn+w8eZem4Q4qiSB7xOCit00tq0U+nGbqo9FpAI8Gyzmj=qe9+pQF=zGhcKU+No/wObLI1WW82PFsLprrS7Y+cj…AGSKFz236tFKZO6bQhX=G+f1=RehoOib78hHzzRHe41o=ONLwK9Ln8uuQgTHlMiZxQbdtXfoxr8Dg1l8Ovhd38DrqGRA1DgQ4EdPWKsqTTQzYwdSoh68i+4s+wpxT+KGq07=V2zlk1w4wUL1yMQmxHGdsFQ=W7hSMk1uD8h4lkxNhDI78+Q86oI/bkngPIRhRqO8fhTlodn8ildzT+5SsAe6ngIjht5gd54Y7jtLMoL2xRmQdurNdttRUvTPD7QpSxtjDS7w5YFp1BeN68n7T1eN6B8mGzoo5YR4QepIg=q4QgoDjKDeT2dDxi1cY9x4D==; _uab_collina=166685903347257599468797; aliyungf_tc=c3fcb6b202b5ad141375e460e81cab47e310338a1ea359f1bb21342462a6bf5c; acw_tc=76b20f8416669348601576294e60b6ec09e9c366db36a0d920bc65167cd57b";
        List<String> cookiesList = StrUtil.split(cookies, ";");
        Map<String, String> cookiesMap = new HashMap<>(10);
        for (String cookie : cookiesList) {
            int index = StrUtil.indexOf(cookie, '=');
            String key = cookie.substring(0, index);
            String value = cookie.substring(index + 1);
            cookiesMap.put(key, value);
        }
        return cookiesMap;
    }

    /**
     * 根据URL获取HTML文档
     *
     * @param url 链接地址
     */
    public Document getHtmlDocumentByUrl(String url) {
        try (WebClient webClient = new WebClient(BrowserVersion.CHROME)) {
            // 不启用ActiveX
            webClient.getOptions().setActiveXNative(false);
            // 是否启用CSS，因为不需要展现页面，所以不需要启用
            webClient.getOptions().setCssEnabled(true);
            // 设置为true，客户机将接受与任何主机的连接，而不管它们是否有有效证书
            webClient.getOptions().setUseInsecureSSL(true);
            // 很重要，启用JS
            webClient.getOptions().setJavaScriptEnabled(true);
            // 不下载图片
            webClient.getOptions().setDownloadImages(false);
            // 当JS执行出错的时候是否抛出异常，这里选择不需要
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            // 当HTTP的状态非200时是否抛出异常，这里选择不需要
            webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
            // 等待5s
            webClient.getOptions().setTimeout(3 * 1000);
            webClient.getOptions().setConnectionTimeToLive(3 * 1000);
            //允许重定向
            webClient.getOptions().setRedirectEnabled(true);
            webClient.getCookieManager().setCookiesEnabled(true);
            //很重要，设置支持AJAX
            webClient.setAjaxController(new NicelyResynchronizingAjaxController());
            logger.info("请求网页数据:" + url);
            HtmlPage page = webClient.getPage(url);
            // 异步JS执行需要耗时，所以这里线程要阻塞10秒，等待异步JS执行结束
            webClient.waitForBackgroundJavaScript(10 * 1000);
            logger.info("请求页面成功，转换为HTML");
            return Jsoup.parse(page.asXml());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取网页 相关资料的下载链接列表
     *
     * @param document 网页数据
     * @return 下载链接列表
     */
    public List<String> getDownloadLinksByPageDocument(Document document) {
        if (document == null) {
            logger.warning("获取HTML文件失败!");
            return Collections.emptyList();
        }
        logger.info("开始解析页面中的下载链接");
        Elements downloadDivElement = document.body().getElementsByClass("material-list-item");
        List<String> downloadLinks = new ArrayList<>(6);
        for (Element element : downloadDivElement) {
            Element downloadLinkElement = element.select("a[href]").first();
            if (downloadLinkElement == null) {
                continue;
            }
            String downloadLink = downloadLinkElement.attr("href");
            if (StrUtil.isNotBlank(downloadLink)) {
                downloadLinks.add(downloadLink);
            }
        }
        return downloadLinks;
    }
}
