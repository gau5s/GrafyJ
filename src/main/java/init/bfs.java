package init;

import gui.CircleG;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class bfs extends Graph{

    private d_t[] visited = null;

    

    public bfs (int h, int w, double a, double b) {
        super(h,w,a,b);
        visited = new d_t[h*w];
        visitedFill(0);
    }

    public bfs(Graph gr) {
        this.h = gr.h;
        this.w = gr.w;
        this.nod = gr.nod;
        this.minValEdg = gr.minValEdg;
        this.maxValEdg = gr.maxValEdg;
        this.visited = new d_t[w*h];
        this.lines = gr.lines;
        visitedFill(0);
    }

    public boolean bfsRun() {

        Queue q = new Queue();
        nod[0].getCircle().setOnMousePressed(circleOnMousePressedEventHandler);
        q.addToQueue(visited[0]);
        d_t i;
        while (!q.isEmpty()) {
            i = q.popFromQueue();
            for (int j = 0; j < 4; j++) {
                if (nod[i.node].isEdg(j) && visited[nod[i.node].EdgNum(j)].parent == -1) {
                    int x = nod[i.node].EdgNum(j);
                    visited[x].node = x;
                    visited[x].parent = i.node;
                    visited[x].odl = nod[i.node].showValEdg(j);
                    nod[x].getCircle().setOnMousePressed(circleOnMousePressedEventHandler);
                    q.addToQueue(visited[x]);
                }
            }
        }
        for (int j = 0; j < w*h; j++) {
            if (visited[j].parent == -1) {
                return false;
            }
        }
        return true;
    }

    public boolean bfsRuntest() {

        Queue q = new Queue();

        q.addToQueue(visited[0]);
        d_t i;
        while (!q.isEmpty()) {
            i = q.popFromQueue();
            for (int j = 0; j < 4; j++) {
                if (nod[i.node].isEdg(j) && visited[nod[i.node].EdgNum(j)].parent == -1) {
                    int x = nod[i.node].EdgNum(j);
                    visited[x].node = x;
                    visited[x].parent = i.node;
                    visited[x].odl = nod[i.node].showValEdg(j);

                    q.addToQueue(visited[x]);
                }
            }
        }
        for (int j = 0; j < w*h; j++) {
            if (visited[j].parent == -1) {
                return false;
            }
        }
        return true;
    }

    public void bfsColor(int node) {
        double sRw = (double)1/(w+h)*360;

        visitedFill(node);
        Queue q = new Queue();
        nod[node].getCircle().setFill(Color.hsb(0,1,0.75));
        q.addToQueue(visited[node]);
        d_t i;
        while (!q.isEmpty()) {
            i = q.popFromQueue();
            for (int j = 0; j < 4; j++) {
                if (nod[i.node].isEdg(j) && visited[nod[i.node].EdgNum(j)].parent == -1) {
                    int x = nod[i.node].EdgNum(j);
                    visited[x].node = x;
                    visited[x].parent = i.node;
                    visited[x].odl = visited[visited[x].parent].odl+1;
                    double z = visited[x].odl*sRw;
                    nod[x].getCircle().setFill(Color.hsb(z,1,0.75));
                    q.addToQueue(visited[x]);
                }
            }
        }

    }

    EventHandler<MouseEvent> circleOnMousePressedEventHandler =
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    CircleG c = (CircleG)t.getSource();
                    bfsColor(c.getNodeNmb());
                }
            };

    private void visitedFill(int node) {
        for (int i = 0; i < w*h; i++) {
            if(i == node)
                visited[i] = new d_t(i, 0, i);
            else
                visited[i] = new d_t(i, 999999999, -1);
        }
    }

}
