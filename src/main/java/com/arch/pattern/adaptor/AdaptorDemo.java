package com.arch.pattern.adaptor;

import lombok.Data;

public class AdaptorDemo {

    public static void main(String[] args) {
        /**
         * 代码都通过适配，把接口版本统一起来便于维护管理
         */
        UserService_v2 userService_v2 = new AbastractUserServiceAdaptor(new UserServiceImpl_v1());
        userService_v2.saveUser(new User("张三"));

        UserService_v2 userServiceV2 = new UserServiceImpl_v2();
        userServiceV2.saveUser(new User("lisi"));
    }

    /**
     * 把UserService_v1适配到UserService_v2上
     */
    public static class AbastractUserServiceAdaptor implements UserService_v2{
        private UserService_v1 userServiceV1;

        public AbastractUserServiceAdaptor(UserService_v1 userServiceV1) {
            this.userServiceV1 = userServiceV1;
        }

        @Override
        public boolean saveUser(User user) {
            userServiceV1.saveUser(user.getName(), user.getId());
            return true;
        }
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

    public static class UserServiceImpl_v1 implements UserService_v1 {
        @Override
        public void saveUser(String name, Long id) {
            System.out.printf("保存用户%s，成功\n", name );
        }
    }

    public static class UserServiceImpl_v2 implements UserService_v2 {
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
