package com.arch.partten.fsm;

public class FindState implements State{
    @Override
    public void update() {
        System.out.println("找到敌人了！");
    }
}
