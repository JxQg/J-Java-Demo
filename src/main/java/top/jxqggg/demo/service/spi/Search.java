package top.jxqggg.demo.service.spi;

import java.util.List;

/**
 * @author : JiangQiang
 * @className : Search
 * @description : TODO 类描述
 * @date :  2022/3/9
 **/
public interface Search {
    /**
     * 查询文档
     *
     * @param keyword 关键字
     * @return 文档
     */
    public List<String> searchDoc(String keyword);

}
