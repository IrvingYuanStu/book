package com.arch.pattern.iterator;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class WithoutIteratorPattern {

    public static void main(String[] args) {
        // 测试数据
//        Employee employee1 = new Employee("张三");
//        Employee employee2 = new Employee("李四");
//        Employee[] employees = new Employee[2];
//        employees[0] = employee1;
//        employees[1] = employee2;
//        Department department = new Department();
//        department.setEmployees(employees);
//
//        Employee[] emps = department.getEmployees();
//        for (Employee emp: emps) {
//            System.out.println(emp.toString());
//        }

        Employee employee1 = new Employee("张三");
        Employee employee2 = new Employee("李四");
        Employee[] employees = new Employee[2];
        List<Employee> list = new ArrayList<>();
        list.add(employee1);
        list.add(employee2);
        Department department = new Department();
        department.setEmployees(list);

        // 如果底层的数据结构发生变化，上层需要该声明的类型，甚至修改遍历数据的方式
        List<Employee> emps = department.getEmployees();
        for (Employee emp: emps) {
            System.out.println(emp.toString());
        }
    }

    @Data
    @AllArgsConstructor
    static class Employee {
        private String name;
    }

    @Data
    static class Department {
        // 原先的使用数组
//    private Employee[] employees;

        // 如果修改了底层的集合类型
        private List<Employee> employees;
    }
}

