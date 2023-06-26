package top.jxqggg.demo.controller;

import cn.hutool.core.bean.BeanUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.jxqggg.demo.api.dto.J2DemoDTO;
import top.jxqggg.demo.api.request.J2DemoRequest;
import top.jxqggg.demo.api.vo.J2DemoVO;
import top.jxqggg.demo.service.J2DemoService;

import javax.validation.Valid;
import java.math.BigDecimal;

/**
 * 测试api接口
 *
 * @author : JiangQiang
 * @date :  2022/1/12
 **/
@RestController
@AllArgsConstructor
public class J2DemoController {

    private final J2DemoService j2DemoService;

    /**
     * 首页默认页面、
     *
     * @param name 姓名
     * @return 首页内容
     * @apiNote 根据传入的姓名返回对应的内容
     */
    @GetMapping("/hello")
    public String hello(@RequestParam String name) {
        return "hello" + name;
    }

    /**
     * 数学相加
     *
     * @param demoRequest 请求参数
     * @return 相加后的值
     */
    @PostMapping("/math/add")
    public ResponseEntity<J2DemoVO> mathAdd(@Valid @RequestBody J2DemoRequest demoRequest) {
        J2DemoDTO j2DemoDTO = BeanUtil.copyProperties(demoRequest, J2DemoDTO.class);
        BigDecimal sum = j2DemoService.mathAdd(j2DemoDTO);
        J2DemoVO j2DemoVO = new J2DemoVO();
        j2DemoVO.setSum(sum);
        return ResponseEntity.ok(j2DemoVO);
    }

}
