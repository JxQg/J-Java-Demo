package top.jxqggg.demo.service.hutool;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author : JiangQiang
 * @className : ShiftOperatorDemo
 * @description : 位移运算符demo
 * @date :  2022/3/9
 **/
public class ShiftOperatorDemo {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入字符:");
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNext()) {
            sb.append(scanner.next());
        }
        System.out.println(sb);
        scanner.close();
    }
}
