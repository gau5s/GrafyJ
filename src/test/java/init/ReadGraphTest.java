package init;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReadGraphTest {

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testReadGraph_correctGraph() throws Exception {

        String path = "src/test/resources/mygraph";

        Graph g = ReadGraph.readGraph(path);
        Node result = g.getEdg(0, 3);
        assertEquals(null, result);
        double a = g.getVal(0, 1);
        assertEquals(0.2187532451857941, a, 0);

    }

    @Test
    public void testReadGraph_wrongGraphDimensions() throws Exception {
        Exception e=assertThrows(MyException.class, () -> {
            ReadGraph.readGraph("src/test/resources/wrongGraphDimensions");
        });
         assertEquals(true, e.getMessage().contains("wymiary"));
    }

    @Test
    public void testReadGraph_wrongGraphDimensions2() throws Exception {
        Exception e=assertThrows(MyException.class, () -> {
            ReadGraph.readGraph("src/test/resources/wrongGraphDimensions2");
        });
         assertEquals(true, e.getMessage().contains("wymiary"));
    }

    @Test
    public void testReadGraph_wrongCharacter() throws Exception {
        Exception e=assertThrows(MyException.class, () -> {
            ReadGraph.readGraph("src/test/resources/wrongCharacter");
        });
         assertEquals(true, e.getMessage().contains("dna"));
    }

    @Test
    public void testReadGraph_negativeValue() throws Exception {
        Exception e=assertThrows(MyException.class, () -> {
            ReadGraph.readGraph("src/test/resources/negativeValue");
        });
        assertEquals(true, e.getMessage().contains("ujemna"));
    }

}
