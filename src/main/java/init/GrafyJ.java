
package init;

import java.io.FileNotFoundException;
import java.io.IOException;



public class GrafyJ {

 
    public static void main(String[] args) throws FileNotFoundException, IOException {
       if (args.length < 1) {
            System.err.println("Potrzebuję nazwy pliku z grafem");
            System.exit(1);
        }
       Graph g=ReadGraph.readGraph(args[0]);
       g.printGraph();
       
       
       
              
    }
    
}
