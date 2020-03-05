package presentation;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.PointerLine;
import util.Observer;
import view.Main;

import java.util.ArrayList;

public class MouseMovement implements Observer<Point2D> {

    private PaintableShapesManager shapesManager = PaintableShapesManager.getInstance();
    private ArrayList<Node> nodes;

    @Override
    public void update(Point2D changed) {
        nodes = shapesManager.getNodes();
        removeCursorCircle();
        removePointerLine();

        double posY = shapesManager.getYCoordinateForXCoordinate(changed.getX()).getY();
        shapesManager.addNode(new Circle(changed.getX(), posY, 5, Color.valueOf("#dc5d4c")));
        shapesManager.addNode(new PointerLine(changed.getX(), Main.HEIGHT, changed.getX(), posY));
    }

    private void removePointerLine() {
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i) instanceof PointerLine) {
                nodes.remove(i);
            }
        }
    }

    private void removeCursorCircle() {
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i) instanceof Circle) {
                nodes.remove(i);
            }
        }
    }
}
