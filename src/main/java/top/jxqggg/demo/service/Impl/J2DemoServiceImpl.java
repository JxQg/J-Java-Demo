package top.jxqggg.demo.service.Impl;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.stereotype.Service;
import top.jxqggg.demo.api.dto.J2DemoDTO;
import top.jxqggg.demo.service.J2DemoService;

import java.math.BigDecimal;

/**
 * TODO
 *
 * @author JiangQiang
 * @date 2022/8/22
 */
@Service
public class J2DemoServiceImpl implements J2DemoService {
    @Override
    public BigDecimal mathAdd(J2DemoDTO demoDTO) {
        if (ObjectUtil.isEmpty(demoDTO.getFirstNum())) {
            throw new RuntimeException("");
        }
        return null;
    }
}
