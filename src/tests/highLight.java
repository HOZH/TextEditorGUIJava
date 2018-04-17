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
//        ta.setStyle("-fx-effect: dropshadow(gaussian, red, 2,0,0,0)");


        ta.setText("1234567891011");

//        ta.selectRange(2,3);


//        ta.getStylesheets().add("src/tests/temp.css");
        ta.selectRange(2,4);
        ta.selectRange(6,7);



        vBox.getChildren().add(ta);

        Scene scene = new Scene(vBox);
//        scene.getStylesheets().add("src/tests/temp.css");

        primaryStage.setScene(scene);
        primaryStage.show();
    }


}

