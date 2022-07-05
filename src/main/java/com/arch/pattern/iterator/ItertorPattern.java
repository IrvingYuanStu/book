package com.arch.pattern.iterator;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class ItertorPattern {

    public static void main(String[] args) {
        Department department = new Department();
        List<Employee> employees = new ArrayList<>();
        Employee e1 = new Employee("张三");
        Employee e2 = new Employee("李四");
        employees.add(e1);
        employees.add(e2);
        department.setEmployees(employees);

        /**
         * 调用者面向iterator，与底层实现隔离
         * 底层修改集合实现，也不需要改动调用方逻辑
         */
        Iterator<Employee> employeeIterator = department.iterator();
        while (employeeIterator.hasNext()) {
            Employee employee = employeeIterator.next();
            System.out.println(employee);
        }
    }


    /**
     * 迭代器
     * @param <T>
     */
    public interface Iterator<T>  {
        // 获取下一个
        public T next();

        // 是否还有下一个
        public boolean hasNext();
    }

    /**
     * 表示一个可以被迭代的类
     * @param <T>
     */
    public interface Iterable<T> {
        // 获取iterator
        public Iterator<T> iterator();
    }

    /**
     * 迭代器
     */
    public static class DepartmentIterator implements Iterator<Employee> {
        // 被迭代的集合
        private Department department;
        private int length;

        /**
         * 传入要被迭代的集合
         * @param department
         */
        public DepartmentIterator(Department department) {
            this.department = department;
            this.length = 0;
        }

        /**
         * 获取下一个元素
         * @return
         */
        @Override
        public Employee next() {
            Employee employee = department.getEmployees().get(length);
            length++;
            return employee;
        }

        /**
         * 判断是否还有下一个
         * @return
         */
        @Override
        public boolean hasNext() {
            return length < department.getEmployees().size();
        }
    }

    @Data
    @AllArgsConstructor
    public static class Employee {
        private String name;
    }

    @Data
    public static class Department implements Iterable<Employee> {
        // 要迭代的集合
//        private Employee[] employees;
        private List<Employee> employees;

        @Override
        public Iterator<Employee> iterator() {
            return new DepartmentIterator(this);
        }
    }

}

