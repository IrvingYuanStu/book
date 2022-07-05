package com.arch.pattern.prototype;

public class PrototypeDemo {

    private String name;
    private String id;

    /**
     * 把复制的方法都封装在clone中
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        // 此处时浅拷贝，深拷贝可能需要BeanCopier等工具
        PrototypeDemo demo = new PrototypeDemo();
        demo.id = id;
        demo.name = name;
        return demo;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        PrototypeDemo proto1 = new PrototypeDemo();
        proto1.name = "abc";

        PrototypeDemo protoClone = (PrototypeDemo) proto1.clone();
        System.out.println(protoClone.name);
        System.out.println(protoClone.equals(proto1));
    }
}
