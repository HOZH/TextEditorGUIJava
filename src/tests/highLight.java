package tests;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class highLight extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        var vBox = new VBox();
        var ta = new TextArea();
//        ta.setStyle("-fx-highlight-fill: lightgray; -fx-highlight-text-fill: firebrick; -fx-font-size: 20px;");

        ta.setText("123456");

        ta.selectRange(2,3);

//        ta.getStylesheets().add("src/tests/temp.css");



        vBox.getChildren().add(ta);

        Scene scene = new Scene(vBox);
//        scene.getStylesheets().add("src/tests/temp.css");

        primaryStage.setScene(scene);
        primaryStage.show();
    }


}

