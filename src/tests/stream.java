package tests;

import java.util.Arrays;
import java.util.function.Predicate;

public class stream {
    public static void main(String[] args) {
        int[] ints = {1, 2, 3};
        System.out.println(Arrays.stream(ints).filter(x -> x != 2).count());
        Arrays.stream(ints).filter(x -> x != 2).forEach(System.out::println);


        String str = "e ee le ed es adb";
        str = "When one you get a stream from the list, it doesn't modify the list. If you want to get the size of the stream after the filtering";
        Predicate<String> startWithJ = x -> x.startsWith("J");
        Predicate<String> length4 = x -> x.length() == 4;
        Predicate<String> notEndwithe = x -> !x.endsWith("e");
        Predicate<String> magicl = x -> x.charAt(x.length() - 2) != 'l';

        String[] strs = str.split(",\\s*|\\s|\\.\\s*");
        //fixme seems like filter can't work along with multi conditional statement
        //de morgan law not works here either

        //Arrays.stream(strs).filter(notEndwithe&&magicl).forEach(System.out::println);

        long count = Arrays.stream(strs).map(x -> {
            if (x.length() >= 3
                    &&
                    x.substring(0, x.length() - 2).matches("[AEIOUaeiouyY]+")
                    && ((x.endsWith("e") && x.charAt(x.length() - 2) != 'l') || x.endsWith("es") || x.endsWith("ed"))) {
                System.out.println(x);
                return null;

            }
            return x;
        }).filter(x -> x == null).count();

        System.out.println(count);

        String s = "abc";
        System.out.println(s);
        System.out.println(s.substring(0, s.length() - 2));


        Arrays.stream(strs).forEach(System.out::println);
    }
}
