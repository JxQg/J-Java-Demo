package top.jxqggg.demo.service.thread;

import lombok.Data;

import java.util.concurrent.Callable;

/**
 * @author : JiangQiang
 * @date :  2022/3/14
 **/
public class CallableExDemo {

    public static void main(String[] args) {

    }

    static class CallableDemo implements Callable<CallableDTO> {

        @Override
        public CallableDTO call() throws Exception {
            return null;
        }
    }

    @Data
    static class CallableDTO {
        private String name;
        private String count;
    }

}
