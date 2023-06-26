package top.jxqggg.demo.service.hutool;

import cn.hutool.db.nosql.redis.RedisDS;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @author : JiangQiang
 * @className : Redis
 * @description : redis
 * @date :  2022/1/12
 **/
public class RedisDemo {

    public static void main(String[] args) {
        Log log = LogFactory.get();

        log.info("Redis Created By Jedis");
        Jedis jedis = RedisDS.create().getJedis();
        String name = jedis.get("name");
        System.out.println(name);
        log.info("name get By Jedis");
        jedis.set("name", "jxqggg");

    }
}
