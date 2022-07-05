package com.arch.pattern.observable;

import java.util.Observable;
import java.util.Observer;

public class ObsevableDemo {
    public static void main(String[] args) {
        Order order = new Order();
        order.setId(1);
        order.setState(1);

        order.addObserver(new OrderObserver());
        order.setState(2);
        order.stateChanged();

        order.setState(3);
        order.stateChanged();
    }

    public static class OrderObserver implements Observer{
        @Override
        public void update(Observable o, Object arg) {
            Order order = (Order) o;
            System.out.println("order is changed, id=" + order.getId() + ", state=" + order.getState());
        }
    }

    public static class Order extends Observable {
        private int state = 0;
        private long id;

        public void stateChanged() {
            setChanged();
            notifyObservers();// 拉模式
//            notifyObservers(this.state); // 推模式
            clearChanged();
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }
    }
}
