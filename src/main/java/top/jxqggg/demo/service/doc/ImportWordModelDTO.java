package top.jxqggg.demo.service.doc;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * 导出模板类型
 *
 * @author JiangQiang
 * @date 2022/6/28
 */
@Builder
@Getter
@ToString
public class ImportWordModelDTO {
    /**
     * DTO字段名称
     */
    private String fieldName;
    /**
     * DTO字段来源文档类型
     */
    private DocumentTypeEnum fieldDocumentType;
    /**
     * 表格顺序
     */
    private Integer tableNum;
    /**
     * 表格行位置标识
     */
    private Integer tableRowIndex;
    /**
     * 表格列位置标识
     */
    private Integer tableColIndex;
    /**
     * 文本框顺序
     */
    private Integer textBoxNum;
}
