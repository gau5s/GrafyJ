package Init;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ReadGraph {

    public static Graph readGraph(String path) throws IOException {
        int h = 0, w = 0;
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        if ((line = br.readLine()) != null) {
            String[] f = line.split("\\s+");
            try {
                h = Integer.parseInt(f[0]);
                w = Integer.parseInt(f[1]);
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                System.err.println("Na poczatku pliku powinny sie znajdowac wymiary grafu");
            }
        }
        System.out.printf("Wymiary grafu: %d X %d \n", h, w);
        Graph g = new Graph(h, w);

        int ilepolaczen, ilewartosci;
        for (int i = 0; i < h * w; i++) {
            ilepolaczen = 0;
            ilewartosci = 0;
            if ((line = br.readLine()) != null) {
                String[] f = line.split("\\s+");

                for (int j = 0; j < f.length; j++) {
                    if (ilepolaczen > 4 || ilewartosci > 3) {
                        break;
                    }
                    if (f[j] != null) {
                        Scanner s = new Scanner(f[j]);
                        if (s.hasNext()) {

                            try {
                                if (s.hasNextInt()) {
                                    int n = s.nextInt();
                                    if (n >= 0 && n < w * h) {
                                        g.connectNode(i, ilepolaczen, n);
                                        ilepolaczen++;
                                    }

                                } else {
                                    String[] fs = f[j].split(":");
                                    Double x = Double.parseDouble(fs[1]);
                                    g.setVal(i, ilewartosci, x);
                                    ilewartosci++;
                                }
                            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                                System.err.println("Linia " + (i + 2) + " jest błędna");
                                throw e;
                            }
                        }
                        s.close();
                    }

                }

            }

        }
        return g;
    }

}
