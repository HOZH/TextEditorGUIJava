package tests;

import java.util.Arrays;

public class wordCount2 {
    public static void main(String[] args) {
        var bString = "is that in a considerable part of his“ lands ”3 In my opinion is mysterious especially in peace with each ";

        String aString = "\"it's a great day\"";
        String words = "How could manage huffed and wondered what all away!  \"Watch me he gasped? Ah no Frog He could\" ";
        String[] strs = bString.trim().split(",\\s*|\\s+|\\.\\s*|!\\s*|\\?\\s*|\"\\s*|”\\s*|“\\s*");


        Arrays.stream(strs).forEach(System.out::println);

        System.out.println(strs.length);
    }
}
