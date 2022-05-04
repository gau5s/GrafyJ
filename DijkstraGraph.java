package Init;

public class DijkstraGraph extends Graph {

    private d_t [] dij = null;

    public DijkstraGraph(int h, int w, double a, double b) {
        super(h, w, a, b);
        dij = new d_t[w*h];
        for(int i = 0; i < w*h; i++) {
            dij[i] = new d_t(i, 999999999, -1);
        }
    }

    public void dijkstra(int x) { //x - wezel od ktorego szukamy najkr odl
        Queue q = new Queue();
        dij[x].odl = 0;
        q.addToQueue(dij[x]);
        while(!q.isEmpty()) {
            d_t act = q.popFromQueue();
            for (int i = 0; i < 4; i++) {
                if (nod[act.node].isEdg(i)) {
                    if(dij[act.node].odl + getVal(act.node, i) < dij[getEdg(act.node, i).showNode()].odl) {
                        dij[getEdg(act.node, i).showNode()].odl = dij[act.node].odl + getVal(act.node, i);
                        dij[getEdg(act.node, i).showNode()].parent = dij[act.node].node;
                        q.addToQueue(dij[getEdg(act.node, i).showNode()]);
                    }
                }
            }
        }
    }
}
