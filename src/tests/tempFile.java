package tests;

import java.io.File;
import java.io.IOException;

public class tempFile {
    public static void main(String[] args) throws IOException, InterruptedException {
        var temp = File.createTempFile("tempFile", ".txt");
        //fixme


        var thread = new Thread();

        var startTime = System.currentTimeMillis();
//        for (int i = 0; i<100000;i++)
//            thread.sleep(1000);
//        System.out.println((System.currentTimeMillis()-startTime)/1000);

        System.out.println(temp.getPath());
    }

}
