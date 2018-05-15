package tests.booleanPropertyListener;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class gui extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        BooleanProperty booleanProperty = new SimpleBooleanProperty();
        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setText(booleanProperty.getValue().toString());
        Button button = new Button("change");

        button.setOnAction(event -> {
            booleanProperty.setValue(!booleanProperty.get());
            textArea.setText(booleanProperty.getValue().toString());
        });

        booleanProperty.addListener((e, oldvalue, newvalue) -> {
            System.out.println("changed");
            System.out.println("old =" + oldvalue.toString());
            System.out.println("new =" + newvalue.toString());
        });

        VBox vBox = new VBox();

        vBox.getChildren().addAll(textArea, button);

        Scene scene = new Scene(vBox);

        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
