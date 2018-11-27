package Controller;

import Model.Model;
import Model.Joueur;
import Model.Kinko;
import Model.Sasayakko;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Optional;

public class LauncherController {
    boolean selectJoueur=false;
    private Model model;
    @FXML
    Button commencerP;
    @FXML
    Label labelJ1;
    @FXML
    Label labelJ2;

    public LauncherController(){
        this.model = new Model();
    }

    @FXML
    public void fenetreLauncher() throws IOException {
        if(selectJoueur){

            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Vue/plateau.fxml"));
            Parent root = loader.load();
            System.out.println("loader créer");

            Scene scene = new Scene(root);
            Stage secondaryStage = new Stage();
            loader.<GameController>getController().setData(model);
            secondaryStage.setScene(scene);
            secondaryStage.setMaximized(false);
            secondaryStage.setResizable(true);
            secondaryStage.setTitle("Tokaido");
            secondaryStage.show();
            Stage stage = (Stage) commencerP.getScene().getWindow();
            stage.close();
        }else{ messageErreur("Veuillez selectionner un joueur");
        }
    }

    public void selectionJoueur(){
        ArrayList<String> choix = new ArrayList<String>();
        choix.add("Kinko");
        choix.add("Sasayakko");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("Kinko", choix);
        dialog.setTitle("Choix des joueurs !");
        dialog.setHeaderText("Start");
        dialog.setContentText("Choisissez un joueur.");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            if(selectJoueur){
                if(result.get()=="Kinko") {
                    model.addJoueur(new Kinko());
                    labelJ2.setText("Kinko selectionné");
                    labelJ2.setVisible(true);
                }else{
                    model.addJoueur(new Sasayakko());
                    labelJ2.setText("Sasayakko selectionné");
                    labelJ2.setVisible(true);
                }
            }else{
                if(result.get()=="Kinko") {
                    model.addJoueur(new Kinko());
                    labelJ1.setText("Kinko selectionné");
                    selectJoueur = true;
                }else{
                    model.addJoueur(new Sasayakko());
                    labelJ1.setText("Sasayakko selectionné");
                    selectJoueur = true;
                }
            }

        }
    }

    public void messageErreur(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void showReglement(){
        reglement();
    }

    static void reglement() {
        if( Desktop.isDesktopSupported() )
        {
            new Thread(() -> {
                try {
                    Desktop.getDesktop().browse( new URI( "http://www.funforge.fr/US/files/tokaido/Tokaido_rules_FR_LD.pdf" ) );
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }).start();
        }
    }

    public void leaveGame(){
        System.exit(0);
    }
}
