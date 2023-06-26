package top.jxqggg.demo.api.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author : JiangQiang
 * @className : TestParam
 * @description : TODO 类描述
 * @date :  2021/12/16
 **/
@Getter
@Setter
public class TestRequest {

    private Integer FUSEORGID;

    private String FNUMBER;

    private String FDOCUMENTSTATUS;

    private List<Long> FCUSTOMERID;

}
