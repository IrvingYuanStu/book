package com.arch.pattern.mediator;

public class MediatorDemo {
    public static void main(String[] args) {

        ConcreteMediator mediator = new ConcreteMediator();
        ModuleA moduleA = new ModuleA();
        mediator.setModuleA(moduleA);
        mediator.setModuleB(new ModuleB());
        mediator.setModuleC(new ModuleC());

        moduleA.setMediator(mediator);

        moduleA.someMethod();
    }

    /**
     * 具体的中介者，注册管理了模块/模块调用关系
     */
    public static class ConcreteMediator {
        /**
         * 需要中介的业务模块
         */
        private ModuleA moduleA;
        private ModuleB moduleB;
        private ModuleC moduleC;

        /**
         * 指定A模块需要的调用逻辑
         */
        public void moduleAExecuteOthers() {
            // 把A要调用哪些应用的关系，逻辑封装，相当于MQ的解耦功能
            // 可以把function理解成通知一些消息给其他模块，如果需要新增一个模块，再这里改就行了，模块A的业务不受这个影响。
            moduleB.function();
            moduleC.function();
        }

        public void moduleBExecuteOthers() {
            moduleA.function();
            moduleC.function();
        }

        public void moduleCExecuteOthers() {
            moduleB.function();
            moduleA.function();
        }

        public void setModuleA(ModuleA moduleA) {
            this.moduleA = moduleA;
        }

        public void setModuleB(ModuleB moduleB) {
            this.moduleB = moduleB;
        }

        public void setModuleC(ModuleC moduleC) {
            this.moduleC = moduleC;
        }
    }

    /**
     * 三个业务模块
     */
    public static class ModuleA {
        private ConcreteMediator mediator;

        public void someMethod() {
            // 执行业务逻辑
            System.out.println("一些A的业务逻辑");
            // 通知、调用其他模块自己完成了
            mediator.moduleAExecuteOthers();
        }

        // 用于被别人调用，相当于MQ的消费
        public void function() {
            System.out.println("模块A执行了");
        }

        public void setMediator(ConcreteMediator mediator) {
            this.mediator = mediator;
        }
    }

    public static class ModuleB {
        public void function() {
            System.out.println("模块b执行了");
        }
    }

    public static class ModuleC {
        public void function() {
            System.out.println("模块c执行了");
        }
    }
}
