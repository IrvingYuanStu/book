package com.arch.pattern.strategy;

import java.util.HashMap;
import java.util.Map;

public class StrategyDemo {

    public static void main(String[] args) {
        // 简单调用一下枚举策略
        String key = "Idle";
        switch (HandlerEnum.keyOf(key)) {
            case IDLE:
                HandlerEnum.IDLE.handle();
                break;
            case RUNNING:
                HandlerEnum.RUNNING.handle();
                break;
        }
    }

    /**
     * 策略接口
     */
    public interface Handler {
        void handle();
    }

    public static class IdleHandler implements Handler{
        @Override
        public void handle() {
            System.out.println("idle handle");
        }
    }

    public static class RunningHandler implements Handler{
        @Override
        public void handle() {
            System.out.println("running handle");
        }
    }

    /**
     * 可以有这么一个上下文，管理所有的策略
     */
    public static class Context {
        Map<String, Handler> handlerMap = new HashMap<>();

        public void handle(String key) {
            handlerMap.get(key).handle();
        }

        // 其他初始化方法
    }

    /**
     * 变种的策略，直接用枚举当上下文
     * 这种枚举策略在CAT的Long-SQL，Long-Service中使用了
     */
    public enum HandlerEnum {
        IDLE("Idle") {
            @Override
            public void handle() {
                System.out.println("Idle handle");
            }
        },
        RUNNING("Running") {
            @Override
            public void handle() {
                System.out.println("running handle");
            }
        };

        String key;
        HandlerEnum(String key) {
            this.key = key;
        }

        // 策略的接口
        public abstract void handle();

        // 其他方法
        public static HandlerEnum keyOf(String key) {
            for (HandlerEnum handlerEnum :values()) {
                if (handlerEnum.key.equalsIgnoreCase(key)) {
                    return handlerEnum;
                }
            }
            return null;
        }
    }
}
