package top.jxqggg.demo.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;

/**
 * @author : JiangQiang
 * @className : BillQueryUtil
 * @description : 单据查询工具类
 * @date :  2021-12-15
 **/
public final class BillQueryUtil {

    private static final Logger logger = LoggerFactory.getLogger(BillQueryUtil.class);

    private static final Map<Class<?>, Function<FunctionParam, String>> FUNCTION_BY_CLASS_TYPE_MAP = instanceFunctionByClassTypeMap();


    /**
     * 根据模型类构建类中的属性名称
     *
     * @param obj 模型类实体
     * @return 模型中包含的属性名称
     */
    public static String getQueryFieldKeys(Object obj) {
        StringBuilder sb = new StringBuilder();
        try {
            Field[] fields = obj.getClass().getDeclaredFields();
            if (fields.length > 0) {
                for (Field field : fields) {
                    if (sb.length() > 0) {
                        sb.append(",");
                    }
                    sb.append(field.getName());
                }
            }
        } catch (Exception e) {
            logger.error("构建查询字段失败", e);
            throw new RuntimeException("构建查询字段失败", e);
        }
        return sb.toString();
    }

    /**
     * 构建查询过滤条件
     *
     * @param obj 查询条件类
     * @return 查询条件
     */
    public static String getQueryFilter(Object obj) {
        StringBuilder sb = new StringBuilder();
        try {
            Field[] fields = obj.getClass().getDeclaredFields();
            if (fields.length > 0) {
                for (Field field : fields) {
                    // 获取查询条件
                    String filterCondition = getFilterCondition(field, obj);
                    if (!StrUtil.isEmpty(filterCondition)) {
                        if (sb.length() > 0) {
                            sb.append(" and ");
                        }
                        sb.append(field.getName()).append(getFilterSymbol(field)).append(filterCondition);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("构建查询字段失败", e);
            throw new RuntimeException("构建查询字段失败", e);
        }
        return sb.toString();
    }

    /**
     * 获取字段值
     *
     * @param field 字段
     */
    public static String getFilterCondition(Field field, Object obj) throws Exception {
        field.setAccessible(true);
        Class<?> clazz = obj.getClass();
        Method method = clazz.getMethod("get" + getMethodName(field.getName()));
        Function<FunctionParam, String> function = FUNCTION_BY_CLASS_TYPE_MAP.get(field.getType());
        if (function != null) {
            return function.apply(new FunctionParam(method, obj));
        }
        return null;
    }

    /**
     * 设置 字段值
     *
     * @param field 字段
     * @param obj   实体
     * @param value 值
     */
    public static void setFieldValue(Field field, Object obj, Object value) throws Exception {
        field.setAccessible(true);
        Class<?> clazz = obj.getClass();
        Method method = clazz.getMethod("set" + getMethodName(field.getName()), field.getType());
        method.invoke(obj, value);
    }

    /**
     * 获取方法名 把首字母大写
     *
     * @param fieldName 字段名
     * @return 字段名
     */
    public static String getMethodName(String fieldName) {
        byte[] items = fieldName.getBytes();
        // 将第一个字符处理成大写 ASCII码值小写字母为：97-122 大写字母为：65-90
        if (97 <= items[0] && items[0] <= 122) {
            items[0] = (byte) ((char) items[0] - 97 + 65);
        }
        return new String(items);
    }

    /**
     * 初始化值方法对应类型
     *
     * @return 值对应类型MAP
     */
    private static Map<Class<?>, Function<FunctionParam, String>> instanceFunctionByClassTypeMap() {
        Map<Class<?>, Function<FunctionParam, String>> map = new LinkedHashMap<>(10);
        map.put(String.class, (FunctionParam param) -> stringMethodInvoke(param.getMethod(), param.getObj()));
        map.put(Character.class, (FunctionParam param) -> stringMethodInvoke(param.getMethod(), param.getObj()));
        map.put(Date.class, (FunctionParam param) -> dateMethodInvoke(param.getMethod(), param.getObj()));
        map.put(Integer.class, (FunctionParam param) -> numberMethodInvoke(param.getMethod(), param.getObj()));
        map.put(Double.class, (FunctionParam param) -> numberMethodInvoke(param.getMethod(), param.getObj()));
        map.put(Boolean.class, (FunctionParam param) -> numberMethodInvoke(param.getMethod(), param.getObj()));
        map.put(Short.class, (FunctionParam param) -> numberMethodInvoke(param.getMethod(), param.getObj()));
        map.put(Long.class, (FunctionParam param) -> numberMethodInvoke(param.getMethod(), param.getObj()));
        map.put(Float.class, (FunctionParam param) -> numberMethodInvoke(param.getMethod(), param.getObj()));
        map.put(Byte.class, (FunctionParam param) -> numberMethodInvoke(param.getMethod(), param.getObj()));
        map.put(List.class, (FunctionParam param) -> collectionMethodInvoke(param.getMethod(), param.getObj()));
        map.put(Set.class, (FunctionParam param) -> collectionMethodInvoke(param.getMethod(), param.getObj()));
        return map;
    }

    /**
     * 获取查询条件字符
     *
     * @param field 字段
     * @return 查询条件字符
     */
    private static String getFilterSymbol(Field field) {
        field.setAccessible(true);
        if (isPrimitive(field.getType())) {
            return " = ";
        }
        if (Collection.class.isAssignableFrom(field.getType())) {
            return " IN ";
        }
        return " = ";
    }

    /**
     * 获取字符类型的字段值
     *
     * @param method 方法
     * @param obj    实体
     * @return 字段值
     */
    private static String stringMethodInvoke(Method method, Object obj) {
        try {
            return "'" + method.invoke(obj) + "'";
        } catch (IllegalAccessException | InvocationTargetException e) {
            logger.error("获取字符类型的字段值失败", e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取日期字段值
     *
     * @param method 方法
     * @param obj    实体
     * @return 字段值
     */
    private static String dateMethodInvoke(Method method, Object obj) {
        try {
            return "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) method.invoke(obj)) + "'";
        } catch (IllegalAccessException | InvocationTargetException e) {
            logger.error("获取日期字段值失败", e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取数字类型的字段值
     *
     * @param method 方法
     * @param obj    实体
     * @return 字段值
     */
    private static String numberMethodInvoke(Method method, Object obj) {
        try {
            return method.invoke(obj).toString();
        } catch (IllegalAccessException | InvocationTargetException e) {
            logger.error("获取数字类型的字段值失败", e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取集合类型的字段值
     *
     * @param method 方法
     * @param obj    实体
     * @return 字段值
     */
    private static String collectionMethodInvoke(Method method, Object obj) {
        try {
            Collection<Object> collection = (Collection<Object>) method.invoke(obj);
            if (CollectionUtil.isEmpty(collection)) {
                return null;
            }
            Object object = collection.iterator().next();
            if (!isPrimitive(object.getClass())) {
                logger.warn("集合类型的字段值不支持非基本类型");
                return null;
            }
            StringBuilder filterConditions = new StringBuilder();
            for (Object o : collection) {
                if (filterConditions.length() > 0) {
                    filterConditions.append(",");
                }
                filterConditions.append("'").append(o.toString()).append("'");
            }
            return "(" + filterConditions + ")";
        } catch (Exception e) {
            logger.error("获取集合类型的字段值失败", e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断是否为基本类型
     *
     * @param cls 类型
     * @return 是否为基本类型
     */
    private static boolean isPrimitive(Class<?> cls) {
        return cls.isPrimitive() || cls == Class.class || cls == String.class || cls == Boolean.class
                || cls == Character.class || Number.class.isAssignableFrom(cls)
                || Date.class.isAssignableFrom(cls) || cls == Object.class;
    }

    /**
     * 方法参数
     */
    static class FunctionParam {
        /**
         * 方法
         */
        private final Method method;
        /**
         * 实体
         */
        private final Object obj;

        public FunctionParam(Method method, Object obj) {
            this.method = method;
            this.obj = obj;
        }

        public Method getMethod() {
            return this.method;
        }

        public Object getObj() {
            return this.obj;
        }
    }
}
