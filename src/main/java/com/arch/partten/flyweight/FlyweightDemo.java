package com.arch.partten.flyweight;

import java.util.HashMap;
import java.util.Map;

public class FlyweightDemo {

    public static void main(String[] args) {
        FlyweightFactory flyweightFactory = new FlyweightFactory();
        flyweightFactory.createFlyweight("test");
    }

    /**
     * 元抽象接口
     */
    public interface Flyweight {
        void operate();
    }

    public static class ConcreteFlyweight implements Flyweight {
        @Override
        public void operate() {
            System.out.println("具体的操作");
        }
    }

    /**
     * 元对象管理工厂，可以是单例的,很灵活
     */
    public static class FlyweightFactory {
        // 管理所有的对象
        private Map<String, Flyweight> map = new HashMap<>();

        public Flyweight createFlyweight(String name) {
            Flyweight flyweight = map.get(name);
            // 保证flyweight数量是有限的
            if (null == flyweight) {
                synchronized (Flyweight.class) {
                    if (null == map.get(name)) {
                        // 这个创建可以根据name来策略、工厂方法
                        flyweight = new ConcreteFlyweight();
                        map.put(name, flyweight);
                    }
                }
            }
            return flyweight;
        }
    }
}
