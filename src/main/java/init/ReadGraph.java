package init;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ReadGraph {

    public static Graph readGraph(String path) throws IOException, MyException {
        try {
            int h = 0, w = 0;
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            if ((line = br.readLine()) != null) {
                String[] f = line.split("\\s+");
                try {
                    h = Integer.parseInt(f[0]);
                    w = Integer.parseInt(f[1]);
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                    throw new MyException("Na poczatku pliku powinny sie znajdowac wymiary grafu",path,0);
                }
            }

            Graph g = new Graph(h, w);

            int ilepolaczen, ilewartosci;
            boolean czyByloJuzPierwszePolaczenie = false;
            for (int i = 0; i < h * w; i++) {
                ilepolaczen = 0;
                ilewartosci = 0;
                if ((line = br.readLine()) != null) {
                    String[] f = line.split("\\s+");

                    for (int j = 0; j < f.length; j++) {
                        if (ilepolaczen > 4 || ilewartosci > 4) {
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
                                        }else{
                                            throw new MyException("W linii " + (i+2) + " połączenie "+n+" jest bledne",path,i+2);
                                        }

                                    } else {
                                        String[] fs = f[j].split(":");
                                        double x = Double.parseDouble(fs[1]);
                                        if(x<0)
                                            throw new MyException("W linii " + (i+2) + " waga krawedzi jest ujemna ",path,i+2);
                                        g.setVal(i, ilewartosci, x);

                                        if(x > g.getMaxValEdg()){
                                            g.setMaxValEdg(x);
                                            if(!czyByloJuzPierwszePolaczenie) {
                                                g.setMinValEdg(x);
                                                czyByloJuzPierwszePolaczenie = true;
                                            }
                                        }
                                        else if( x < g.getMinValEdg() ) {
                                            g.setMinValEdg(x);
                                        }

                                        ilewartosci++;
                                    }
                                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                                    throw new MyException("Linia " + (i + 2) + " jest błędna",path, i + 2);

                                }
                            }
                            s.close();
                        }

                    }

                }

            }
            return g;
        } catch (IOException e) {
            throw new MyException(e.getMessage(),path,0);
        }

    }
}