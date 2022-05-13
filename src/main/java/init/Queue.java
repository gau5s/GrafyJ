package init;

import java.util.ArrayList;

public class Queue {
    private d_t [] q;
    private int size;
    private int actualSize;

    public Queue(){
        q = new d_t[8];
        size = 0;
        actualSize = 8;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addToQueue(d_t element) {
        if(size+1 > actualSize)
            doubleQueueSize();
        q[size++] = element;
        heapUp();
    }

    public d_t popFromQueue() {
        d_t ret = q[0];
        q[0] = q[--size];
        heapDown();
        return ret;
    }

    private void doubleQueueSize() {
        d_t[] tmpq = new d_t[actualSize*2];
        System.arraycopy(q, 0, tmpq, 0, size);
        q = tmpq;
        actualSize *= 2;
    }

    private void heapUp() {
        int i = size-1;
        while( i > 0 ) {
            int p = (i-1) / 2;
            if( q[p].odl <= q[i].odl )
                return;
            d_t tmp = q[p];
            q[p] = q[i];
            q[i] = tmp;
            i = p;
        }
    }

    private void heapDown() {
        int i = 0;
        int c = 2*i + 1;
        while( c < size ) {
            if( c+1 < size && q[c+1].odl < q[c].odl )
                c++;
            if( q[i].odl <= q[c].odl )
                return;
            d_t tmp = q[i];
            q[i] = q[c];
            q[c] = tmp;
            i = c;
            c = 2*i + 1;
        }
    }
}
