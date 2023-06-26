package top.jxqggg.demo.service.hutool;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : JiangQiang
 * @className : SalaryCalcDemo
 * @description : 工资计算器demo
 * @date :  2022/3/4
 **/
public class SalaryCalcDemo {

    private static final BigDecimal BASIC_SALARY = new BigDecimal(60000);

    private static final BigDecimal FIRST_TAX = new BigDecimal(36000);
    private static final BigDecimal SECOND_TAX = new BigDecimal(144000);
    private static final BigDecimal THIRD_TAX = new BigDecimal(300000);
    private static final BigDecimal FOURTH_TAX = new BigDecimal(420000);
    private static final BigDecimal FIFTH_TAX = new BigDecimal(660000);
    private static final BigDecimal SIXTH_TAX = new BigDecimal(960000);


    public static void main(String[] args) {
        BigDecimal salary = new BigDecimal(String.valueOf(System.in));
        BigDecimal needTaxSalary = salary.subtract(BASIC_SALARY);
        Map<TAX, BigDecimal> stageTax = new HashMap<>(7);
        for (TAX value : TAX.values()) {

        }
    }

    @Getter
    @AllArgsConstructor
    public enum TAX {
        /**
         * 阶梯
         */
        FIRST_TAX(BigDecimal.valueOf(36000), BigDecimal.valueOf(0.03)),
        SECOND_TAX(BigDecimal.valueOf(144000), BigDecimal.valueOf(0.1)),
        THIRD_TAX(BigDecimal.valueOf(300000), BigDecimal.valueOf(0.2)),
        FOURTH_TAX(BigDecimal.valueOf(420000), BigDecimal.valueOf(0.25)),
        FIFTH_TAX(BigDecimal.valueOf(660000), BigDecimal.valueOf(0.3)),
        SIXTH_TAX(BigDecimal.valueOf(960000), BigDecimal.valueOf(0.35)),
        SEVENTH_TAX(BigDecimal.valueOf(96000), BigDecimal.valueOf(0.4));

        private final BigDecimal exceed;
        private final BigDecimal tax;
    }
}