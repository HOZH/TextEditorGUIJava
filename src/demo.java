import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class demo extends Application {
    // dic
    public static File dictionaryFile = new File("src/dictionary.txt");
    public static HashMap dictionary;

    @Override
    public void start(Stage primaryStage) throws Exception {


        Parent root = FXMLLoader.load(getClass().getResource("TextEditorGUI.fxml"));
        primaryStage.setTitle("Text Editor");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        loadDictionary();
    }


    public static void loadDictionary() throws FileNotFoundException {

        dictionary = new HashMap();
        Scanner dicScanner = new Scanner(dictionaryFile);

        while (dicScanner.hasNext()) {
            dictionary.put(dicScanner.next(), "");
        }

    }
}

/*
one single loop VS. three loops
performance record
1) When the string length is 100 words
2) When the string length is 1,000 words
3) When the string length is 10,000 words.
4) When the string length is 100,000 words.
 */


/*
todo  up to 4 threads random access reading for intput txt
todo  up to 4 threads for generating markov
todo  rewrite 3loops
todo  1) When the string length is 100 words
todo 2) When the string length is 1,000 words
todo 3) When the string length is 10,000 words.
todo 4) When the string length is 100,000 words.
todo hashMap dictionary
todo replace explicit variable type to variable


 */
