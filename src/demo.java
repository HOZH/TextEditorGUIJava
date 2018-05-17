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


}

