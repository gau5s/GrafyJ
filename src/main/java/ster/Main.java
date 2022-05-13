package ster;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent load = FXMLLoader.load(getClass().getResource("/MainView.fxml"));
        Scene scene = new Scene(load);
        stage.setScene(scene);
        stage.setTitle("GrafyJ");
        stage.show();
    }
}
