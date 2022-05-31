package gui;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class CircleG extends Circle {
    private int nodeNmb;

    public CircleG(double r, Paint paint, int n) {
        super(r,paint);
        nodeNmb = n;
    }

    public int getNodeNmb() {
        return nodeNmb;
    }
}
