package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class fileInputStream {
    public static void main(String[] args) throws IOException {

        FileInputStream inputStream = null;
        Scanner sc = null;
//        String line="";
        var stringBuilder = new StringBuilder();

        long startTime = System.currentTimeMillis();
        try {
            inputStream = new FileInputStream("src/inputData/warAndPeace.txt");
            sc = new Scanner(inputStream, "UTF-8");
            while (sc.hasNextLine()) {
//                 line += sc.nextLine();
                stringBuilder.append(sc.nextLine());
                // System.out.println(line);
            }
            // note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
        }
//        System.out.println(line);
//        System.out.println(line.length());
        System.out.println(stringBuilder);
        System.out.println(stringBuilder.length());
        System.out.println(System.currentTimeMillis() - startTime);

        File outputFile = new File("src/testData/output1.txt");

        PrintWriter printWriter = new PrintWriter(outputFile);
        printWriter.write(stringBuilder.toString());
        printWriter.flush();


    }

}
