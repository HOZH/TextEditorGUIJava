package tests;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class rex {
    public static void main(String[] args) {

        String word = "When one you get a stream from the list, it doesn't modify the list. If you want to get the size of the stream after the filtering";
//                "When you get stream from the list";
        String syllables = "[aeiouyAEIOUY]+";

        Pattern pattern = Pattern.compile(syllables);

        Matcher matcher = pattern.matcher(word);

        int counter = 0;
        while (matcher.find()) {
            System.out.println(matcher.group());
            counter++;
        }

        String[] strs = word.split(",\\s*|\\s|\\.\\s*");
        //strs=word.split("\\t|,|;|\\.|\\?|!|-|:|@|\\[|\\]|\\(|\\)|\\{|\\}|_|\\*|/");
        System.out.println(strs.length);
        System.out.println();

//        Arrays.stream(strs).filter(x ->
//                (x.charAt(x.length() - 1) == 'e'//&&x.charAt(x.length()-2)!='l'
//                )
////                ||(
////                        x.charAt(x.length()-2)=='e'&&(x.charAt(x.length()-1)=='d'||x.charAt(x.length()-1)=='s')
////                )
//        ).forEach(System.out::println);
        long silent = Arrays.stream(strs).map(x -> {
            if (x.length() >= 3
                    &&
                    x.substring(0, x.length() - 2).matches("[AEIOUaeiouyY]+")
                    && ((x.endsWith("e") && x.charAt(x.length() - 2) != 'l') || x.endsWith("es") || x.endsWith("ed"))) {
                System.out.println(x);
                return null;

            }
            return x;
        }).filter(x -> x == null).count();

        //Arrays.stream(strs).forEach(System.out::println);
        System.out.println(silent);
        System.out.println(counter);
    }
}
