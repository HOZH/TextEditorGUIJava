import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author hz
 */
public class demo extends Application {
    // dic
    public static File dictionaryFile = new File("src/dictionary.txt");
    public static HashMap dictionary;
    public static BooleanProperty ifModified = new SimpleBooleanProperty(false);

    /**
     * load the dictionary from .txt file into hashMap
     *
     * @throws FileNotFoundException
     */
    public static void loadDictionary() throws FileNotFoundException {

        dictionary = new HashMap();
        Scanner dicScanner = new Scanner(dictionaryFile);

        while (dicScanner.hasNext()) {
            dictionary.put(dicScanner.next(), "");
        }


    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        Parent root = FXMLLoader.load(getClass().getResource("TextEditorGUI.fxml"));
        primaryStage.setTitle("Text Editor");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        loadDictionary();

    }

//    public static void afterExitSpellCheckerStage(TextEditorGUI textEditorGUI){
//
//        textEditorGUI.textArea.setText(SpellCheckGUI.spellCheckTxt);
//
//    }
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
todo  up to 4 threads random access reading for input txt
todo  rewrite markov with binary search tree or hashMap
todo  rewrite 3loops
todo  1) When the string length is 100 words
todo 2) When the string length is 1,000 words
todo 3) When the string length is 10,000 words.
todo 4) When the string length is 100,000 words.
todo replace explicit variable type to variable.


 */

/*
todo set boundaries for getNext and getPrevious
todo set defualt textArea not editable
todo save speed of 4 kinds of words
todo 3 loops and single loop
todo rewrite markov with binary search tree
todo plot performancd in the line chart
todo update comments
todo /if i have time convert this code to java 8 version
 */
