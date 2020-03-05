package presentation;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.shape.Line;
import util.Observable;
import util.Observer;

import java.util.ArrayList;

public class PaintableShapesManager implements Observable<ArrayList<Node>> {

    private ArrayList<Node> nodes = new ArrayList<>();
    private ArrayList<Observer<ArrayList<Node>>> observers = new ArrayList<>();
    private static PaintableShapesManager instance;

    private PaintableShapesManager() {
    }

    public static PaintableShapesManager getInstance() {
        if (instance == null) {
            instance = new PaintableShapesManager();
        }
        return instance;
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void addNode(Node node) {
        this.nodes.add(node);
        notifyObservers();
    }

    @Override
    public void addObserver(Observer<ArrayList<Node>> observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(Observer<ArrayList<Node>> observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer<ArrayList<Node>> o : observers) {
            o.update(this.nodes);
        }
    }

    public Point2D getYCoordinateForXCoordinate(double x) {
        Point2D nearest = new Point2D(0, 0);
        double bestDistance = Integer.MAX_VALUE;

        for (int i = 0; i < nodes.size(); i++) {
            Line line = ((Line) nodes.get(i));
            double distance1 = Math.abs(x - line.getStartX());
            double distance2 = Math.abs(x - line.getEndX());
            if (distance1 < bestDistance) {
                bestDistance = distance1;
                nearest = new Point2D(line.getStartX(), line.getStartY());
            }
            if (distance2 < bestDistance) {
                bestDistance = distance2;
                nearest = new Point2D(line.getEndX(), line.getEndY());
            }
        }
        return nearest;
    }
}

