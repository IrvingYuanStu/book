package com.arch.pattern.proxy;

public class ProxyDemo {
    public static void main(String[] args) {
        Proxy targetProxy = new TargetProxy(new Target());
        targetProxy.doExecute();
    }

    /**
     * 代理接口
     */
    public interface Proxy {
        void doExecute();
    }

    public static class Target implements Proxy {
        @Override
        public void doExecute() {
            //真实的业务需求
            System.out.println("业务逻辑方法");
        }
    }

    public static class TargetProxy implements Proxy {
        private Proxy target;

        public TargetProxy(Proxy target) {
            this.target = target;
        }

        @Override
        public void doExecute() {
            // 1.代理的逻辑
            System.out.println("代理逻辑");
            // 2.自己的逻辑
            target.doExecute();
        }
    }
}
