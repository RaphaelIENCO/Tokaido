package Vue;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("luncher.fxml"));
        primaryStage.setTitle("LuncherV1 Tokaido");
        primaryStage.setScene(new Scene(root, 500, 550));
        primaryStage.setMaximized(false);
        primaryStage.setResizable(true);
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
