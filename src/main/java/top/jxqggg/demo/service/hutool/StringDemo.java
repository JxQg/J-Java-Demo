package top.jxqggg.demo.service.hutool;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;

import java.util.Date;
import java.util.List;

/**
 * TODO
 *
 * @author JiangQiang
 * @date 2022/6/9
 */
public class StringDemo {

    public static void main(String[] args) {
        String position = "（苏锡常战区）常州玖贸子公司副总经理";
        String position1 = "战区总经理（宁舟战区）兼渠道运营总监（宁绍舟）";
        System.out.println(StrUtil.subPreGbk(position, 30, false));
        System.out.println(StrUtil.subPreGbk(position1, 30, false));


        String resultSeq = "1234567890";
        String sequence = resultSeq.replaceAll("4", "5");
        int index = StrUtil.lastIndexOfIgnoreCase(resultSeq, "4");
        System.out.println(index);
        System.out.println(resultSeq.substring(index));
        System.out.println(sequence);

        String department = "业务平台\\久加久新零售事业部\\区域子公司\\青田子公司\\业务拓展部";
        String organization = "业务平台";
        List<String> split = StrUtil.split(department, StrUtil.BACKSLASH);
        List<String> splitOrganization = StrUtil.split(organization, StrUtil.BACKSLASH);
        System.out.println(split.size());
        System.out.println(splitOrganization.size());
        System.out.println(JSONUtil.toJsonStr(split));
        System.out.println(JSONUtil.toJsonStr(splitOrganization));

        long time = new Date().getTime();
        String s = String.valueOf(time);
        System.out.println(s);


//
//        StringJoiner S1 = new StringJoiner(",", "[", "]");
//        String[] str = {" ", "I", "II", "III", "IV", "V", "VI", "VII", "IIX", "IX"};
//        Scanner sc = new Scanner(System.in);
//        boolean checkFinish = false;
//        do {
//            String s = sc.next();
//            if (s.length() > 9) {
//                System.out.println("长度超限");
//                continue;
//            }
//            for (int i = 0; i < s.length(); i++) {
//                if (s.charAt(i) < 48 || s.charAt(i) > 57) {
//                    System.out.println("内容非法");
//                    break;
//                } else {
//                    S1.add(str[s.charAt(i) - 48]);
//                    checkFinish = true;
//                }
//            }
//        } while (!checkFinish);
//        System.out.println(S1);
    }
}




