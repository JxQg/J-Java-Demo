package top.jxqggg.demo.service.thread;

import cn.hutool.core.thread.ThreadUtil;

/**
 * @author : JiangQiang
 * @className : ThreadDemo
 * @description : 线程demo
 * @date :  2022/3/14
 **/
public class RunnableExDemo {

    public static void main(String[] args) {
        Runnable runnable = new RunnableDemo();

        Thread threadFir = ThreadUtil.newThread(runnable,"threadFir");
        threadFir.start();
        Thread threadSec = ThreadUtil.newThread(runnable,"threadSec");
        threadSec.start();
        ThreadUtil.execAsync(runnable, false);
        ThreadUtil.execAsync(runnable, false);
        System.out.println("倒计时：");
    }

    static class RunnableDemo implements Runnable {
        private int count = 10000;

        @Override
        public void run() {
            while (count-- > 0) {
                System.out.println(Thread.currentThread().getName() + "[" + count + "]");
            }
        }
    }
}
