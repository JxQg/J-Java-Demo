package top.jxqggg.demo.api.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * JDemo 展示层数据
 *
 * @author JiangQiang
 * @date 2022/8/22
 */
@Data
public class J2DemoVO {
    /**
     * 加法的和
     */
    private BigDecimal sum;
    /**
     * 减法差
     */
    private BigDecimal different;
    /**
     * 乘法积
     */
    private BigDecimal product;
    /**
     * 除法商
     */
    private BigDecimal quotient;

}
