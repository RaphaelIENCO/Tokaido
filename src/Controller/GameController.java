package Controller;


import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;
import java.util.Optional;

public class GameController {
    private Model model;
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
    @FXML Label scoreLabel;
    @FXML Label goldLabel;
    @FXML Label nameLabel;

    public GameController(){
        model=new Model();
        boutonsPlateau = new ArrayList<>();
        ajoutBouton();
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
    public void actionColor(ActionEvent event){
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
     * Partie pioche
     */
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
    public void testSouvenir(){
        if (model.getListSouvenir().size()>3) {
            Souvenirs cartes1 = model.getListSouvenir().get(0);
            Souvenirs cartes2 = model.getListSouvenir().get(1);
            Souvenirs cartes3 = model.getListSouvenir().get(2);
            CheckBox checkBox1 = new CheckBox();
            CheckBox checkBox2 = new CheckBox();
            CheckBox checkBox3 = new CheckBox();

            checkBox1.setGraphic(new ImageView("/Vue/Images/"+cartes1.getNom()+".jpg"));
            checkBox2.setGraphic(new ImageView("/Vue/Images/"+cartes2.getNom()+".jpg"));
            checkBox3.setGraphic(new ImageView("/Vue/Images/"+cartes3.getNom()+".jpg"));


            GridPane grille = new GridPane();
            grille.add(checkBox1,1,0);
            grille.add(checkBox2,2,0);
            grille.add(checkBox3,3,0);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setGraphic(grille);
            alert.setHeaderText("Veuillez cocher les cartes voulues");
            alert.showAndWait();
        } else messageErreur("Pas assez de carte");


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


    /**
     * Partie Affichage
     */
    public void updateScore(){
        goldLabel.setText(model.getListJoueur().get(0).getGold()+"");
        nameLabel.setText(model.getListJoueur().get(0).getNom());
        scoreLabel.setText(model.getListJoueur().get(0).getPoints()+"");
    }
    public void viewCards(){
        GridPane grid = new GridPane();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Vos cartes:");
        if (!model.getListJoueur().get(0).getCartes().isEmpty()) {
            for (int i = 0; i < model.getListJoueur().get(0).getCartes().size(); i++) {
                grid.add(new ImageView(new Image("Vue/Images/" + model.getListJoueur().get(0).getCartes().get(i).getNom() + ".jpg")), i, 0);
            }
            alert.setGraphic(grid);
        }else alert.setContentText("Pas de cartes");
        grid.alignmentProperty();
        alert.showAndWait();
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

    /**
     * Partie menu
     */
    public void leaveGame(){
        System.exit(0);
    }
    public void restart(){
    }
    public void showReglement(){
        LauncherController.reglement();

    }

}
