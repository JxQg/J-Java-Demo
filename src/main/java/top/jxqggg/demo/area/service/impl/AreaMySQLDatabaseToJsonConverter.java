package top.jxqggg.demo.area.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.db.ds.simple.SimpleDataSource;
import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONUtil;
import lombok.Data;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * 地址从数据库数据转换成json格式
 *
 * @author JiangQiang
 */
public class AreaMySQLDatabaseToJsonConverter {

    public static void main(String[] args) throws SQLException {
        // 配置数据库连接信息
        String jdbcUrl = "jdbc:mysql://192.168.11.130:3306/sunrise_all?useUnicode=true&characterEncoding=UTF-8";
        String username = "dev";
        String password = "dev";
        // 创建数据源对象
        SimpleDataSource ds = new SimpleDataSource(jdbcUrl, username, password);
        Db db = Db.use(ds);
        // 读取数据库中的 district 和 district_ext 数据
        System.out.println("读取数据库中的 district 和 district_ext 数据");
        List<Entity> districtList = db.query("SELECT id, district_name, parent_id FROM district");
        List<Entity> districtExtList = db.query("SELECT id, district_name, parent_id FROM district_ext");

        // 合并两个数据列表
        System.out.println("合并两个数据列表");
        List<Entity> mergedList = mergeLists(districtList, districtExtList);

        // 转换为所需的 JSON 格式
        System.out.println("转换为所需的 JSON 格式");
        String jsonString = convertToJson(mergedList);

        // 导出 JSON 到 D 盘
        exportToJson(jsonString, "D:/area_old.json");
    }

    // 合并两个数据列表
    private static List<Entity> mergeLists(List<Entity> list1, List<Entity> list2) {
        List<Entity> mergedList = new ArrayList<>();
        mergedList.addAll(list1);
        mergedList.addAll(list2);
        return mergedList;
    }

    // 转换为 JSON 格式
    private static String convertToJson(List<Entity> dataList) {
        List<Node> nodeList = new ArrayList<>();
        for (Entity data : dataList) {
            String id = data.getStr("id");
            String parentId = data.getStr("parent_id");
            String name = data.getStr("district_name");
            Node node = new Node(id, name, parentId);
            nodeList.add(node);
        }

        List<Node> jsonList = buildJsonTree(nodeList, "0");
        return JSONUtil.toJsonStr(jsonList, createJsonConfig());
    }

    // 构建 JSON 树形结构
    private static List<Node> buildJsonTree(List<Node> nodeList, String parentId) {
        List<Node> treeList = new ArrayList<>();
        for (Node node : nodeList) {
            if (parentId.equals(node.getParentId())) {
                List<Node> childList = buildJsonTree(nodeList, node.getId());
                node.setChilds(childList);
                treeList.add(node);
            }
        }
        if (treeList.size() > 0) {
            setNull2JsonTree(treeList);
        }
        return treeList;
    }


    private static void setNull2JsonTree(List<Node> treeList) {
        for (Node node : treeList) {
            node.setParentId(null);
            if (CollUtil.isNotEmpty(node.getChilds())) {
                setNull2JsonTree(node.getChilds());
            } else {
                node.setChilds(null);
            }
        }
    }

    // 导出 JSON 到指定路径
    private static void exportToJson(String jsonString, String filePath) {
        FileUtil.writeUtf8String(jsonString, filePath);
        System.out.println("JSON 导出成功！");
    }

    // 创建 JSON 配置对象，排除为空的字段
    private static JSONConfig createJsonConfig() {
        JSONConfig config = JSONConfig.create();
        config.setIgnoreNullValue(true);
        return config;
    }
    // 定义节点类

    @Data
    static
    class Node implements Serializable {
        private String id;
        private String name;
        private String parentId;
        private List<Node> childs;

        public Node(String id, String name, String parentId) {
            this.id = id;
            this.name = name;
            this.parentId = parentId;
        }
    }
}