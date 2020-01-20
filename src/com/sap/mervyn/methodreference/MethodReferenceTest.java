package com.sap.mervyn.methodreference;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.function.Predicate;

public class MethodReferenceTest {

    public static void main(String[] args) {
        normal();
        System.out.println("############################");
        java8();

        Predicate<String> predicate = (s) -> "apple".equalsIgnoreCase(s);
        System.out.println(predicate.test("good"));
        System.out.println(predicate.test("apple"));
    }

    private static void normal() {

        File rootDir = new File(System.getProperty("user.dir"));

        File[] hiddenFiles = rootDir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isHidden();
            }
        });

        for (File hiddenFile : hiddenFiles) {
            System.out.println(hiddenFile.getName());
        }

    }

    private static void java8() {
        File rootDir = new File(System.getProperty("user.dir"));

        //final File[] hiddenFiles = rootDir.listFiles(pathname -> pathname.isHidden());
        final File[] hiddenFiles = rootDir.listFiles(File::isHidden);


        Arrays.stream(hiddenFiles).forEach(hiddenFile -> System.out.println(hiddenFile.getName()));
    }

}
