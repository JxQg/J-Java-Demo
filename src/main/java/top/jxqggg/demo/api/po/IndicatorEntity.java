package top.jxqggg.demo.api.po;

import lombok.Data;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * 指数估值实体
 *
 * @author JiangQiang 2022/10/31
 */
@Data
@ToString
public class IndicatorEntity {

    private String date;

    private String indexCode;

    private String chineseName;

    private String indexChineseName;

    private String englishName;

    private String indexEnglishName;

    private Double priceEarningRatioFirst;

    private Double priceEarningRatioSecond;

    private Double dividendYieldFirst;

    private Double dividendYieldSecond;

    public static Map<String, String> buildHeaderAlias() {
        Map<String, String> headerAlias = new HashMap<>(10);
        headerAlias.put("日期Date", "data");
        headerAlias.put("指数代码Index Code", "indexCode");
        headerAlias.put("指数中文全称Chinese Name(Full)", "chineseName");
        headerAlias.put("指数中文简称Index Chinese Name", "indexChineseName");
        headerAlias.put("指数英文全称English Name(Full)", "englishName");
        headerAlias.put("指数英文简称Index English Name", "indexEnglishName");
        headerAlias.put("市盈率1（总股本）P/E1", "priceEarningRatioFirst");
        headerAlias.put("市盈率2（计算用股本）P/E2", "priceEarningRatioSecond");
        headerAlias.put("股息率1（总股本）D/P1", "dividendYieldFirst");
        headerAlias.put("股息率2（计算用股本）D/P2", "dividendYieldSecond");
        return headerAlias;
    }
}
