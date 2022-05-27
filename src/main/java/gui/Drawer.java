package gui;

import init.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Drawer {
    public static void draw(AnchorPane Pane, int x0, int x1, int y0, int y1, Graph gr) {
        double r1 = (double)(x1 - x0) / (2 * gr.getWidth() - 1) / 2;
        double r2 = (double)(y1 - y0) / (2 * gr.getHeight() - 1) / 2;
        double r = r1 < r2 ? r1: r2;
        double offx = x0 + r, offy = y0 + r;

        for(int i = 0; i < gr.getHeight(); i++) {
            for(int j = 0; j < gr.getWidth(); j++) {
                int x = i * gr.getWidth() + j;
                Circle circle = new Circle(r, Color.STEELBLUE);
                circle.setCenterX(offx);
                circle.setCenterY(offy);
                circle.toFront();
                Pane.getChildren().add(circle);
                gr.getNode(i * gr.getWidth() + j).setCircle(circle);

                ///////////////////////////////
                for(int k = 0; k < 4; k++) {
                    if(gr.getEdg(x,k) != null) {
                        int z = gr.EdgNum(x,k);
                        double lineWidth = 2;
                        Line l;
                        if(x == z + gr.getWidth()) {//gora
                            l = new Line(offx, offy-r, offx, offy-3*r);
                            l.setStrokeWidth(lineWidth);
                            Pane.getChildren().add(l);
                        }
                        else if(x == z-1) {//prawo
                            l = new Line(offx+r, offy, offx+3*r, offy);
                            l.setStrokeWidth(lineWidth);
                            Pane.getChildren().add(l);
                        }
                        else if(x == z - gr.getWidth()) {//dol
                            l = new Line(offx, offy+r, offx, offy+3*r);
                            l.setStrokeWidth(lineWidth);
                            Pane.getChildren().add(l);
                        }
                        else if (x == z+1) {//lewo
                            l = new Line(offx-r, offy, offx-3*r, offy);
                            l.setStrokeWidth(lineWidth);
                            Pane.getChildren().add(l);
                        }

                    }
                }
                ///////////////////////////////
                offx += 4*r;
            }

            offx = x0 + r;
            offy += 4*r;
        }
    }

    public static void drawDijkstra(AnchorPane Pane, DijkstraGraph gr, int fromNode, int toNode) {
        gr.dijkstra(fromNode);
        d_t [] d = gr.shortestPath(toNode);
        double x0,x1,y0,y1;

        for(int i = 0; i < d.length-1; i++) {
            x0 = gr.getNode(d[i].node).getCircle().getCenterX();
            y0 = gr.getNode(d[i].node).getCircle().getCenterY();
            x1 = gr.getNode(d[i+1].node).getCircle().getCenterX();
            y1 = gr.getNode(d[i+1].node).getCircle().getCenterY();

            Line l = new Line(x0,y0,x1,y1);
            l.setStroke(Color.WHITE);
            l.setStrokeWidth(3);
            Pane.getChildren().add(l);
        }
    }

}
