package top.jxqggg.demo.api.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * JDemo请求参数
 *
 * @author JiangQiang
 * @date 2022/8/22
 */
@Data
public class J2DemoRequest {
    /**
     * 第一个计算数字
     *
     * @mock 1
     */
    @NotNull(message = "第一个数字不能为空")
    private BigDecimal firstNum;
    /**
     * 第二个计算数字
     *
     * @mock 2
     */
    @NotNull(message = "第二个数字不能为空")
    private BigDecimal secondNum;
    /**
     * 测试非必填空数字
     */
    private BigDecimal nullNum;

}
