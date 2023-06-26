package top.jxqggg.demo.service.hutool;

import java.util.concurrent.atomic.AtomicLong;

/**
 * TODO
 *
 * @author JiangQiang 2022/12/14 18:02
 */
public class LongDemo {

    public static void main(String[] args) {

        AtomicLong nextId = new AtomicLong(0L);
        System.out.println(nextId);
        LongDemo longDemo = new LongDemo();
        longDemo.changeLong(nextId);
        System.out.println(nextId);
    }

    public void changeLong(AtomicLong nextId) {
        nextId.set(2L);
    }

}
