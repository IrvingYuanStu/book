package com.arch.pattern.factory;

public class SimpleFactoryDemo {
    public static void main(String[] args) {
        // 生成逻辑封装在factory里，上层是隔离的
        Product product = SimpleFactory.createProduct();
        product.function();
    }
}

/**
 * 简单工厂类
 */
class SimpleFactory {
    /**
     * 可以加入参，根据参数来控制生成的对象
     */
    public static Product createProduct() {
        // 可以存在其他生成前的逻辑
        return new ProductA();
        // 如果改成B实现，也很方便
//        return new ProductB();
    }
}

interface Product {
    void function();
}

class ProductA implements Product{
    @Override
    public void function() {
        System.out.println("a的逻辑");
    }
}

class ProductB implements Product{
    @Override
    public void function() {
        System.out.println("b的逻辑");
    }
}
