package Init;

public class Graph {
    private int h;
    private int w;
    private Node[] nod = null;

    public Graph(int h, int w, double a, double b) {
        this.h = h;
        this.w = w;
        this.nod = new Node[h*w];
        for(int i = 0; i < w*h; i++)
            this.nod[i] = new Node(i);

        int column = 1, row = 1;
        /////////////////////////////////
        for(int i = 0; i < h*w; i++) {
            if (column > this.w) {
                column = 1;
                row++;
            }
            if (i - this.w >= 0) { //sprawdzenie czy polaczenie w gore
                nod[i].connect(0, nod[i - this.w]);
                nod[i].randEdg(0, a, b);
            }
            if (i + 1 < row * this.w) { //prawo
                nod[i].connect(1, nod[i + 1]);
                nod[i].randEdg(1, a, b);
            }
            if (i + this.w < w * h) { //dol
                nod[i].connect(2, nod[i + this.w]);
                nod[i].randEdg(2, a, b);
            }
            if (i - 1 >= (row - 1) * this.w) { //gora
                nod[i].connect(3, nod[i - 1]);
                nod[i].randEdg(3, a, b);
            }
            column++;
        }
        /////////////////////////////////
    }

}
