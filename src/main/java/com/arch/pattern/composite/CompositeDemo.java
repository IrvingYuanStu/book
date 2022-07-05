package com.arch.pattern.composite;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CompositeDemo {

    public static void main(String[] args) {
        Component component = new Branch();
        component.display();
    }

    // 统一的组件接口
    public interface Component {
        // 删除节点
        void display();

        // 获取子节点
        List<Component> getChildren();
    }

    /**
     * 叶子类组件实现
     */
    public static class Leaf implements Component {
        @Override
        public void display () {
            // 删除自己
            System.out.println(this.hashCode());
        }

        @Override
        public List<Component> getChildren() {
            return Collections.EMPTY_LIST;
        }
    }

    public static class Branch implements Component {
        private List<Component> components;

        @Override
        public void display () {
            Iterator<Component> componentIterator = components.iterator();
            while (componentIterator.hasNext()) {
                Component child = componentIterator.next();
                child.display();
            }
        }

        @Override
        public List<Component> getChildren() {
            return this.components;
        }
    }
}
