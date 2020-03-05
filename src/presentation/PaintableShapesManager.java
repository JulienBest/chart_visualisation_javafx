package presentation;

import javafx.scene.Node;
import util.Observable;
import util.Observer;

import java.util.ArrayList;

public class PaintableShapesManager implements Observable<ArrayList<Node>> {

    private ArrayList<Node> nodes = new ArrayList<>();
    private ArrayList<Observer<ArrayList<Node>>> observers = new ArrayList<>();
    private static PaintableShapesManager instance;

    private PaintableShapesManager() {}

    public static PaintableShapesManager getInstance() {
        if (instance == null) {
            instance = new PaintableShapesManager();
        }
        return instance;
    }

    public ArrayList<Node> getShapes() {
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
}

