package tests;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class stringSplit {
    public static void main(String[] args) {
        String words = "When one you get a stream from the list, it doesn't modify the list. If you want to get the size of the stream after the filtering";

        String[] strs = words.split("\\.");

        Arrays.stream(strs).forEach(System.out::println);

        String one = " You can also Save&Share with the Community,an";


        strs = one.split(" |,");

        //   Arrays.stream(strs).forEach(System.out::println);

        Pattern p = Pattern.compile("[aeiouyAEIOUY]+[^$e(,.:;!?)]");
        Matcher m = p.matcher(words);

        int syllables = 0;
        while (m.find()) {
            syllables++;
            System.out.println(m.group());
        }
        System.out.println(syllables);


    }
}
