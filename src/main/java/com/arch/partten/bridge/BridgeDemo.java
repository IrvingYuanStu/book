package com.arch.partten.bridge;

public class BridgeDemo {
    public static void main(String[] args) {
        Operation operation = new Operation(new IfaceImpl(), new UserFaceImpl());
        operation.operate();
    }

    /**
     * 桥：接口
     */
    public interface Iface {
        void execute();
    }

    public static class IfaceImpl implements Iface{
        @Override
        public void execute() {
            System.out.println("实现方法");
        }
    }

    /**
     * 桥：另一个接口
     */
    public interface IUserFace {
        void get();
    }

    public static class UserFaceImpl implements IUserFace {
        @Override
        public void get() {
            System.out.println("userFace 的实现方法");
        }
    }

    public static class Operation {
        private Iface iface;
        private IUserFace userFace;

        /**
         * 实现类注入的都是接口，并调用接口
         */
        public Operation(Iface iface, IUserFace userFace) {
            this.iface = iface;
            this.userFace = userFace;
        }

        public void operate() {
            System.out.println("业务逻辑, 要调用另外一个接口了");
            iface.execute();
            System.out.println("再执行一些业务逻辑，再调用另一个接口");
            userFace.get();
        }
    }
}
