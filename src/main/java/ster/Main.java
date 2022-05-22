package ster;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import gui.*;
import init.*;


public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane load = FXMLLoader.load(getClass().getResource("/MainView.fxml"));
        BackgroundFill backgroundFill = new BackgroundFill(Color.gray(0.3), CornerRadii.EMPTY, Insets.EMPTY);
        load.setBackground(new Background(backgroundFill));

        Scene scene = new Scene(load);
        stage.setScene(scene);
        stage.setTitle("GrafyJ");
        stage.show();

        DijkstraGraph gr = new DijkstraGraph(10,10,0,10);
        Drawer.draw(load, 0, 600, 60, 350, gr);
        Drawer.drawDijkstra(load, gr, 1, 97);
    }

}
