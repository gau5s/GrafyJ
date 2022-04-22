package Init;

import java.util.Random;

public class Node {
    private int i;
    private Node[] edg = new Node[4];
    private double[] valEdg = new double[4];
    private Random liczba = new Random();

    public Node(int i) {
        this.i = i;
    }

    public void connect(int j, Node n) {
        edg[j] = n;
    }

    public void randEdg(int j, double a, double b) {
        this.valEdg[j] = liczba.nextDouble() * (b-a) + a;
    }
    
    public int showNode() {
        return this.i;
    }

    public double showValEdg(int k) {
        return this.valEdg[k];
    }

    public boolean isEdg(int k) {
        return this.edg[k] != null;
    }

}
