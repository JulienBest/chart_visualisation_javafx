package view;

import controller.RandomChartDataGenerator;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import model.Chart;
import presentation.PaintableChart;
import presentation.PaintableShapesManager;
import util.Observer;

import java.awt.*;
import java.util.ArrayList;

public class Main extends Application implements Observer<ArrayList<Node>> {

    private final int WIDTH = 1400;
    private final int HEIGHT = 800;
    private Pane root;

    @Override
    public void start(Stage primaryStage) throws Exception{
        root = new Pane();
        PaintableShapesManager.getInstance().addObserver(this);
        primaryStage.setTitle("Chart Generator");
        Scene scene = new Scene(root, WIDTH, HEIGHT, Color.grayRgb(30));
        primaryStage.setScene(scene);
        primaryStage.show();

        PaintableChart chart = new PaintableChart(new Chart(new RandomChartDataGenerator()));
        chart.paint();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void update(ArrayList<Node> shapes) {
        root.getChildren().clear();
        for (Node node : shapes) {
            root.getChildren().add(node);
        }
    }
}
