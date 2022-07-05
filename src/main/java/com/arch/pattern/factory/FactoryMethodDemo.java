package com.arch.pattern.factory;

/**
 * 工厂方法
 */
public class FactoryMethodDemo {

    public static void main(String[] args) {
        Factory factory = new ProductAFatory();
        Product productA = factory.createProduct();
        productA.function();

        Factory factoryB = new ProductBFactory();
        Product productB = factoryB.createProduct();
        productB.function();
    }
}

/**
 * 抽象一个工厂接口
 * 具体创建逻辑在每个实现工厂中
 */
interface Factory {
    Product createProduct();
}

/**
 * 还可以整合模板方法模式
 */
abstract class AbstractFactory implements Factory {
    @Override
    public Product createProduct() {
        System.out.println("一些公共逻辑");
        // 执行特殊逻辑，创建对象
        return specialCreate();
    }

    protected abstract Product specialCreate();
}

/**
 * 创建productA的工厂
 */
class ProductAFatory extends AbstractFactory {
    @Override
    protected Product specialCreate() {
        return new ProductA();
    }
}

/**
 * 创建productB的工厂
 */
class ProductBFactory extends AbstractFactory {
    @Override
    protected Product specialCreate() {
        return new ProductB();
    }
}