package com.arch.pattern.chain;

public class ChainOfRespDemo {

    public static void main(String[] args) {

        Handler secondHandler = new SecondHandler(null);
        Handler firstHandler = new FirstHandler(secondHandler);

        firstHandler.execute();
    }

    public static abstract class Handler {
        // 链上下一个节点
        private Handler nextHandler;

        public Handler() {
        }

        public Handler(Handler nextHandler) {
            this.nextHandler = nextHandler;
        }

        public abstract void execute();

        public void next() {
            if (null != nextHandler) {
                nextHandler.execute();
            }
        }
    }

    public static class FirstHandler extends Handler {
        public FirstHandler(Handler nextHandler) {
            super(nextHandler);
        }

        @Override
        public void execute() {
            System.out.println("第一个节点的逻辑");
            // 一些check
            next();
        }
    }

    public static class SecondHandler extends Handler {
        public SecondHandler(Handler nextHandler) {
            super(nextHandler);
        }

        @Override
        public void execute() {
            System.out.println("第二个节点的逻辑");
            next();
        }
    }
}
