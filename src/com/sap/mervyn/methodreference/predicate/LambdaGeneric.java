package com.sap.mervyn.methodreference.predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class LambdaGeneric {

    public static <T> List<T> filter(List<T> inventory, Predicate<T> p) {
        List<T> result = new ArrayList<>();

        inventory.stream().forEach(apple -> {
            if (p.test(apple)) {
                result.add(apple);
            }
        });

        return result;
    }

}
