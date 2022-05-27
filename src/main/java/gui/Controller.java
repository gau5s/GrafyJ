package gui;

import init.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

import javafx.scene.control.TextField;

import java.io.IOException;


public class Controller {
    @FXML
    private TextField wymiarytext;
    @FXML
    private TextField wartoscitext;
    @FXML
    private Button generujbutton;
    @FXML
    private TextField pathtext;
    @FXML
    private Button wczytajbutton;
    @FXML
    private Button zapiszbutton;
    @FXML
    private Button bfsbutton;
    @FXML
    private CheckBox dijcheck;
    @FXML
    private TextField dialog;
    @FXML
    private TextField bfstext;

    private Graph g;

    @FXML
    public void generuj() {
        try {
            String a = wymiarytext.getText();
            String b = wartoscitext.getText();
            String[] wym = a.split(":");
            String[] war = b.split(":");
            int h=Integer.parseInt(wym[0]);
            int w=Integer.parseInt(wym[1]);
            double min=Double.parseDouble(war[0]);
            double max=Double.parseDouble(war[1]);
            try {
                if (h < 1 || w < 1 )
                    throw new MyException("Wymiary grafu muszą być dodatnie", null, 0);
                if (min < 0 || max < 0 || min > max)
                    throw new MyException("Zakres wartości musi być dodatni i min<max", null, 0);
            }catch(MyException e){
                dialog.setText(e.getMessage());
            }
            g = new Graph(h,w,min,max);
            dialog.setText("Wygenerowano graf");
            g.printGraph();

        }catch(ArrayIndexOutOfBoundsException | NumberFormatException e){
            dialog.setText("Podano złe dane wejściowe do generacji grafu");
        }

    }

    @FXML
    public void wczytaj() {
        try {
            g = ReadGraph.readGraph(pathtext.getText());
            g.printGraph();
            dialog.setText("Wczytano graf");
        }catch (IOException e){
            dialog.setText(e.getMessage());
        }
        catch (MyException e){
            dialog.setText(e.getMessage());
        }
    }


    @FXML
    public void bfsg() {
        if(g==null)
            bfstext.setText("Brak grafu");
        else {
            bfs b = new bfs(g);
            boolean b1 = b.bfsRun();
            if (b1 == true)
                bfstext.setText("Spójny");
            else
                bfstext.setText("Niespójny");
        }
    }
    @FXML
    public void zapisz() {
        if(g==null)
            dialog.setText("Brak grafu");
        else {
            try{
                g.writeToFile(pathtext.getText());
                dialog.setText("Zapisano graf");
            }catch (IOException e){
                dialog.setText(e.getMessage());
            }

        }
    }

    @FXML
    public void dij() {

    }
}
