package com.arch.partten.state;

public class StateDemo {

    public static void main(String[] args) {
        Context alarmContext = new Context();
        alarmContext.setCurrentState(Context.todo);

        alarmContext.getCurrentState().claim(1);
        alarmContext.getCurrentState().fix(1);
        alarmContext.getCurrentState().claim(1);
    }

    /**
     * 状态接口，封装所有对状态的操作
     */
    public interface State {
        // 认领告警
        void claim(Integer alarmId);
        // 解决
        void fix(Integer alarmId);
    }

    public static class TodoState extends AbstractState{

        @Override
        public void claim(Integer alarmId) {
            System.out.println("认领" + alarmId + "告警事件");
            // 更新上下文中的当前状态
            super.context.setCurrentState(new HandleState());
        }

        @Override
        public void fix(Integer alarmId) {
            System.out.println("尚未认领，不可标记修复");
        }
    }

    public static class HandleState extends AbstractState{
        @Override
        public void claim(Integer alarmId) {
            System.out.println("事件"+ alarmId +", 已经处理中，不需要重复认领");
        }

        @Override
        public void fix(Integer alarmId) {
            System.out.println("修复" + alarmId + "告警事件的问题");
        }
    }

    public static class FixState extends AbstractState {

        @Override
        public void claim(Integer alarmId) {
            System.out.println("已修复该问题，不用在认领了");
        }

        @Override
        public void fix(Integer alarmId) {
            System.out.println("已修复该问题，不用再标记修复了");
        }
    }

    /**
     * 可以有一个上下文
     * 但是solaris中数据库保存了当前状态，所以不需要上下文
     */
    public static class Context {
        public static AbstractState todo = new TodoState();
        public static AbstractState handle = new HandleState();
        public static AbstractState fix = new FixState();

        // 当前状态
        private State currentState;

        public Context() {
            todo.setContext(this);
            handle.setContext(this);
            fix.setContext(this);
        }

        // 获取/更新当前状态
        public State getCurrentState() {
            return currentState;
        }
        public void setCurrentState(State currentState) {
            this.currentState = currentState;
        }
    }

    /**
     * 使用上下文就要用
     */
    public static abstract class AbstractState implements State {
        protected Context context;

        public void setContext(Context context) {
            this.context = context;
        }
    }
}
