package com.java.threadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;
import com.java8.book.domain.Apple;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    private static final ExecutorService es = Executors.newSingleThreadExecutor();

    private static final InheritableThreadLocal<Apple> inheritableThreadLocal = new InheritableThreadLocal<>();

    private static final TransmittableThreadLocal<Apple> transmitableThreadLocal = new TransmittableThreadLocal<>();

    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        Test test = new Test();

        test.testThreadLocal();
//        test.testInheritable();
//        test.testTransmit();
    }

    public void testThreadLocal() throws InterruptedException {
        threadLocal.set("hello world");
        Thread thread = new Thread(() -> System.out.println(threadLocal.get()));
        thread.start();

        Thread.sleep(10);

        System.out.println(threadLocal.get());
    }

    public void testInheritable() throws InterruptedException {
        // 如果先init线程池
        es.submit(() -> System.out.println("init first"));

        Apple apple = new Apple();
        apple.setColor("red");

        inheritableThreadLocal.set(apple);
        es.submit(() -> {
            Apple a = inheritableThreadLocal.get();
            if (null != a) {
                System.out.println(a.getColor());
                a.setColor("green");
            } else {
                System.out.println("未取到threadlocal");
            }
        });

        Thread.sleep(10);

        String color = inheritableThreadLocal.get().getColor();
        System.out.println(color);
    }

    public void testTransmit() throws InterruptedException {
        es.submit(Objects.requireNonNull(TtlRunnable.get(() -> System.out.println("init first"))));

        Apple apple = new Apple();
        apple.setColor("red");
        transmitableThreadLocal.set(apple);

        es.submit(Objects.requireNonNull(TtlRunnable.get(() -> {
            Apple a = transmitableThreadLocal.get();
            if (null != a) {
                System.out.println(a.getColor());
            }
        })));

        Thread.sleep(10);
        System.out.println(transmitableThreadLocal.get().getColor());
    }
}
