package tests;

import java.util.Arrays;

public class filterKey {
    public static void main(String[] args) {
        String fKey = "@#$%^&*()_-+=";

        String[] fs = fKey.split("");
        System.out.println(fs.length);
        Arrays.stream(fs).forEach(System.out::println);
    }
}
