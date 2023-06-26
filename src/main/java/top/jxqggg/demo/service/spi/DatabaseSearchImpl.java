package top.jxqggg.demo.service.spi;

import cn.hutool.core.util.StrUtil;

import java.util.Collections;
import java.util.List;

/**
 * @author : JiangQiang
 * @className : DatabaseSearchImpl
 * @description : TODO 类描述
 * @date :  2022/3/9
 **/
public class DatabaseSearchImpl implements Search {
    @Override
    public List<String> searchDoc(String keyword) {
        if (StrUtil.isEmpty(keyword)) {
            return null;
        }
        return Collections.singletonList(keyword + "DatabaseValue");
    }
}
