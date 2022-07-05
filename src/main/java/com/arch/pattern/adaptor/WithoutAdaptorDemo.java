package com.arch.pattern.adaptor;

import lombok.Data;

public class WithoutAdaptorDemo {

    public static void main(String[] args) {
        /**
         * 系统里有两套UserService接口，调用者不知道该用哪个?
         * 写完以后也不知道为啥有时候用v1，有时候用v2
         */
        UserService_v1 userService_v1 = new UserServiceImpl_v1();
        userService_v1.saveUser("张三", 1000L);

        UserService_v2 userService_v2 = new UserServiceImpl_v2();
        userService_v2.saveUser(new User("李四"));
    }

    /**
     * 原先有一套已经暴露使用的接口
     */
    public interface UserService_v1 {
        public void saveUser(String name, Long id);
    }

    /**
     * 随着迭代，功能丰富，对第一个版本的接口优化
     * 如参数抽象成类，返回值类型也改变了（openAPI）
     */
    public interface UserService_v2 {
        public boolean saveUser(User user);
    }

    public static class UserServiceImpl_v1 implements UserService_v1{
        @Override
        public void saveUser(String name, Long id) {
            System.out.printf("保存用户%s，成功\n", name );
        }
    }

    public static class UserServiceImpl_v2 implements UserService_v2{
        @Override
        public boolean saveUser(User user) {
            System.out.printf("保存用户%s, 成功\n", user.getName());
            return true;
        }
    }
    @Data
    public static class User {
        private String name;
        private Long id;

        public User(String name) {
            this.name = name;
        }
    }
}
