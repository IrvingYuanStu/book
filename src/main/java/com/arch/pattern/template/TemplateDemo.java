package com.arch.pattern.template;

public class TemplateDemo {
    public static void main(String[] args) {
        // 直接通过接口调用
        AbstractTemplate template1 = new ConcreteTemplate1();
        AbstractTemplate template2 = new ConcreteTemplate2();
        template1.execute();
        template2.execute();
    }

    /**
     * 模版方法接口
     */
    public interface Template {
        void step1();
        void step2();
        void step3();
    }

    public static abstract class AbstractTemplate implements Template {
        public void execute() {
            this.step1();
            this.step2();
            this.step3();
        }
    }

    public static class ConcreteTemplate1 extends AbstractTemplate {
        @Override
        public void step1() {
            System.out.println("模版1的步骤1");
        }

        @Override
        public void step2() {
            System.out.println("模版1的步骤2");
        }

        @Override
        public void step3() {
            System.out.println("模版1的步骤3");
        }
    }

    public static class ConcreteTemplate2 extends AbstractTemplate {
        @Override
        public void step1() {
            System.out.println("模版2的步骤1");
        }

        @Override
        public void step2() {
            System.out.println("模版2的步骤2");
        }

        @Override
        public void step3() {
            System.out.println("模版2的步骤2");
        }
    }
}
