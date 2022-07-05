package com.arch.partten.fsm;

/**
 * 状态，封装下的操作
 */
public interface State {

    // 每帧的更新逻辑
    void update();
}
