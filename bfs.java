package Init;

public class bfs {

    private d_t[] visited = null;

    

    public bfs(Graph g) {
        visited = new d_t[g.getHeight() * g.getWidth()];
        visited[0] = new d_t(0, 0, 0);
        for (int i = 1; i < g.getHeight() * g.getWidth(); i++) {
            visited[i] = new d_t(i, 999999999, -1);
        }

    }

    public int bfs(Graph g) {

        Queue q = new Queue();
        q.addToQueue(visited[0]);
        d_t i;
        while (!q.isEmpty()) {
            i = q.popFromQueue();
            for (int j = 0; j < 4; j++) {
                if (g.nod[i.node].isEdg(j) && visited[g.nod[i.node].EdgNum(j)].parent == -1) {
                    int x = g.nod[i.node].EdgNum(j);
                    visited[x].node = x;
                    visited[x].parent = i.node;
                    visited[x].odl = g.nod[i.node].showValEdg(j);
                    q.addToQueue(visited[x]);
                }
            }
        }
        for (int j = 0; j < g.getHeight() * g.getWidth(); j++) {
            if (visited[j].parent == -1) {
                return 0;
            }
        }
        return 1;
    }
}
