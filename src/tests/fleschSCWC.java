package tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class fleschSCWC {
    public static void main(String[] args) throws FileNotFoundException {


        File file;
        file = new File("src/inputData/warAndPeace.txt");

        Scanner scanner = new Scanner(file);

        //        String novel="";
        var novel = new StringBuilder();

        long startTime = System.currentTimeMillis();
        while (scanner.hasNext()) {
            novel.append(scanner.nextLine());
        }
        System.out.println(novel.length());
        System.out.println(System.currentTimeMillis() - startTime);


//        String words="When one you get a stream from the list, it doesn't modify the list. If you want to get the size of the stream after the filtering";
        String words = novel.toString();
        String syllables = "[aeiouyAEIOUY]+";

        Pattern pattern = Pattern.compile(syllables);

        Matcher matcher = pattern.matcher(words);
        int count = 0;

        while (matcher.find()) {
            count++;
        }

        String[] strs = words.split(",\\s*|\\s|\\.\\s*");
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

        int syllablesNum = count - (int) silent;
        System.out.println("found " + syllablesNum + " syllables");


        System.out.println("found " + Arrays.stream(strs).count() + " words");

        System.out.println("found " + words.split("!|\\.|\\?").length + " sentences");

    }
}
