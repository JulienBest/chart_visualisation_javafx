package controller;

import javafx.geometry.Point2D;
import view.Main;

import java.util.ArrayList;

public class RandomChartDataGenerator {

    private Point2D max = new Point2D(Main.WIDTH, Main.HEIGHT);
    private Point2D min = new Point2D(0, 0);
    private Point2D startingPoint = new Point2D(0, max.getY() / 2);


    public ArrayList<Point2D> generateChartData(double volatility) {
        ArrayList<Point2D> generated = new ArrayList<>();
        generated.add(startingPoint);
        Point2D lastAdded = startingPoint;
        double bias = 1.0;

        do {
            if (lastAdded.getY() >= max.getY() - (max.getY() / 5)) {
                bias = 2;
            }
            if (lastAdded.getY() <= min.getY() + (min.getY() / 5)) {
                bias = -2;
            }
            double randomOffsetX = (Math.random() * 5 / volatility) + 5 / volatility;
            double randomOffsetY = (Math.random() * (volatility * 20)) - (volatility * 10 * bias);

            if (bias != 1) {
                bias = bias < 0 ? bias + 0.5 : bias - 0.5;
            }

            Point2D newPoint = new Point2D(lastAdded.getX() + randomOffsetX, lastAdded.getY() + randomOffsetY);
            generated.add(newPoint);
            lastAdded = newPoint;
        } while (lastAdded.getX() <= max.getX());

        return generated;
    }
}
