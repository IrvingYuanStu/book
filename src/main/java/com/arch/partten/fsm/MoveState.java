package com.arch.partten.fsm;

public class MoveState implements State{
    @Override
    public void update() {
        System.out.println("更新坐标，向敌人移动中");
    }
}
