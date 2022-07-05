package com.arch.pattern.singleton;

/**
 * 内部类单例
 */
public class InnerInstance {

    private InnerInstance () {}

    /**
     * private 静态 内部类
     * INSTANCE只初始化一次，且LazyHolder类不会启动即初始化
     * private 外部不能访问LazyHolder类
     */
    private static class LazyHolder {
        private static final InnerInstance INSTANCE = new InnerInstance();
    }

    public static InnerInstance getInstance() {
        return LazyHolder.INSTANCE;
    }
}
