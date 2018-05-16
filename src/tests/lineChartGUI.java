package tests;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static java.lang.StrictMath.log;

public class lineChartGUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {


        NumberAxis xAxis = new NumberAxis(0, 100000, log(100));
        NumberAxis yAxis = new NumberAxis(0, 10000, log(100));
        xAxis.setLabel("amount of words");
        yAxis.setLabel("time used in milliseconds");

        XYChart xyChart = new LineChart(xAxis, yAxis);


        XYChart.Series singleLoop = new XYChart.Series();
        XYChart.Series threeLoops = new XYChart.Series();


        singleLoop.setName("singleLoop");

        singleLoop.getData().addAll(new XYChart.Data<>(100, 10), new XYChart.Data<>(1000, 100), new XYChart.Data<>(10000, 4857));

        threeLoops.setName("threeLoops");

        threeLoops.getData().addAll(new XYChart.Data<>(200, 100), new XYChart.Data<>(3000, 1000), new XYChart.Data<>(9000, 6980));


        xyChart.getData().addAll(singleLoop, threeLoops);


        TextArea textArea = new TextArea();


        VBox vBox = new VBox();

        vBox.getChildren().add(textArea);

        Scene scene = new Scene(vBox);

        primaryStage.setScene(scene);
        primaryStage.show();


        Stage stage = new Stage();

        VBox vBox1 = new VBox();

        vBox1.getChildren().add(xyChart);

        Scene scene1 = new Scene(vBox1);

        stage.setScene(scene1);
        stage.show();
    }
}
