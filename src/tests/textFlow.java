package tests;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class textFlow extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {


        VBox vbox = new VBox();

        Text text1 = new Text("there");

        TextFlow textFlow = new TextFlow();
        textFlow.getChildren().add(text1);









        vbox.getChildren().add(textFlow);

        Scene scene = new Scene(vbox);

        primaryStage.setScene(scene);
        primaryStage.show();




    }
}
