package init;

import gui.CircleG;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class DijkstraGraph extends Graph {

    private d_t [] dij = null;
    private ArrayList<Line> dijkstraLines = new ArrayList<Line>();
    private int nodeDijkstra; //=-1;
    private AnchorPane pane = null; // do rysowania najkrotszej sciezki potrzebne

    public DijkstraGraph(int h, int w, double a, double b) {
        super(h, w, a, b);
        dij = new d_t[w*h];
        nodeDijkstra = -1;
        for(int i = 0; i < w*h; i++) {
            dij[i] = new d_t(i, 999999999, -1);
        }
    }

    public DijkstraGraph(Graph gr) {
        this.h = gr.h;
        this.w = gr.w;
        this.nod = gr.nod;
        this.nodeDijkstra = -1;
        this.minValEdg = gr.minValEdg;
        this.maxValEdg = gr.maxValEdg;
        this.lines = gr.lines;
        dij = new d_t[w*h];
        for(int i = 0; i < w*h; i++) {
            if(nod[i].getCircle() != null)
                nod[i].getCircle().setOnMousePressed(circleOnMousePressedEventHandler);
            dij[i] = new d_t(i, 999999999, -1);
        }
    }

    public void dijkstra(int x) { //x - wezel od ktorego szukamy najkr odl
        this.nodeDijkstra = x;
        Queue q = new Queue();
        dijFill(x);
        nod[x].getCircle().setOnMousePressed(circleOnMousePressedEventHandler);
        q.addToQueue(dij[x]);
        while(!q.isEmpty()) {
            d_t act = q.popFromQueue();
            for (int i = 0; i < 4; i++) {
                if (nod[act.node].isEdg(i)) {
                    if(dij[act.node].odl + getVal(act.node, i) < dij[getEdg(act.node, i).showNode()].odl) {
                        dij[getEdg(act.node, i).showNode()].odl = dij[act.node].odl + getVal(act.node, i);
                        dij[getEdg(act.node, i).showNode()].parent = dij[act.node].node;
                        nod[getEdg(act.node, i).showNode()].getCircle().setOnMousePressed(circleOnMousePressedEventHandler);
                        q.addToQueue(dij[getEdg(act.node, i).showNode()]);
                    }
                }
            }
        }
        dijColor();
    }

    private void drawDijkstra(AnchorPane Pane, int toNode) {
        d_t [] d = shortestPath(toNode);
        double x0,x1,y0,y1;
        double lineWidth = lines[0].getStrokeWidth();


        for(int i = 0; i < d.length-1; i++) {
            x0 = getNode(d[i].node).getCircle().getCenterX();
            y0 = getNode(d[i].node).getCircle().getCenterY();
            x1 = getNode(d[i+1].node).getCircle().getCenterX();
            y1 = getNode(d[i+1].node).getCircle().getCenterY();

            Line l = new Line(x0,y0,x1,y1);
            l.setStroke(Color.WHITE);
            l.setStrokeWidth(1.75*lineWidth);
            addDijkstraLine(l);
            Pane.getChildren().add(l);
        }
    }

    public d_t [] shortestPath (int toNode) {
        int x = dijIlePolaczen(toNode);
        d_t [] ret = new d_t[x];

        ret[--x] = dij[toNode];
        while(ret[x].parent != -1) {
            ret[--x] = dij[ret[x+1].parent];
        }

        return ret;
    }

    private int dijIlePolaczen(int toNode) {
        d_t tmp = this.dij[toNode];
        int count = 1;
        while(tmp.parent != -1) {
            tmp = dij[tmp.parent];
            count++;
        }

        return count;
    }

    private void dijColor() {
        double sRw = 300/longestPathVal();
        for(int i = 0; i < dij.length; i++) {
            double z = dij[i].odl*sRw;
            nod[i].getCircle().setFill(Color.hsb(z,1,0.75));
        }
    }

    private double longestPathVal() {
        double ret = 0;
        for(int i = 0; i < dij.length; i++) {
            if(dij[i].odl > ret)
                ret = dij[i].odl;
        }
        return ret;
    }

    EventHandler<MouseEvent> circleOnMousePressedEventHandler =
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    if(t.getButton() == MouseButton.PRIMARY) {
                        CircleG c = (CircleG) t.getSource();
                        dijkstra(c.getNodeNmb());
                    }
                    else if (t.getButton() == MouseButton.SECONDARY && pane != null && nodeDijkstra != -1) {
                        CircleG c = (CircleG) t.getSource();
                        drawDijkstra(pane, c.getNodeNmb());
                    }
                }
            };
    private void dijFill(int node) {
        for (int i = 0; i < w*h; i++) {
            if(i == node)
                dij[i] = new d_t(i, 0, -1);
            else
                dij[i] = new d_t(i, 999999999, -1);
        }
    }

    public void addDijkstraLine(Line l) {
        dijkstraLines.add(l);
    }

    public void cleanDijkstraLines() {
        pane.getChildren().removeAll(dijkstraLines);
        dijkstraLines.clear();
    }

    public void setPane(AnchorPane p) {
        this.pane = p;
    }
}
