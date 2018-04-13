package tests;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class popUp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Button btn = new Button();
        btn.setText("Open Dialog");
        btn.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        final Stage dialog = new Stage();
                        dialog.initModality(Modality.APPLICATION_MODAL);
                        dialog.initOwner(primaryStage);
                        VBox dialogVbox = new VBox(20);
                        dialogVbox.getChildren().add(new Text("This is a Dialog"));


                        Button add = new Button();
                        TextField textField = new TextField();
                        TextField textField1 = new TextField();
                        add.setOnAction(event1 -> {
                            String string = textField.getText();
                            textField1.setText(string);

                        });
                        dialogVbox.getChildren().addAll(add, textField, textField1);


                        Scene dialogScene = new Scene(dialogVbox, 300, 200);
                        dialog.setScene(dialogScene);
                        dialog.show();
                    }
                });
        VBox vBox = new VBox();
        vBox.getChildren().add(btn);
        Scene scene = new Scene(vBox);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
