package tests;

import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class lambdaTest {
    public static void main(String[] args) {
        var words = "once upon the time age";
        String[] strs = words.trim().split(",\\s*|\\s+|\\.\\s*");

        String syllables = "[aeiouyAEIOUY]+";

        Pattern pattern = Pattern.compile(syllables);

        Matcher matcher = pattern.matcher(words);
        int count = 0;

        while (matcher.find()) {
            count++;
        }
        System.out.println(count);




        var num2=0;
        var ints = new int[1];


//                Arrays.stream(strs).map(x-> {
//                    var y=x;
//                    var z=x;
//                    var num1=0;
//
//
//
//
//                    Matcher matcher1=pattern.matcher(x);
//                    while(matcher.find()){
//                        num1++;
//
//                    }
//                    ints[1]=ints[1]+num1;
//
//                });
        String syllables1 = "[aeiouyAEIOUY]+";

        Pattern pattern1 = Pattern.compile(syllables);


        long silent =
                Arrays.stream(strs).map(x-> {
                    var num1=0;


                    Matcher matcher1 = pattern.matcher(x);




                    while(matcher1.find()){
//                        System.out.println("y");
                        num1++;

                    }
//                    System.out.println(num1);
                    ints[1]=ints[1]+num1;
//                    System.out.println(ints[1]);




            if (x.length() >= 3
                    &&
                    x.substring(0, x.length() - 2).matches("[AEIOUaeiouyY]+")
                    && ((x.endsWith("e") && x.charAt(x.length() - 2) != 'l') || x.endsWith("es") || x.endsWith("ed"))) {
                //   System.out.println(x);
                var y = x;
                return null;

            }
            return x;
        }).filter(Objects::isNull).count();


        System.out.println(silent);
        System.out.println(ints[1]);
    }
}
