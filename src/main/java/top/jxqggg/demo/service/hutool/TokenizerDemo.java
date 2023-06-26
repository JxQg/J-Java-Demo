package top.jxqggg.demo.service.hutool;

import cn.hutool.extra.tokenizer.Result;
import cn.hutool.extra.tokenizer.TokenizerEngine;
import cn.hutool.extra.tokenizer.Word;
import cn.hutool.extra.tokenizer.engine.hanlp.HanLPEngine;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

/**
 * @author : JiangQiang
 * @className : TokenizerDemo
 * @description : TODO 类描述
 * @date :  2022/1/12
 **/
public class TokenizerDemo {

    public static void main(String[] args) {
        Log log = LogFactory.get();
        log.info("开始构建分词引擎");
        // 初始化引擎
        TokenizerEngine engine = new HanLPEngine();

        String str = "面向生产环境的多语种自然语言处理工具包，基于PyTorch和TensorFlow 2.x双引擎，目标是普及落地最前沿的NLP技术。HanLP具备功能完善、性能高效、架构清晰、语料时新、可自定义的特点。";
        log.info("分词前：{}", str);
        Result result = engine.parse(str);
        StringBuilder resultText = new StringBuilder();
        for (Word word : result) {
            resultText.append(word.getText()).append(",");
        }
        log.info("分词后：{}", resultText.toString());
    }
}
