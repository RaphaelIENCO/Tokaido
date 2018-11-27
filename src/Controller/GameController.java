package Controller;


import Model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class GameController {
    private Model model;
    private Label nomJ1;
    boolean selectJoueur = false;
    private ArrayList<Button> boutonsPlateau;

    @FXML Button button1;
    @FXML Button button2;
    @FXML Button button3;
    @FXML Button button4;
    @FXML Button button5;
    @FXML Button button6;
    @FXML Button button7;
    @FXML Button button8;
    @FXML Button button9;
    @FXML Button button10;
    @FXML Button button11;
    @FXML Button button12;
    @FXML Button button13;
    @FXML Button button14;
    @FXML Label labelJ1;
    @FXML Label labelJ2;
    @FXML Button commencerP;
    @FXML Label scoreLabel;
    @FXML Label goldLabel;
    @FXML Label nameLabel;

    public GameController(){
        model=new Model();
    }

    public void survolEntered(MouseEvent event){
        Button button =(Button) event.getSource();
        if (button.getId().contains("true")){
            button.setStyle("-fx-background-color: green;"+"-fx-border-style: dashed;"+"-fx-border-color: red;");
        } else button.setStyle("-fx-background-color: gray;"+"-fx-border-style: dashed;"+"-fx-border-color: red;");

}

    public void survolExited(MouseEvent event){
        Button button =(Button) event.getSource();
        if (button.getId().contains("true")){
            button.setStyle("-fx-background-color: green;"+"-fx-border-style: solid;"+"-fx-border-color: black;");
        } else button.setStyle("-fx-background-color: gray;"+"-fx-border-style: solid;"+"-fx-border-color: black;");



    }
    public void actionColor(javafx.event.ActionEvent event){
        Button button=(Button) event.getSource();
        if (button.getId().contains("relais")){
            model.getListJoueur().get(0).setPiocheRelais(true);
            piocheRelais();
        }
        else if (button.getId().contains("source")){
            model.getListJoueur().get(0).setPiocheSource(true);
            piocheSourceChaude();
        }
        else if (button.getId().contains("souvenir")){
            model.getListJoueur().get(0).setPiocheSouvenir(true);
            piocheSouvenir();
        }
        else if (button.getId().contains("rencontre")){
            model.getListJoueur().get(0).setPiocheRencontre(true);
            piocheRencontre();
        }
         button.setId(button.getId()+"true");
        button.setStyle("-fx-background-color: green;");

    }

    /**
     * Partie menu
     */
    public void leaveGame(){
        System.exit(0);
    }
    public void restart(){
    }
    public void showReglement(){
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
    public void piocheRelais() {
        if (!model.getListRepas().isEmpty()) {
            if (model.getListJoueur().get(0).isPiocheRelais()) {
                Repas repas = model.getListRepas().get(0);
                String nomImage = "/Vue/Images/" + repas.getNom() + ".jpg";
                Alert show = new Alert(Alert.AlertType.CONFIRMATION);
                ImageView imageView = new ImageView(new Image(nomImage));
                show.setGraphic(imageView);
                show.setTitle("Carte repas");
                show.setHeaderText("prix : " + repas.getPrix());
                show.setContentText("Il vous reste : " + model.getListJoueur().get(0).getGold() + " or");

                ButtonType btnAcheter = new ButtonType("Acheter (-" + repas.getPrix() + " or)");
                ButtonType btnRefuser = new ButtonType("Refuser", ButtonBar.ButtonData.CANCEL_CLOSE);
                show.getButtonTypes().setAll(btnAcheter, btnRefuser);
                Optional<ButtonType> choice = show.showAndWait();
                model.getListRepas().remove(repas);
                if (choice.get() == btnAcheter && model.getListJoueur().get(0).getGold() >= repas.getPrix()) {
                    model.getListJoueur().get(0).setGold(model.getListJoueur().get(0).getGold() - repas.getPrix());
                    model.getListJoueur().get(0).addCarte(repas);
                } else if (choice.get() == btnAcheter && model.getListJoueur().get(0).getGold() < repas.getPrix()) {
                    model.getListRepas().add(repas);
                    messageErreur("Vous n'avez pas l'or nécéssaire");
                }
                model.getListJoueur().get(0).setPiocheRelais(false);
            } else {
                messageErreur("Vous n'êtes pas autorisé à piocher une carte relais");
            }
        }else messageErreur("Plus de cartes repas");
        updateScore();
    }

    public void piocheSourceChaude(){
        if (!model.getListSource().isEmpty()) {
            if (model.getListJoueur().get(0).isPiocheSource()) {
                Sources source = model.getListSource().get(0);
                String nomImage = "/Vue/Images/" + source.getNom() + ".jpg";

                Alert show = new Alert(Alert.AlertType.INFORMATION);
                ImageView imageView = new ImageView(new Image(nomImage));
                show.setGraphic(imageView);
                show.setTitle("Source chaude");
                show.setHeaderText("Vous rapporte : " + source.getPoint());
                show.showAndWait();
                model.getListJoueur().get(0).addCarte(source);
                model.getListSource().remove(source);
                model.getListJoueur().get(0).setPiocheSource(false);
            } else {
                messageErreur("impossible de piocher une carte source chaude");
            }
        }else messageErreur("Plus de carte source chaude");
        updateScore();
    }
    public void piocheSouvenir(){
        if (!model.getListSouvenir().isEmpty()) {
            if (model.getListJoueur().get(0).isPiocheSouvenir()) {
                Souvenirs souvenirs = model.getListSouvenir().get(0);
                String nomImage = "/Vue/Images/" + souvenirs.getNom() + ".jpg";

                Alert show = new Alert(Alert.AlertType.CONFIRMATION);
                ImageView imageView = new ImageView(new Image(nomImage));
                show.setGraphic(imageView);
                show.setTitle("Souvenirs");
                show.setHeaderText("prix : " + souvenirs.getPrix());
                show.setContentText("Il vous reste : " + model.getListJoueur().get(0).getGold() + " or");

                ButtonType btnAcheter = new ButtonType("Acheter (-" + souvenirs.getPrix() + " or)");
                ButtonType btnRefuser = new ButtonType("Refuser", ButtonBar.ButtonData.CANCEL_CLOSE);
                show.getButtonTypes().setAll(btnAcheter, btnRefuser);
                Optional<ButtonType> choice = show.showAndWait();
                model.getListSouvenir().remove(souvenirs);
                if (choice.get() == btnAcheter && model.getListJoueur().get(0).getGold() >= souvenirs.getPrix()) {
                    model.getListJoueur().get(0).setGold(model.getListJoueur().get(0).getGold() - souvenirs.getPrix());
                    model.getListJoueur().get(0).addCarte(souvenirs);

                } else if (choice.get() == btnAcheter && model.getListJoueur().get(0).getGold() < souvenirs.getPrix()) {
                    model.getListSouvenir().add(souvenirs);
                    messageErreur("Vous n'avez pas l'or nécessaire");
                }
                model.getListJoueur().get(0).setPiocheSouvenir(false);
            } else {
                messageErreur("Vous ne pouvez pas piocher de carte souvenirs!");
            }
        }else messageErreur("Il n'y à plus de carte souvenir");
        updateScore();
}

    public void piocheRencontre(){
        if(!model.getListRecontre().isEmpty()) {
            if (model.getListJoueur().get(0).isPiocheRencontre()) {
                Rencontre rencontre = model.getListRecontre().get(0);
                String nomImage = "/Vue/Images/" + rencontre.getNom() + ".jpg";
                Alert show = new Alert(Alert.AlertType.INFORMATION);
                ImageView imageView = new ImageView(new Image(nomImage));
                show.setGraphic(imageView);
                show.setTitle("Rencontre");
                show.setHeaderText("Vous avez rencontré " + rencontre.getNom());
                show.setContentText("Effets  :" + rencontre.getDescription());
                show.showAndWait();
                rencontre.rencontre(model.getListJoueur().get(0));
                model.getListJoueur().get(0).addCarte(rencontre);
                model.getListRecontre().remove(rencontre);
                model.getListJoueur().get(0).setPiocheRencontre(false);
            } else {
                messageErreur("Vous ne pouvez pas piocher de carte Rencontre");
            }
        }else messageErreur("Plus de cartes rencontre");
        updateScore();
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

    public void updateScore(){
        goldLabel.setText(model.getListJoueur().get(0).getGold()+"");
        nameLabel.setText(model.getListJoueur().get(0).getNom());
        scoreLabel.setText(model.getListJoueur().get(0).getPoints()+"");
    }
    public void viewCards(){


    }



    public void messageErreur(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void ajoutBouton() {
        boutonsPlateau.add(button1);
        boutonsPlateau.add(button2);
        boutonsPlateau.add(button3);
        boutonsPlateau.add(button4);
        boutonsPlateau.add(button5);
        boutonsPlateau.add(button6);
        boutonsPlateau.add(button7);
        boutonsPlateau.add(button8);
        boutonsPlateau.add(button9);
        boutonsPlateau.add(button10);
        boutonsPlateau.add(button11);
        boutonsPlateau.add(button12);
        boutonsPlateau.add(button13);
        boutonsPlateau.add(button14);
    }


    public void setData(Model m){
        this.model =m;
    }
}
