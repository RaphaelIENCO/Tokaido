package Vue;

import Model.Model;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private Model model;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("luncher.fxml"));
        primaryStage.setTitle("LauncherV1 Tokaido");
        primaryStage.setScene(new Scene(root, 200, 320));
        primaryStage.setMaximized(false);
        primaryStage.setResizable(true);
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
