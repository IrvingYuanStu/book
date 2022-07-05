package com.arch.partten.fsm;

public class AttackState implements State{
    @Override
    public void update() {
        System.out.println("攻击敌人，扣血，敌人挂了!");
    }
}
