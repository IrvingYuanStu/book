package com.arch.pattern.decorator;

import java.io.*;

public class DecoratorDemo {
    public static void main(String[] args) throws Exception{
        // 把被包装的类进行包装
        Component component = new Decorator(new ConcreteComponent());
        component.operate();

        // JavaIO大量这种包装
        InputStream is = new FileInputStream("/");
        InputStream inputStream = new BufferedInputStream(is);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
    }

    /**
     * 要包装的接口
     */
    public interface Component {
        void operate();
    }

    public static class ConcreteComponent implements Component{
        @Override
        public void operate() {
            System.out.println("具体的操作");
        }
    }

    /**
     * 扩展的包装类
     */
    public static class Decorator implements Component {
        // 被包装的类
        private Component component;

        public Decorator(Component component) {
            this.component = component;
        }

        @Override
        public void operate() {
            System.out.println("包装类A的逻辑");
            component.operate();
            System.out.println("包装类A的逻辑");
        }
    }

    public static class DecoratorB implements Component {
        // 被包装的类
        private Component component;

        public DecoratorB(Component component) {
            this.component = component;
        }

        @Override
        public void operate() {
            System.out.println("包装类B的逻辑");
            component.operate();
            System.out.println("包装类B的逻辑");
        }
    }
}
