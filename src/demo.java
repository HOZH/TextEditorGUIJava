import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class demo extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("TextEditorGUI.fxml"));
        primaryStage.setTitle("Text Editor");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
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
