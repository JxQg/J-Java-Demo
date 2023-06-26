package top.jxqggg.demo.service.aop;

import cn.hutool.aop.ProxyUtil;
import cn.hutool.aop.aspects.SimpleAspect;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;

import java.lang.reflect.Method;

/**
 * jdk实现模式代理
 * @author : JiangQiang
 * @date :  2022/3/15
 **/
public class JdkAopProxyDemo {

    public static void main(String[] args) {
        Animal dog = ProxyUtil.proxy(new Dog(), AnimalAspect.class);
        dog.eat();
    }

    interface Animal {
        /**
         * 吃
         */
        void eat();
    }

    static class AnimalAspect extends SimpleAspect {
        @Override
        public boolean before(Object target, Method method, Object[] args) {
            Console.log("Hunting.. at time[{}]", DateUtil.now());
            return super.before(target, method, args);
        }

        @Override
        public boolean after(Object target, Method method, Object[] args, Object returnVal) {
            Console.log("Cleaning.. at time[{}]", DateUtil.now());
            return super.after(target, method, args, returnVal);
        }

        @Override
        public boolean afterException(Object target, Method method, Object[] args, Throwable e) {
            return super.afterException(target, method, args, e);
        }
    }

    static class Dog implements Animal {

        @Override
        public void eat() {
            Console.log("Dog eat!");
        }
    }

}
