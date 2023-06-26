package top.jxqggg.demo.service.doc;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 客户合同信息
 *
 * @author JiangQiang
 * @date 2022/6/28
 */
@Data
public class CustomerContractDTO {

    /**
     * 序号
     */
    private String serialNo;
    /**
     * 区域
     */
    private String area;
    /**
     * 五星会员号
     */
    private String memberNo4FiveStar;
    /**
     * 客户名称
     */
    private String customerName;
    /**
     * 客户简称
     */
    private String customerAbbreviation;
    /**
     * 联系人姓名
     */
    private String linkmanName;
    /**
     * 地址
     */
    private String address;
    /**
     * 联系人手机号
     */
    private String linkmanMobile;
    /**
     * 所属经销商名称
     */
    private String distributorName;
    /**
     * 保证金
     */
    private BigDecimal securityDeposit;
    /**
     * 签约数量
     */
    private BigDecimal contractTotalAmount;
    /**
     * 一月数量
     */
    private BigDecimal janAmount;
    /**
     * 二月数量
     */
    private BigDecimal febAmount;
    /**
     * 三月数量
     */
    private BigDecimal marAmount;
    /**
     * 四月数量
     */
    private BigDecimal aprAmount;
    /**
     * 五月数量
     */
    private BigDecimal mayAmount;
    /**
     * 六月数量
     */
    private BigDecimal junAmount;
    /**
     * 七月数量
     */
    private BigDecimal julAmount;
    /**
     * 八月数量
     */
    private BigDecimal augAmount;
    /**
     * 九月数量
     */
    private BigDecimal septAmount;
    /**
     * 十月数量
     */
    private BigDecimal octAmount;
    /**
     * 十一月数量
     */
    private BigDecimal novAmount;
    /**
     * 十二月数量
     */
    private BigDecimal decAmount;
}
