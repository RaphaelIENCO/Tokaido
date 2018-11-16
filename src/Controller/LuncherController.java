package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.Objects;

public class LuncherController {


    @FXML
    public void startGame(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Vue/plateau.fxml")));
        Scene scene = new Scene(root);
        Stage secondaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        secondaryStage.setScene(scene);
        secondaryStage.setMaximized(true);
        secondaryStage.setResizable(true);
        secondaryStage.setTitle("Tokaido");
        secondaryStage.show();
    }
}
