package top.jxqggg.demo.service.doc;

import cn.hutool.core.text.StrBuilder;
import com.spire.doc.*;
import com.spire.doc.collections.CellCollection;
import com.spire.doc.collections.RowCollection;
import com.spire.doc.collections.TableCollection;
import com.spire.doc.collections.TextBoxCollection;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.fields.DocPicture;
import com.spire.doc.fields.TextBox;
import top.jxqggg.demo.util.BillQueryUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author JiangQiang
 * @date 2022/6/23
 */
public class WordDemo {

    private final static String WORD_PATH = "C:\\Users\\Shangyuan-IT-001\\Desktop\\五星俱乐部 入会协议 （2022版).docx";

    public static void main(String[] args) {
        readWord();
    }

    private static void readWord() {
        File file = new File(WORD_PATH);
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            Document document = new Document(fileInputStream);
            StrBuilder strBuilder = new StrBuilder();
            List<CustomerContractDTO> customerContractDTOList = new ArrayList<>(1);
            ImportWordModelConfig importWordModelConfig = new ImportWordModelConfig();
            List<ImportWordModelDTO> importWordModelDTOList = importWordModelConfig.generateCustomerContractModel();
            CustomerContractDTO customerContractDTO = new CustomerContractDTO();
            for (ImportWordModelDTO importWordModelDTO : importWordModelDTOList) {

                if (DocumentTypeEnum.TEXT_BOX.equals(importWordModelDTO.getFieldDocumentType())) {
                    getTextBox2DTOField(document, importWordModelDTO, customerContractDTO);
                    continue;
                }
                if (DocumentTypeEnum.TABLE.equals(importWordModelDTO.getFieldDocumentType())) {
//                    getTables();
//                    continue;
                }
            }

            customerContractDTOList.add(customerContractDTO);
            ExcelDemo excelDemo = new ExcelDemo(customerContractDTOList);
            excelDemo.writeToFile();
            getTextBox(document,strBuilder);
            System.out.println(strBuilder);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void getTextBox2DTOField(Document document, ImportWordModelDTO importWordModelDTO, CustomerContractDTO customerContractDTO) {
        TextBoxCollection textBoxes = document.getTextBoxes();
        TextBox textBox = textBoxes.get(importWordModelDTO.getTextBoxNum());
        DocumentObject documentObject = textBox.getBody().getChildObjects().get(0);
        if (documentObject instanceof Paragraph) {
            String text = ((Paragraph) documentObject).getText();
            try {
                Field declaredField = CustomerContractDTO.class.getDeclaredField(importWordModelDTO.getFieldName());
                BillQueryUtil.setFieldValue(declaredField, customerContractDTO, text);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


    private static void getText(Document document, StrBuilder strBuilder) {
        strBuilder.append(document.getText());
    }

    private static void getTextBox(Document document, StrBuilder strBuilder) {
        TextBoxCollection textBoxes = document.getTextBoxes();

        for (int textBoxIndex = 0; textBoxIndex < textBoxes.getCount(); textBoxIndex++) {
            TextBox textBox = textBoxes.get(textBoxIndex);
            for (Object childObject : textBox.getBody().getChildObjects()) {
                //判定是否为文本段落
                if (childObject instanceof Paragraph) {
                    //获取段落中的文本
                    String text = ((Paragraph) childObject).getText();
                    //写入文本到txt文档
                    strBuilder.append("(").append(textBoxIndex).append(")").append(".").append(text).append("\n");
                }
            }
        }
    }

    private static void getTables(Document document, StrBuilder strBuilder) {
        strBuilder.append("table").append("\n");
        TableCollection tables = document.getSections().get(0).getTables();
        for (int tableIndex = 0; tableIndex < tables.getCount(); tableIndex++) {
            if (tableIndex == 0) {
                continue;
            }
            Table table = tables.get(tableIndex);
            RowCollection rows = table.getRows();
            for (int rowIndex = 0; rowIndex < rows.getCount(); rowIndex++) {
                if (rowIndex == 0) {
                    continue;
                }
                TableRow tableRow = rows.get(rowIndex);
                CellCollection cells = tableRow.getCells();
                for (int colIndex = 0; colIndex < cells.getCount(); colIndex++) {
                    TableCell cell = cells.get(colIndex);
                    //遍历单元格中的段落
                    for (int k = 0; k < cell.getParagraphs().getCount(); k++) {
                        Paragraph paragraph = cell.getParagraphs().get(k);
                        strBuilder.append("row:").append(rowIndex).append(" col:").append(colIndex).append(" value:").append(paragraph.getText()).append("\t");
                        //遍历段落中的所有子对象
                        for (int x = 0; x < paragraph.getChildObjects().getCount(); x++) {
                            Object object = paragraph.getChildObjects().get(x);
                            //判定对象是否为图片
                            if (object instanceof DocPicture) {
                                //获取图片
                                DocPicture picture = (DocPicture) object;
                                strBuilder.append(" Pic:").append(picture.getImage().toString());

                            }
                        }
                        strBuilder.append("\n");
                    }
                }
            }
        }
    }
}
