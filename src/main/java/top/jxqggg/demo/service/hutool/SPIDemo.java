package top.jxqggg.demo.service.hutool;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ServiceLoaderUtil;
import top.jxqggg.demo.service.spi.Search;

import java.util.List;
import java.util.ServiceLoader;

/**
 * @author : JiangQiang
 * @className : SPIDemo
 * @description : TODO 类描述
 * @date :  2022/3/9
 **/
public class SPIDemo {

    public static void main(String[] args) {
//        ServiceLoader<Search> load = ServiceLoader.load(Search.class);
        ServiceLoader<Search> load = ServiceLoaderUtil.load(Search.class);
        for (Search search : load) {
            List<String> docList = search.searchDoc("doc");
            if (CollUtil.isNotEmpty(docList)) {
                for (String doc : docList) {
                    System.out.println(doc);
                }
            }
        }
    }
}
