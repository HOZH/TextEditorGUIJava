package tests.overRTA;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class demo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox vBox = new VBox();

        TextA textA = new TextA();
        textA.setEditable(false);
        Text text = new Text();

        textA.setText("11111111111111111111");
//        textA.setStyle("-fx-border-color: black, transparent, black;");
//        textA.selectRange(3,5).setStyle("-fx-text-inner-color: red;");;
        TextFlow textFlow = new TextFlow();
//        textFlow.

        textA.selectRange(3, 4);
        //textA.selectRange(6,7);


        vBox.getChildren().add(textA);

        var scene = new Scene(vBox);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
