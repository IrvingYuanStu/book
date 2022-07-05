package com.arch.partten.fsm;

/**
 * 空闲状态
 */
public class IdleState implements State{
    @Override
    public void update() {
        System.out.println("站着不动");
    }
}
