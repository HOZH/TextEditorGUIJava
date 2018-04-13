package tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class fileLoad {
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
        //System.out.println(novel);

    }
}
