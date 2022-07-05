package com.arch.partten.memento;

public class MementoDemo {
    public static void main(String[] args) {
        Originator originator = new Originator();
        // 初始化状态
        originator.setState(1);

        // 用当前状态1创建一个备忘录
        Memento memento = originator.createMemento();
        Caretaker caretaker = new Caretaker();
        caretaker.save(memento);

        // 更新状态
        originator.setState(2);
        originator.setState(3);

        // 现在想恢复上个状态
        Memento memento1 = caretaker.restore();
        originator.restoreFromMemento(memento1);
    }

    /**
     * 操作业务数据的类
     */
    public static class Originator {
        // 有一个state, 要被备忘录管理
        private int state;

        // 把属性创建到备忘录里保存
        public Memento createMemento() {
            System.out.println("根据当前状态创建一个备忘录");
            return new Memento(state);
        }

        // 从备忘录把数据回复
        public void restoreFromMemento(Memento memento) {
            System.out.println("从备忘录恢复数据");
            int state = memento.getState();
            this.setState(state);
        }

        // state的get/set方法
        public int getState() {
            return state;
        }
        public void setState(int state) {
            this.state = state;
            System.out.printf("更新state=%d \n", state);
        }
    }

    public static class Memento {
        // 备忘录管理的属性
        private int state;

        public Memento(int state) {
            this.state = state;
        }

        public int getState() {
            return this.state;
        }
    }

    /**
     * Memento的管理容器吧，可以用享元来做，核心是上面的思想
     */
    public static class Caretaker {
        private Memento memento;

        private void save(Memento memento) {
            this.memento = memento;
        }

        private Memento restore() {
            return this.memento;
        }
    }
}
