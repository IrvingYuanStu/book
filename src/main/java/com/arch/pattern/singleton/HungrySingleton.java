package com.arch.pattern.singleton;

public class HungrySingleton {
    /**
     * 静态，不可变, 加载类信息时直接创建
     */
    private static final HungrySingleton INSTANCE = new HungrySingleton();

    /**
     * 构造方法私有，不允许外部创建
     */
    private HungrySingleton() {}

    public static HungrySingleton getInstance() {
        return INSTANCE;
    }
}
