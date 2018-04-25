package tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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


        String txt = "once upon the time there is a man whose name is mike he is a male lalala";
        HashSet spellErrors = new HashSet<>();
        String[] words = txt.split(",\\s*|\\s|\\.\\s*");

var errorLocations = new ArrayList<Integer>();
        for (int i = 0; i < words.length; i++) {
            if (map.get(words[i]) == null) {
             //   System.out.println(words[i]);
                errorLocations.add(i);
                spellErrors.add(words[i]);
            }

        }
spellErrors.stream().forEach(System.out::println);
        errorLocations.stream().forEach(System.out::println);
        var errorsLocationsArr =errorLocations.toArray();
        System.out.println(errorsLocationsArr[0]);
//        Predicate<String> keyTerm =x->x.contains(spellErrors) // fixme
//                x->x.length()==4;


    }
}
