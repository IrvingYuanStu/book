package com.arch.partten.visitor;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class VisitorDemo {
    public static void main(String[] args) {
        List<Wheel> wheels = new ArrayList<>(4);
        for (int i=0; i<4; i++) {
            Wheel wheel = new Wheel();
            wheel.setPosition(i);
            wheel.setTypeCode("马牌轮胎-A101");
            wheel.setSize(60);
            wheels.add(wheel);
        }

        Car car = new Car();
        car.setBind("宾利");
        car.setId("沪A88888");
        car.setWheels(wheels);

        System.out.println("输出车辆信息");
        car.accept(new CatInfoVisitor());

        System.out.println("\n启动汽车");
        car.accept(new CarRunVisitor());
    }

    /**
     * 访问者接口
     */
    public interface Visitor {
        // 访问者包含的功能，可自定义
        void visitCar(Car car);
        void visitWheel(Wheel wheel);
    }

    /**
     * 输出汽车信息的visitor
     */
    public static class CatInfoVisitor implements Visitor {
        @Override
        public void visitCar(Car car) {
            StringBuilder builder = new StringBuilder();
            builder.append("汽车品牌：" + car.getBind() + "\n");
            builder.append("车牌号:" + car.getId() + "\n");
            System.out.println(builder.toString());
        }

        @Override
        public void visitWheel(Wheel wheel) {
            StringBuilder builder = new StringBuilder();
            builder.append("轮胎位置:" + wheel.getPosition() + ", 型号:" + wheel.getTypeCode() + ", 尺寸:" + wheel.getSize());
            System.out.println(builder.toString());
        }
    }

    public static class CarRunVisitor implements Visitor {
        @Override
        public void visitCar(Car car) {
            System.out.println(car.getId() + "启动了！");
        }

        @Override
        public void visitWheel(Wheel wheel) {
            System.out.println("[" + wheel.getPosition() + "]号轮胎,正在运转！");
        }
    }

    /**
     * 需要被访问的类，提供一个接受访问的接口
     */
    public static abstract class Element {
        public abstract void accept(Visitor visitor);
    }

    @Data
    public static class Car extends Element{
        private String bind;
        private String id;
        private List<Wheel> wheels;

        @Override
        public void accept(Visitor visitor) {
            // 调用具体的访问者
            visitor.visitCar(this);
            if (null != wheels) {
                for (Wheel wheel : wheels) {
                    visitor.visitWheel(wheel);
                }
            }
        }
    }

    @Data
    public static class Wheel extends Element{
        private int position;
        private String typeCode;
        private int size;

        @Override
        public void accept(Visitor visitor) {
            visitor.visitWheel(this);
        }
    }
}
