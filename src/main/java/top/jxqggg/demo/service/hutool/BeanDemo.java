package top.jxqggg.demo.service.hutool;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : JiangQiang
 * @className : BeanDemo
 * @description : TODO 类描述
 * @date :  2022/3/2
 **/
public class BeanDemo {

    public static void main(String[] args) {
//        Map<String, List<String>> map = new HashMap<>(1);
//        List<String> innerSignManagerIdList = map.get("1");
//        String s = innerSignManagerIdList.stream().findFirst().orElse(null);
//        BeanDTO beanDTO = new BeanDTO();
//        beanDTO.setName("1111111");
//        BeanDTO1 bean1 = BeanDemo.transform(beanDTO, BeanDTO1.class);
//        System.out.println(bean1.getName());

        BeanDTO1 beanDTO1 = new BeanDTO1();
        beanDTO1.setDescription("description1");
        BeanDemo beanDemo = new BeanDemo();
        beanDemo.setBeanDTO(beanDTO1);
        System.out.println(JSON.toJSONString(beanDTO1));
    }

    public static <FromType, ToType> ToType transform(FromType sourceObject, Class<ToType> resultClass) {
        return JSON.parseObject(JSON.toJSONString(sourceObject), resultClass);
    }

    public void setBeanDTO(BeanDTO beanDTO) {
        beanDTO.setTest("测试");
    }

    @Data
    static class BeanDTO {
        private String test;

    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    static class BeanDTO1 extends BeanDTO {
        private String description;
    }
}
