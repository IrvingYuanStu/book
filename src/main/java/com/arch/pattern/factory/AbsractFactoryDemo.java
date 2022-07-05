package com.arch.pattern.factory;

/**
 * 抽象工厂
 */
public class AbsractFactoryDemo {

    public static void main(String[] args) {
        // mac工厂只生产mac固定组合的
        Factory macFactory = new Factory1();
        macFactory.createComputer();
        macFactory.createKeyboard();
    }

    /**
     * 抽象工厂接口
     * 定义产品组合的元素个数
     */
    interface Factory {
        Computer createComputer();
        Keyboard createKeyboard();
    }

    /**
     * 具体工厂，生成、限定具体的产品
     */
    static class Factory1 implements Factory {
        @Override
        public Computer createComputer() {
            return new MacBook();
        }

        @Override
        public Keyboard createKeyboard() {
            return new Ikeyboard();
        }
    }

    static class Factory2 implements Factory {
        @Override
        public Computer createComputer() {
            /**
             * 如果组合2改了，不是thinkpad+hhkb了，直接改工厂这里即可
             * 对于调用者，还是只用createComputer/createKayboard
             */
            return new ThinkPad();
        }

        @Override
        public Keyboard createKeyboard() {
            return new Hhkb();
        }
    }

    interface Computer {
    }

    interface Keyboard {
    }

    static class MacBook implements Computer {
    }

    static class ThinkPad implements Computer {
    }

    static class Hhkb implements Keyboard {
    }

    static class Ikeyboard implements Keyboard {
    }
}

