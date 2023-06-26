package top.jxqggg.demo.service;

import cn.hutool.system.SystemUtil;
import lombok.Data;
import org.apache.poi.ss.usermodel.Row;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author JiangQiang 2022/12/6 15:43
 */
public class ListMemoryDemo {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        long freeMemory = SystemUtil.getFreeMemory();
        long totalMemory = SystemUtil.getTotalMemory();
        System.out.println(totalMemory + "Gb");
        System.out.println(freeMemory + "Gb");
        System.out.println();
        List<Person> personList = new ArrayList<>(500);
        Person person;
        for (int i = 0; i < 10000; i++) {
            person = new Person();
            person.setName("测试" + i);
            person.setBirthday("2222-22-22" + i);
            person.setPositiveDate("2222-22-22" + i);
            person.setHireDate("2222-22-22" + i);
            person.setGender("1");
            person.setEmail("asdfasdf@dasfasf.com");
            person.setDepartment("12312321321321");
            person.setOpenId("1234131231231231" + i);
            personList.add(person);
        }
        int rows = 10000;
        System.out.println(String.format("查询到云之家在职员工数据共%d条，保存到中间表:%d条", personList.size(), rows));
        System.out.println((System.currentTimeMillis() - start) + "ms");
        System.out.println((freeMemory - SystemUtil.getFreeMemory()) + "Mb");
    }

    @Data
    static class Person {
        /**
         * 生日
         */
        public String birthday;
        /**
         * 入职日期
         */
        public String hireDate;
        /**
         * 转正日期
         */
        public String positiveDate;
        /**
         * 性别,0: 不确定; 1: 男; 2: 女
         */
        public String gender;
        /**
         * 是否在通讯录中隐藏手机号码,0: 不隐藏; 1: 隐藏,默认为0
         */
        public String isHidePhone;
        /**
         * 人员的openid
         */
        public String openId;
        /**
         * 职位名称
         */
        public String jobTitle;
        /**
         * 权重 用于排序
         */
        public String weights;
        /**
         * 组织ID
         */
        public String orgId;
        /**
         * 头像URL
         */
        public String photoUrl;
        /**
         * uid
         */
        public String uid;
        /**
         * 手机号码
         */
        public String phone;
        /**
         * 是否部门负责人 0:否， 1：是
         */
        public String orgUserType;
        /**
         * 联系信息
         */
        public String contact;
        /**
         * 企业工号
         */
        public String jobNo;
        /**
         * 姓名
         */
        public String name;
        /**
         * 组织长名称
         */
        public String department;
        /**
         * 邮箱
         */
        public String email;
        /**
         * 状态 0: 注销，1: 正常，2: 禁用
         */
        public String status;
    }
}
