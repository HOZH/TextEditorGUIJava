package tests;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class javafxTextAreaListener extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        TextArea textArea = new TextArea();

        TextArea mirror = new TextArea();

        TextField textField = new TextField();

        textArea.textProperty().addListener((ob, o, n) -> {
            if (o != n) {
                textArea.focusTraversableProperty().setValue(!textArea.focusTraversableProperty().getValue());
            }

        });

        textArea.focusTraversableProperty().addListener((ob, o, n) -> {
            System.out.println(o);
            System.out.println(n);
            System.out.println(o.getClass().getName());
        });


//        textArea.textProperty().bind(mirror.textProperty());
//textArea.textProperty().bindBidirectional(mirror.textProperty());


        VBox vBox = new VBox();
        vBox.getChildren().addAll(textArea, mirror);

        Scene scene = new Scene(vBox);

        primaryStage.setScene(scene);
        primaryStage.show();


    }
}
