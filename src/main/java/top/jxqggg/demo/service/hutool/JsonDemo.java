package top.jxqggg.demo.service.hutool;

import cn.hutool.core.lang.ParameterizedTypeImpl;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.ToString;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @author : JiangQiang
 * @className : JsonDemo
 * @description : TODO 类描述
 * @date :  2022/2/18
 **/
public class JsonDemo {

    public static void main(String[] arg) {
//        JsonDemoEntity<JsonDemoDTO> listJsonDemoEntity = json2TBeanDemo(JsonDemoDTO.class);
//        System.out.println(listJsonDemoEntity.toString());
//        JsonParseDemo();
        json2TBeanDemo(JsonDemoDTO.class);
    }

    private static void JsonParseDemo() {
        String body = "[[{\"Result\":{\"ResponseStatus\":{\"ErrorCode\":500,\"IsSuccess\":false,\"Errors\":[{\"Message\":\"会话信息已丢失，请重新登录\",\"DIndex\":0}],\"SuccessEntitys\":[],\"SuccessMessages\":[],\"MsgCode\":1}}}]]";
        String realBody = "[[{Result={ResponseStatus={ErrorCode=500, IsSuccess=false, Errors=[{FieldName=null, Message=会话信息已丢失，请重新登录, DIndex=0}], SuccessEntitys=[], SuccessMessages=[], MsgCode=1}}}]]";
        String toString = "{Result={ResponseStatus={ErrorCode=500, IsSuccess=false, Errors=[{FieldName=null, Message=会话信息已丢失，请重新登录, DIndex=0}], SuccessEntitys=[], SuccessMessages=[], MsgCode=1}}}";
        List<List<Object>> result = JSONUtil.toBean(realBody, new TypeReference<List<List<Object>>>() {
        }, true);
        String s = result.get(0).get(0).toString();
        System.out.println(s);
        ResponseResult result1 = JSONUtil.toBean(JSONUtil.parseObj(s).getStr("Result"), ResponseResult.class);
        System.out.println(result1.toString());

    }

    private static <T> JsonDemoEntity<T> json2TBeanDemo(Class<T> tClass) {
        String body = "{\"success\":true,\"error\":\"\",\"errorCode\":100,\"data\":[{\"name\":\"业务运维更新\",\"id\":\"ff67b038-0639-4703-a385-3dd971804e79\",\"weights\":7,\"department\":\"客户成功部\\\\头部客服\\\\业务运维更新\",\"parentId\":\"2b117f95-d349-422f-9f2a-dead7e6c0375\"}]}";
        JsonDemoEntity<?> jsonDemoEntity = JSONObject.parseObject(body, JsonDemoEntity.class);
        System.out.println(jsonDemoEntity.toString());
        System.out.println(jsonDemoEntity.getData());
        List<T> t = JSONUtil.toList(jsonDemoEntity.getData().toString(), tClass);
        System.out.println(t.toString());
        return JSONUtil.toBean(body, new TypeReference<JsonDemoEntity<T>>() {
        }, true);
    }

    private static <T> JsonDemoEntity<List<T>> parseListResult(String json, Class<T> clazz) {

        return JSONUtil.toBean(json, buildType(JsonDemoEntity.class, List.class, clazz), false);
    }

    private static Type buildType(Type... types) {
        ParameterizedTypeImpl beforeType = null;
        if (types != null && types.length > 0) {
            for (int i = types.length - 1; i > 0; i--) {
                beforeType = new ParameterizedTypeImpl(new Type[]{beforeType == null ? types[i] : beforeType}, null, types[i - 1]);
            }
        }
        return beforeType;
    }

    @Data
    @ToString
    static class JsonDemoEntity<T> {

        public String errorCode;

        public T data;

    }

    @Data
    @ToString
    static class JsonDemoDTO {
        public String name;

        public String id;

    }

    @Data
    @ToString
    public class ResponseResult {
        /**
         * 返回ID
         */
        private Long Id;
        /**
         * 返回编码
         */
        private String Number;
        /**
         * 返回状态
         */
        private ResponseStatus ResponseStatus;

        /**
         * 返回结果
         */
        private String result;

        @Data
        @ToString
        public class ResponseStatus {
            private String ErrorCode;
            private Boolean IsSuccess;
            private String MsgCode;
        }
    }

}
