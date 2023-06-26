package top.jxqggg.demo.service.hutool;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : JiangQiang
 * @className : ReflectDemo
 * @description : TODO 类描述
 * @date :  2022/2/23
 **/
@Setter
@Getter
public class ReflectDemo<T> {
    private T data;
    private String name;

    public static void main(String[] str) {
        System.out.println(getValue("1").toString());
        System.out.println(getValue("2").toString());
        System.out.println(getValue("3").toString());
//        String body = "{\"data\":[{\"id\":1},{\"id\":2}],\"name\":\"test\"}";
//
//        ReflectDemo reflectDemo = JSONUtil.toBean(body, ReflectDemo.class);
//        System.out.println(reflectDemo.getName());
//
//        ReflectDemo<List<Redo>> objectReflectDemo = returnReflectDemoData(body);
//        for (Redo datum : objectReflectDemo.getData()) {
//            System.out.println(datum.getId());
//        }
//        System.out.println(objectReflectDemo.getName());
    }

    public static ReflectDemo<List<Redo>> returnReflectDemoData(String body) {
        return JSONUtil.toBean(body, new TypeReference<ReflectDemo<List<Redo>>>() {
        }, false);
    }

    public static <E> E getValue(String code) {
        Map<String, Object> valueByCode = new HashMap<>(1);
        valueByCode.put("1", 1);
        valueByCode.put("2", "2");
        Redo redo = new Redo();
        redo.setId(3);
        valueByCode.put("3", redo);
        return (E) valueByCode.get(code);
    }

    @Getter
    @Setter
    @ToString
    static
    class Redo {
        private int id;
    }
}
