package model;

import controller.RandomChartDataGenerator;
import javafx.geometry.Point2D;

import java.util.ArrayList;

public class Chart {

    private ArrayList<Point2D> points;

    public Chart(RandomChartDataGenerator gen) {
        this.points = gen.generateChartData(4.0);
    }
    public Chart() {
        this.points = new ArrayList<>();
    }

    public ArrayList<Point2D> getPoints() {
        return points;
    }
}
