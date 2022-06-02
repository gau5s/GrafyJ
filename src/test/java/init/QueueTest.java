package init;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QueueTest {
    @Test
    public void QueueTest_addToQueue(){
        Queue q=new Queue();


        for(int i=0;i<10;i++) {
            q.addToQueue(  new d_t(i,i*0.111,i-1));
        }
       assertEquals(16,q.getActualSize());
        assertEquals(10,q.getSize());
       d_t[] d=q.getQ();
       for(int i=0;i<10;i++){
           assertEquals(i,d[i].node);
           assertEquals(i*0.111,d[i].odl);
           assertEquals(i-1,d[i].parent);
       }

    }
    @Test
    public void QueueTest_popFromQueue(){
        Queue q=new Queue();


        for(int i=0;i<10;i++) {
            q.addToQueue(  new d_t(i,i*0.111,i-1));
        }
        assertEquals(16,q.getActualSize());
        assertEquals(10,q.getSize());

        for(int i=0;i<10;i++){
            d_t d=q.popFromQueue();

            assertEquals(i,d.node);
            assertEquals(i*0.111,d.odl);
            assertEquals(i-1,d.parent);
        }

    }


}