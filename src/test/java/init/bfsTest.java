
package init;
import org.junit.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class bfsTest {
    


    @Test
    public void bfsTest_spojny() throws MyException, IOException {
        Graph g=ReadGraph.readGraph("src/test/resources/mygraph");
        bfs b=new bfs(g);
        assertTrue(b.bfsRuntest());
    }
    @Test
    public void bfsTest_niespojny() throws MyException, IOException {
        Graph g=ReadGraph.readGraph("src/test/resources/niespojny");
        bfs b=new bfs(g);
        assertFalse(b.bfsRuntest());
    }
    
}
