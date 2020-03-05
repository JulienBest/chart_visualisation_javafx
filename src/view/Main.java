package view;

import controller.RandomChartDataGenerator;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Chart;
import presentation.MouseMovement;
import presentation.PaintableChart;
import presentation.PaintableShapesManager;
import util.Observable;
import util.Observer;

import java.util.ArrayList;

public class Main extends Application implements Observer<ArrayList<Node>>, Observable<Point2D> {

    public static final int WIDTH = 1400;
    public static final int HEIGHT = 800;
    private Pane root;
    private ArrayList<Observer<Point2D>> mousePosObserver = new ArrayList<>();
    private double currentMousePosX;
    private double currentMousePosY;

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new Pane();
        PaintableShapesManager.getInstance().addObserver(this);
        primaryStage.setTitle("Chart Generator");
        Scene scene = new Scene(root, WIDTH, HEIGHT, Color.grayRgb(30));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        root.setOnMouseMoved(mouseEvent -> {
            currentMousePosX = mouseEvent.getX();
            currentMousePosY = mouseEvent.getY();
            notifyObservers();
        });

        Observer<Point2D> mouseMovement = new MouseMovement();
        addObserver(mouseMovement);

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

    @Override
    public void addObserver(Observer<Point2D> observer) {
        mousePosObserver.add(observer);
    }

    @Override
    public void removeObserver(Observer<Point2D> observer) {
        mousePosObserver.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer<Point2D> o : mousePosObserver) {
            o.update(new Point2D(currentMousePosX, currentMousePosY));
        }
    }
}
