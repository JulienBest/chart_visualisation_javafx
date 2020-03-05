package presentation;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import model.Chart;

import java.awt.*;
import java.util.ArrayList;

public class PaintableChart implements Paintable {

    private Chart chart;

    public PaintableChart(Chart chart) {
        this.chart = chart;
    }

    @Override
    public void paint() {
        ArrayList<Point2D> points = this.chart.getPoints();
        PaintableShapesManager manager = PaintableShapesManager.getInstance();

        if (points.size() > 1) {
            for (int i = 1; i < points.size(); i++) {
                System.out.println("Point "+i+": " + points.get(i).getY()+"|"+points.get(i).getY());
                Line line = new Line(points.get(i-1).getX(), points.get(i-1).getY(), points.get(i).getX(), points.get(i).getY());
                line.setStroke(Color.WHITE);
                manager.addNode(line);
            }
        }
    }
}
