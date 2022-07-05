package com.arch.pattern.guardedsuspension;

import java.util.LinkedList;

public class GsDemo {
    private LinkedList<String> queue = new LinkedList<>();

    /**
     * 取队列的元素
     */
    public synchronized String get() {
        // 不满足前置的守卫条件
        while (0 <= queue.size()) {
            try {
                // 挂起
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return queue.removeFirst();
    }

    /**
     * 添加
     */
    public synchronized void put(String item) {
        queue.addLast(item);
        // 通知所有线程结束挂起
        notifyAll();
    }
}
