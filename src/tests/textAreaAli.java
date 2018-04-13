package tests;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class textAreaAli extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        var textArea = new TextArea();
//        textArea.setPrefWidth(10);
        textArea.setPrefColumnCount(5);
        textArea.setWrapText(true);
        textArea.appendText("how many roads must a man walk down before you call him a man how many seas must a white dove sail before she sleeps in the sand yes and how many times must the cannon balls fly before they're foreve");


        var vBox = new VBox();

        vBox.getChildren().add(textArea);

        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);
        primaryStage.show();


    }
}
