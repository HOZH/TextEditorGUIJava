package tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.function.Predicate;

public class dic {
    public static void main(String[] args) throws FileNotFoundException {
        var map = new HashMap<>();

        var file = new File("src/dictionary.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            map.put(scanner.next(), "");
        }


        String txt = "lakfjlkfj one two hslfdhlak";
        HashSet spellErrors = new HashSet<>();
        String[] words = txt.split(",\\s*|\\s|\\.\\s*");


        for (int i = 0; i < words.length; i++) {
            if (map.get(words[i]) == null) {
             //   System.out.println(words[i]);
                spellErrors.add(words[i]);
            }

        }
spellErrors.stream().forEach(System.out::println);
        Predicate<String> keyTerm =x->x.contains(spellErrors)
//                x->x.length()==4;


    }
}
