package tests;

import javafx.application.Application;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class textSelectMultiple extends Application {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        TextArea ta = new TextArea();
        System.out.println(ta.getClass().getName());
        Class clazz = ta.getClass();
        Object obj = clazz.newInstance();
//        new Thread(()->{});


    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        VBox vBox = new VBox();
//
//        TextArea textArea = new TextArea();
//
//        textArea.setText("JavaFX provides some controls that let users select an item(s) from a list of items. They take less space compared to ... ListView provides users an ability to select multiple items from a list of items. Typically, all or more than one item in a ... users select a date from a calendar pop-up. Optionally, users can enter a date as text.");
//
//
//
//        vBox.getChildren().add(textArea);
//
//
//
//
//
//
//
//
//        Scene scene = new Scene(vBox);
//
//        primaryStage.setScene(scene);
//        primaryStage.show();
    }
}
