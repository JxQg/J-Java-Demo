package top.jxqggg.demo.service.doc;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import top.jxqggg.demo.api.po.IndicatorEntity;

import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author JiangQiang
 * @date 2022/6/28
 */
@Slf4j
@AllArgsConstructor
public class ExcelDemo {

    private static final String EXCEL_MODEL_PATH = "C:\\Users\\Shangyuan-IT-001\\Desktop\\导出模板.xlsx";

    private static final String EXCEL_PATH = "C:\\Users\\Shangyuan-IT-001\\Desktop\\导出表格.xlsx";

    private List<CustomerContractDTO> customerContractList;

    public static void main(String[] args) {
        ExcelReader reader = ExcelUtil.getReader("D://000969indicator.xls", 0);
        Map<String, String> headerAlias = IndicatorEntity.buildHeaderAlias();
        List<IndicatorEntity> indicatorEntities = reader.setHeaderAlias(headerAlias).readAll(IndicatorEntity.class);
        if (CollUtil.isNotEmpty(indicatorEntities)) {
            for (IndicatorEntity entity : indicatorEntities) {
                log.info(entity.toString());
            }
        }
    }

    public void writeToFile() {
        log.info("准备导出Excel。");
        log.info("读取模板{}", EXCEL_MODEL_PATH);
        ExcelReader modelReader = ExcelUtil.getReader(EXCEL_MODEL_PATH);
        ExcelWriter writer = modelReader.getWriter();

        for (int rowIndex = 0; rowIndex < customerContractList.size(); rowIndex++) {
            log.info("写入数据到Excel，行数{}", rowIndex);
            ExcelWriter excelWriter = writer.setCurrentRow(rowIndex + 1);
            excelWriter.writeRow(customerContractList.get(rowIndex), false);
        }
        writer.flush(FileUtil.file(EXCEL_PATH));
        log.info("导出成功!文件路径:{}", EXCEL_PATH);
    }
}
