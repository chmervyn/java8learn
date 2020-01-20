package com.sap.mervyn.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class StreamTest {

    private static List<Dish> menu = DishUtil.getMenu();

    public static void main(String[] args) {

        List<String> lowCaloricDishesName = menu.stream().filter(d -> d.getCalories() < 400)
                                   .sorted(Comparator.comparing(Dish::getCalories))
                                   .map(Dish::getName)
                                   .collect(toList());

        System.out.println(lowCaloricDishesName);

        //Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(groupingBy(Dish::getType));

        List<String> threeHighCalaricDishNames = menu.stream()
                        .filter(d -> d.getCalories() > 300)
                        .map(Dish::getName)
                        .limit(3)
                        .collect(toList());
        System.out.println(threeHighCalaricDishNames);

        menu.stream().forEach(System.out::println);

        // test stream API: distinct
        testDistinct();

        // test stream API: limit and skip
        System.out.println("######## test limit and skip ###########");
        testLimitAndSkip();

        // test stream API: map
        testMap();

        // test stream API: flatMap
        System.out.println("##### test flatMap #####");
        testFlatMap();

        // test stream API: anyMatch, allMatch, noneMatch
        System.out.println("##### test anyMatch, allMatch, noneMatch #####");
        testMatch();

        // test stream API: findAny, findFirst
        System.out.println("##### test findAny, findFirst #####");
        testFind();

        // test stream API: reduce
        System.out.println("##### test reduce #####");
        testReduce();

    }

    private static void testReduce() {
        List<Integer> numbers = Arrays.asList(4, 5, 3, 9);

        System.out.println("##### provide initial value");
        Integer result1 = numbers.stream().reduce(0, Integer::sum);
        System.out.println(result1);

        System.out.println("##### does not provide initial value");
        Optional<Integer> result2 = numbers.stream().reduce(Integer::sum);
        System.out.println(result2.get());

        System.out.println("##### get the max value");
        Optional<Integer> result3 = numbers.stream().reduce(Integer::max);
        System.out.println(result3.get());

        System.out.println("##### get the min value");
        Optional<Integer> result4 = numbers.stream().reduce(Integer::min);
        System.out.println(result4.get());

        System.out.println("##### self test");
        Optional<Integer> selfTestResult = menu.stream().map(d -> 1).reduce(Integer::sum);
        System.out.println(selfTestResult.get());
        System.out.println(menu.stream().count());
    }

    private static void testFind() {
        System.out.println("##### findAny");
        menu.stream().filter(Dish::isVegetarian)
                .findAny().ifPresent(d -> System.out.println(d.getName()));

        System.out.println("##### findFirst");
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstSquareDivisibleByThree = someNumbers.stream().map(x -> x * x)
                .filter(x -> x % 3 == 0)
                .findFirst();
        System.out.println(firstSquareDivisibleByThree.get());

    }

    private static void testMatch() {
        if (menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is (somewhat) vegetarian friendly!!!");
        }

        if (menu.stream().allMatch(d -> d.getCalories() < 1000)) {
            System.out.println("allMatch: The menu is healthy");
        } else {
            System.out.println("allMatch: The menu is not healthy");
        }

        if (menu.stream().noneMatch(d -> d.getCalories() >= 1000)) {
            System.out.println("noneMatch: The menu is healthy");
        } else {
            System.out.println("noneMatch: The menu is not healthy");
        }
    }

    private static void testFlatMap() {
        List<String> words = Arrays.asList(
                "Hello",
                "World"
        );

        System.out.println("##### before flatMap #####");
        words.stream().map(word -> word.split(""))
                .distinct()
                .collect(toList())
                .stream().forEach(arr -> System.out.println(Arrays.toString(arr)));

        System.out.println("##### using flatMap #####");
        List<String> result = words.stream().map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());

        System.out.println(result);

        System.out.println("##### self test1 #####");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> intResult = numbers.stream()
                .map(n -> n * n)
                .collect(toList());
        System.out.println(intResult);

        System.out.println("##### self test2 #####");
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);

        numbers1.stream().flatMap(i -> numbers2.stream().map(j -> new int[]{i, j}))
                .collect(toList())
                .stream().forEach(arr -> System.out.println(Arrays.toString(arr)));

        System.out.println("only if i + j % 3 == 0, display the int[] object");
        numbers1.stream().flatMap(i -> numbers2.stream().filter(j -> (i + j) % 3 ==0).map(j -> new int[]{i, j}))
                .collect(toList())
                .stream().forEach(arr -> System.out.println(Arrays.toString(arr)));


    }

    private static void testMap() {
        List<Integer> result = menu.stream().map(Dish::getName)
                .map(String::length)
                .collect(toList());

        System.out.println(result);
    }

    private static void testLimitAndSkip() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        System.out.println("##### limit first, skip next #####");
        numbers.stream().limit(3).skip(2).forEach(System.out::println);

        System.out.println("##### skip first, limit next #####");
        numbers.stream().skip(2).limit(3).forEach(System.out::println);
    }

    private static void testDistinct() {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);

    }

}
