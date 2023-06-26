package top.jxqggg.demo.api.po;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import top.jxqggg.demo.util.BillQueryUtil;

/**
 * @author : JiangQiang
 * @className : TestPo
 * @description : TODO 类描述
 * @date :  2021/12/16
 **/
@Getter
@Setter
@ToString
public class TestPo {

    /**
     * 使用组织ID
     */
    private Integer fuseorgid;
    /**
     * 客户编码
     */
    private String fnumber;
    /**
     * 禁用状态
     */
    private String fforbidstatus;
    /**
     * 客户类型
     */
    private String fdocumentstatus;

    public String getQueryFieldKeys() {
        return BillQueryUtil.getQueryFieldKeys(this);
    }

}
