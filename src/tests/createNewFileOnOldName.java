package tests;

import java.io.File;
import java.io.IOException;

public class createNewFileOnOldName {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("src/inputData/story.txt");

        System.out.println(inputFile.getName());

        var stringBuilder = new StringBuilder();

        stringBuilder.append(inputFile.getName());

        System.out.println(stringBuilder);

        //todo add "Record" before .txt"
        var record = "Record";

        var length = stringBuilder.length();
        System.out.println(length);

        var position = length - 4;

        stringBuilder.insert(position, record);
        System.out.println(stringBuilder);

        var rawPath = new StringBuilder();

        rawPath.append("src/outputData/");

        rawPath.append(stringBuilder);
        System.out.println(rawPath);

        var outputFile = new File(rawPath.toString());

        if (outputFile.createNewFile())
            System.out.println(outputFile.getName() + " is created");
        else
            System.out.println(outputFile.getName() + " is already there");


    }
}
