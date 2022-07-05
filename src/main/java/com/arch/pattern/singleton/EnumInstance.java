package com.arch.pattern.singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * 单个枚举对象的单例是最安全的
 */
public enum EnumInstance {
    // 单例对象
    INSTANCE;

    // 单例对象里的方法和属性
    private Map<String, String> map = new HashMap<>();

    public Map<String, String> getMap() {
        return map;
    }
}
