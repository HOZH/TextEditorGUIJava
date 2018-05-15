package tests;

import java.util.Arrays;

public class wordCount2 {
    public static void main(String[] args) {
        String words = " once  upon the time";
        String[] strs = words.trim().split(",\\s*|\\s+|\\.\\s*");

        Arrays.stream(strs).forEach(System.out::println);

        System.out.println(strs.length);
    }
}
