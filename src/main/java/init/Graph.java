package init;

import java.io.FileWriter;
import java.io.IOException;

public class Graph {

    protected int h;
    protected int w;
    protected Node[] nod = null;
    protected double minValEdg;
    protected double maxValEdg;

    public Node getNode(int i) {
        return this.nod[i];
    }

    public int getHeight() {
        return h;
    }

    public int getWidth() {
        return w;
    }

    public void connectNode(int i, int j, int n) {
        nod[i].connect(j, nod[n]);

    }

    public void setVal(int i, int j, double a) {
        nod[i].setVal(j, a);

    }

    public double getVal(int i, int j) {
        return nod[i].showValEdg(j);
    }

    public Node getEdg(int i, int j) {
        return nod[i].EdgPointer(j);
    }

    public int EdgNum(int i, int j) {
        return nod[i].EdgNum(j);
    }

    public double getMinValEdg() {
        return this.minValEdg;
    }

    public double getMaxValEdg() {
        return this.maxValEdg;
    }

    public void setMinValEdg(double x) {
        this.minValEdg = x;
    }

    public void setMaxValEdg(double x) {
        this.maxValEdg = x;
    }

    public void printGraph(){
        for(int i=0;i<w*h;i++){
            for(int j=0;j<4;j++){
                if(nod[i].isEdg(j))
                    System.out.print(EdgNum(i,j)+"  "+getVal(i,j)+"  ");
               
            }
            System.out.print("\n");
        }
    }

    public Graph() {
    }

    public Graph(int h, int w) {
        this.h = h;
        this.w = w;
        this.nod = new Node[h * w];
        for (int i = 0; i < w * h; i++) {
            this.nod[i] = new Node(i);
        }
    }

    public Graph(int h, int w, double a, double b) {
        this.h = h;
        this.w = w;
        this.minValEdg = a;
        this.maxValEdg = b;
        this.nod = new Node[h * w];
        for (int i = 0; i < w * h; i++) {
            this.nod[i] = new Node(i);
        }

        int column = 1, row = 1;
        /////////////////////////////////
        for (int i = 0; i < h * w; i++) {
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

    public void writeToFile(String filename) throws IOException { /////
        int x = w*h;
        FileWriter w = new FileWriter(filename);
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < 4; j++) {
                if (nod[i].isEdg(j))
                    w.write(EdgNum(i,j) + " :" + getVal(i,j) + " ");
            }
            w.write("\n");
        }
        w.close();
    }

}
