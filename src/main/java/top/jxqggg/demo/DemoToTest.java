package top.jxqggg.demo;

import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.jxqggg.demo.api.request.TestRequest;
import top.jxqggg.demo.api.po.TestPo;
import top.jxqggg.demo.util.BillQueryUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : JiangQiang
 * @className : DemoToTest
 * @description : demo
 * @date :  2021-12-14
 **/
@Log4j2
public class DemoToTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoToTest.class);

    public static void main(String[] args) {
        try {
            LOGGER.info("start");
            test1();
            test();
            LOGGER.info("end");
        } catch (Exception e) {
            LOGGER.error("error", e);
            e.printStackTrace();
        }

    }

    private static void test() throws Exception {
        List<List<Object>> responseQueryDataList = new ArrayList<>();
//        List<Object> responseQueryData = Arrays.asList(1, "33.01.04.0209", "A", "C");
        List<Object> responseQueryData = new ArrayList<>();
//        responseQueryDataList.add(responseQueryData);
        List<TestPo> resultData = new ArrayList<>();

        Field[] declaredFields = TestPo.class.getDeclaredFields();
        // 解析返回结果
        for (List<Object> responseQueryDatum : responseQueryDataList) {
            int index = 0;
            TestPo t = new TestPo();
            for (Object obj : responseQueryDatum) {
                // 设置参数值
                BillQueryUtil.setFieldValue(declaredFields[index], t, obj);
                index++;
            }
            resultData.add(t);
        }
        resultData.forEach(x -> System.out.println(x.toString() + "\n"));
    }

    private static void test1() {
        TestRequest testRequest = new TestRequest();
        testRequest.setFUSEORGID(1);
        testRequest.setFNUMBER("2");
        testRequest.setFDOCUMENTSTATUS("C");
        testRequest.setFCUSTOMERID(Arrays.asList(1L, 2L, 3L));
        String queryFieldKeys = new TestPo().getQueryFieldKeys();
        String queryFilter = BillQueryUtil.getQueryFilter(testRequest);
        System.out.println("queryFieldKeys:" + queryFieldKeys + "\nqueryFilter:" + queryFilter);
    }


    private static void extracted() {
        String signOnTime = "08:30";
        String hour = signOnTime.substring(0, 2);
        String minus = signOnTime.substring(3, 5);
        System.out.println(hour + ":" + minus);
    }
}
