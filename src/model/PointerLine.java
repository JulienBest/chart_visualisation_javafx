package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class PointerLine extends Line {
    public PointerLine(double startX, double startY, double endX, double endY) {
        super(startX, startY, endX, endY);
        this.setStroke(Color.valueOf("#dc5d4c"));
    }
}
