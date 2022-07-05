package com.arch.pattern.singleton;

public class LazySingleton {
    /**
     * volatile禁止指令冲排序
     */
    private static volatile LazySingleton INSTANCE;

    private LazySingleton() {}

    public static LazySingleton getInstance() {
        // 等于null时执行初始化
        if (null == INSTANCE) {
            /**
             * 锁住类class，避免
             * 线程1判断为null，还没有执行初始化，让出给线程2
             * 线程2判断也是null，执行初始化，让出给线程1
             * 线程1再次初始化，创建了两次
             */
            synchronized (LazySingleton.class) {
                /**
                 * 再次判断是不是空，比如
                 * 线程1、2都判断实例是null，但1拿到了锁执行完初始化，2等待
                 * 线程2再拿到锁时，1已经初始化了，所以要判断是否非空
                 */
                if (null == INSTANCE) {
                    INSTANCE = new LazySingleton();
                }
            }
        }
        return INSTANCE;
    }
}
