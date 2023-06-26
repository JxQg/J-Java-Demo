package top.jxqggg.demo.service.doc;

import java.util.ArrayList;
import java.util.List;

/**
 * 导入word模板配置
 *
 * @author JiangQiang
 * @date 2022/6/28
 */
public class ImportWordModelConfig {

    public List<ImportWordModelDTO> generateCustomerContractModel() {
        List<ImportWordModelDTO> importWordModelDTOList = new ArrayList<>();
        // 序号
        ImportWordModelDTO serialNo = ImportWordModelDTO.builder()
                .fieldName("serialNo")
                .fieldDocumentType(DocumentTypeEnum.TEXT_BOX)
                .textBoxNum(0)
                .build();
        importWordModelDTOList.add(serialNo);
        // 区域
        ImportWordModelDTO area = ImportWordModelDTO.builder()
                .fieldName("area")
                .fieldDocumentType(DocumentTypeEnum.TEXT_BOX)
                .textBoxNum(6)
                .build();
        importWordModelDTOList.add(area);
        // 客户名称
        ImportWordModelDTO customerName = ImportWordModelDTO.builder()
                .fieldName("customerName")
                .fieldDocumentType(DocumentTypeEnum.TEXT_BOX)
                .textBoxNum(2)
                .build();
        importWordModelDTOList.add(customerName);

        return importWordModelDTOList;
    }

}
