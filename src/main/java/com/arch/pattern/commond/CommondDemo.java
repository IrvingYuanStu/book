package com.arch.pattern.commond;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/**
 * 命令模式demo
 */
public class CommondDemo {
    public static void main(String[] args) {
        // 两个命令的实现
        Predicate<Apple> colorPredicate = new Predicate<Apple>() {
            @Override
            public boolean test(Apple apple) {
                if (apple.color.equals("green")) {
                    return true;
                }
                return false;
            }
        };

        Predicate<Apple> weightPredicate = new Predicate<Apple>() {
            @Override
            public boolean test(Apple apple) {
                if (apple.weight > 100) {
                    return true;
                }
                return false;
            }
        };

        List<Apple> list = new ArrayList<>();
        // 调用commond，代码复用性更好和扩展性好,且逻辑清晰，可读性强
        filterApples(list, colorPredicate);
        filterApples(list, weightPredicate);
    }

    /**
     * 这里就封装对命令的调用, 不同的命令实现不同的逻辑
     * @param list
     * @param predicate
     */
    public static void filterApples(List<Apple> list, Predicate<Apple> predicate) {
        Iterator<Apple> iterator = list.iterator();
        while (iterator.hasNext()) {
            Apple apple = iterator.next();
            if (!predicate.test(apple)) {
                iterator.remove();
            }
        }
    }

    public static class Apple {
        private String color;
        private Integer weight;
    }
}
