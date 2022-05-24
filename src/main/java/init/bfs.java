package init;

public class bfs extends Graph{

    private d_t[] visited = null;

    

    public bfs (int h, int w, double a, double b) {
        super(h,w,a,b);
        visited = new d_t[h*w];
        visited[0] = new d_t(0, 0, 0);
        for (int i = 1; i < w*h; i++) {
            visited[i] = new d_t(i, 999999999, -1);
        }

    }

    public bfs(Graph gr) {
        this.h = gr.h;
        this.w = gr.w;
        this.nod = gr.nod;
        this.minValEdg = gr.minValEdg;
        this.maxValEdg = gr.maxValEdg;
        this.visited = new d_t[w*h];
        for(int i = 0; i < w*h; i++) {
            visited[i] = new d_t(i, 999999999, -1);
        }
    }

    public boolean bfsRun() {

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


}
