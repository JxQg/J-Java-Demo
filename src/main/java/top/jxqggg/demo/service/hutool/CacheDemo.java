package top.jxqggg.demo.service.hutool;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;

/**
 * TODO
 *
 * @author JiangQiang 2022/8/30
 */
public class CacheDemo {

    public static void main(String[] args) {
        TimedCache<String, String> timedCache = CacheUtil.newTimedCache(10);
        timedCache.put("1", "1", 5);
        timedCache.put("2", "2");

        timedCache.put("1", "3");

        for (String s : timedCache) {
            System.out.println(s);
        }

    }
}
