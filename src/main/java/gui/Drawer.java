package gui;

import init.*;
import javafx.scene.Cursor;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
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

                //double blue = (double)i*j/gr.getHeight()/gr.getWidth()*360;
                //int bluei = (int) blue;
                //Circle circle = new Circle(r, Color.hsb(360-blue,1.0,0.75));

                CircleG circle = new CircleG(r, Color.gray(0.5), x);
                circle.setCenterX(offx);
                circle.setCenterY(offy);
                circle.setCursor(Cursor.HAND);

                Pane.getChildren().add(circle);
                gr.getNode(i * gr.getWidth() + j).setCircle(circle);

                ///////////////////////////////
                for(int k = 0; k < 4; k++) {
                    if(gr.getEdg(x,k) != null) {
                        int z = gr.EdgNum(x,k);
                        double edgValue = avgEdg(gr, x, z, k);
                        double lineWidth = r/3.3;
                        Line l;
                        double sRw = 1/(gr.getMaxValEdg()-gr.getMinValEdg())*360;
                        double color = edgValue * sRw;
                        double saturation = 1;
                        double brightness = 0.75;


                        if(x == z + gr.getWidth()) {//gora
                            l = new Line(offx, offy-r, offx, offy-3*r);
                            l.setStrokeWidth(lineWidth);
                            l.setStroke(Color.hsb(color,saturation,brightness));
                            gr.setLine(l);
                            Pane.getChildren().add(l);
                        }
                        else if(x == z-1) {//prawo
                            l = new Line(offx+r, offy, offx+3*r, offy);
                            l.setStrokeWidth(lineWidth);
                            l.setStroke(Color.hsb(color,saturation,brightness));
                            gr.setLine(l);
                            Pane.getChildren().add(l);
                        }
                        else if(x == z - gr.getWidth()) {//dol
                            l = new Line(offx, offy+r, offx, offy+3*r);
                            l.setStrokeWidth(lineWidth);
                            l.setStroke(Color.hsb(color,saturation,brightness));
                            gr.setLine(l);
                            Pane.getChildren().add(l);
                        }
                        else if (x == z+1) {//lewo
                            l = new Line(offx-r, offy, offx-3*r, offy);
                            l.setStrokeWidth(lineWidth);
                            l.setStroke(Color.hsb(color,saturation,brightness));
                            gr.setLine(l);
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
        for(int i = 0; i < gr.getHeight()*gr.getWidth(); i++) {
            gr.getNode(i).getCircle().toFront();
        }
    }


    private static double avgEdg(Graph gr, int nod1, int nod2, int k) {
        double ret = 0;
        ret = gr.getVal(nod1,k);

        int j = 0;
        for(;j < 4; j++) {
            if(gr.getEdg(nod2,j) != null && gr.EdgNum(nod2,j) == nod1)
                break;
        }
        if(j != 4) {
            ret += gr.getVal(nod2, j);
            ret /= 2;
        }
        return ret;
    }

    public static void clean(AnchorPane Pane, Graph gr) {
        for(int i = 0; i < gr.getWidth()*gr.getHeight(); i++) {
            Pane.getChildren().remove(gr.getNode(i).getCircle());
        }
        Line [] l = gr.getLines();
        for(int i = 0; i < l.length; i++) {
            Pane.getChildren().remove(l[i]);
        }
        if(gr instanceof DijkstraGraph) {
            ((DijkstraGraph) gr).cleanDijkstraLines();
        }
    }

}
