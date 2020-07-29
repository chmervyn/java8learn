package com.sap.mervyn.methodreference.predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class AppleUtils {

    public static boolean isGreenApple(Apple apple) {
        return "green".equalsIgnoreCase(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        final List<Apple> result = new ArrayList<>();

        inventory.stream().forEach(apple -> {
            if (p.test(apple)) {
                result.add(apple);
            }
        });

        return result;
    }

    public static void main(String[] args) {
        List<Apple> inventory = new ArrayList<>();
        Apple apple0 = new Apple(1, "red", 20);
        Apple apple1 = new Apple(2, "yellow", 1700);
        Apple apple2 = new Apple(3, "green", 149);
        Apple apple3 = new Apple(4, "red", 210);
        Apple apple4 = new Apple(5, "green", 151);
        Apple apple5 = new Apple(6, "red", 990);
        inventory.add(apple0);
        inventory.add(apple1);
        inventory.add(apple2);
        inventory.add(apple3);
        inventory.add(apple4);
        inventory.add(apple5);

        List<Apple> result = filterApples(filterApples(inventory, AppleUtils::isGreenApple), AppleUtils::isHeavyApple);
        result.stream().forEach(apple -> System.out.println(apple));
        System.out.println("=====================================");
        result = filterApples(inventory, (Apple apple) -> "green".equalsIgnoreCase(apple.getColor()));
        result.stream().forEach(apple -> System.out.println(apple));
        System.out.println("=====================================");
        result = filterApples(inventory, (Apple apple) -> "red".equalsIgnoreCase(apple.getColor()) && 150 < apple.getWeight());
        result.stream().forEach(apple -> System.out.println(apple));
        System.out.println("=====================================");

        result = LambdaGeneric.filter(inventory, (Apple apple) -> "red".equalsIgnoreCase(apple.getColor()) && 150 < apple.getWeight());
        result.stream().forEach(apple -> System.out.println(apple));
    }
}
