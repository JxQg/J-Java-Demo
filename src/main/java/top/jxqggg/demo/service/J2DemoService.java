package top.jxqggg.demo.service;

import org.springframework.stereotype.Service;
import top.jxqggg.demo.api.dto.J2DemoDTO;

import java.math.BigDecimal;

/**
 * TODO
 *
 * @author JiangQiang
 * @date 2022/8/22
 */
public interface J2DemoService {
    /**
     * 数学加法
     *
     * @param demoDTO
     * @return
     */
    BigDecimal mathAdd(J2DemoDTO demoDTO);

}
