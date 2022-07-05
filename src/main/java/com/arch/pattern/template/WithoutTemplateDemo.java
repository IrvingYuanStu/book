package com.arch.pattern.template;

public class WithoutTemplateDemo {

    public static void main(String[] args) {
        Handler1 handler1 = new Handler1();
        handler1.execute();
        Handler2 handler2 = new Handler2();
        handler2.execute();
        Handler3 handler3 = new Handler3();
        handler3.execute();
    }

    public static class Handler1 {
        public void execute() {
            System.out.println("公共逻辑");
            System.out.println("Handler1的特殊逻辑");
        }
    }

    public static class Handler2 {
        public void execute() {
            System.out.println("公共逻辑");
            System.out.println("Handler2的特殊逻辑");
        }
    }

    public static class Handler3 {
        public void execute() {
            System.out.println("公共逻辑");
            System.out.println("Handler3的特殊逻辑");
        }
    }
}
