package top.jxqggg.demo.api.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * JDemo 数据传输层
 *
 * @author JiangQiang
 * @date 2022/8/22
 */
@Data
public class J2DemoDTO {

    /**
     * 第一个计算数字
     */
    private BigDecimal firstNum;
    /**
     * 第二个计算数字
     */
    private BigDecimal secondNum;
}
