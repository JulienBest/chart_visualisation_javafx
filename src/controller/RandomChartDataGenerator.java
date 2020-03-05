package controller;

import javafx.geometry.Point2D;

import java.util.ArrayList;

public class RandomChartDataGenerator {

    public ArrayList<Point2D> generateChartData(double volatality) {
        ArrayList<Point2D> generated = new ArrayList<>();
        generated.add(new Point2D(0,0));
        generated.add(new Point2D(500,500));
        generated.add(new Point2D(900,200));
        generated.add(new Point2D(1100,700));
        generated.add(new Point2D(1300,500));
        return generated;
    }
}
