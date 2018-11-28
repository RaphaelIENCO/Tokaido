package Controller;


import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import java.util.Optional;

public class GameController {
    private Model model;
    private ArrayList<Button> boutonsPlateau;
    private boolean initButton;
    @FXML Button b0;
    @FXML Button b1;
    @FXML Button b2;
    @FXML Button b3;
    @FXML Button b4;
    @FXML Button b5;
    @FXML Button b6;
    @FXML Button b7;
    @FXML Button b8;
    @FXML GridPane grille;

    public GameController(){
        model=new Model();
        boutonsPlateau = new ArrayList<>();
        initButton=false;


    }

    public void actionColor(ActionEvent event){
        Button button=(Button) event.getSource();
        if (button.getId().equals("relais")){
            model.getListJoueur().get(0).setPiocheRelais(true);
            piocheRelais();
        }
        else if (button.getId().equals("source")){
            model.getListJoueur().get(0).setPiocheSource(true);
            piocheSourceChaude();
        }
        else if (button.getId().equals("souvenir")){
            model.getListJoueur().get(0).setPiocheSouvenir(true);
            piocheSouvenir();
        }
        else if (button.getId().equals("rencontre")){
            model.getListJoueur().get(0).setPiocheRencontre(true);
            piocheRencontre();
        }else if (button.getId().equals("temple")){
            model.getListJoueur().get(0).setTemple(true);
            rencontreTemple(button);
        }
        else if(button.getId().contains("ferme")){
            rencontreFerme();
        }
        if (!initButton){
            ajoutBouton();
            initButton=true;
        }
        updatePos(event);
        model.trieJoueur();
        afficheCartes();
        button.setStyle("-fx-background-color: "+model.getListJoueur().get(0).getCouleur()+";");

    }

    private void updatePos(ActionEvent event) {
        Button button = (Button) event.getSource();
        for (int i = 0; i < boutonsPlateau.size(); i++) {
            if (button.equals(boutonsPlateau.get(i))){
                System.out.println(model.getListJoueur().get(0).getNom());
                model.getListJoueur().get(0).setPositions(i);
                System.out.println(model.getListJoueur().get(0).getPositions());
            } else {
                boutonsPlateau.get(i).setStyle("-fx-background-color: gray;"+"-fx-border-style: solid;"+"-fx-border-color: black;");
            }
        }
    }


    /**
     * Partie pioche
     */
    public void piocheRelais() {
            if (!model.getListRepas().isEmpty()){
                if (model.getListJoueur().get(0).isPiocheRelais()){
                    GridPane gridPane = new GridPane();
                    ArrayList<RadioButton> rbox = new ArrayList<RadioButton>();
                    ArrayList<Repas> mesRepas = new ArrayList<Repas>();
                    ToggleGroup group = new ToggleGroup();
                    for (int i=0;i<model.getListJoueur().size()+1;i++){
                        RadioButton radioButton = new RadioButton();
                        mesRepas.add(model.getListRepas().get(i));
                        radioButton.setGraphic(new ImageView("/Vue/Images/"+model.getListRepas().get(i).getNom()+".jpg"));
                        rbox.add(radioButton);
                        radioButton.setToggleGroup(group);
                        gridPane.add(radioButton,i,0);
                    }
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Relais");
                    alert.setHeaderText("Choisissez un repas \n vous ne pouvez pas gouter deux fois le même");
                    alert.setGraphic(gridPane);
                    alert.showAndWait();
                    int indice=0;
                    boolean choix=false;
                    for (RadioButton radioButton : rbox){
                        if (radioButton.isSelected()){
                            choix=true;
                            break;
                        }
                        else  indice++;
                    }
                    if (choix) {
                        if (!model.getListJoueur().get(0).contient(mesRepas.get(indice))){
                            if (model.getListJoueur().get(0).getGold()>=mesRepas.get(indice).getPrix()){
                                model.getListJoueur().get(0).setGold(model.getListJoueur().get(0).getGold()-mesRepas.get(indice).getPrix());
                                model.getListJoueur().get(0).addCarte(mesRepas.get(indice));
                                model.getListRepas().removeAll(mesRepas);
                                mesRepas.remove(indice);
                                model.getListRepas().addAll(mesRepas);
                            }else {
                                messageErreur("Vous n'avez pas les fond nécéssaires pour : \n"+mesRepas.get(indice).getNom());
                            }
                        }else {
                            messageErreur("Vous avez déjà gouté "+mesRepas.get(indice).getNom());
                            piocheRelais();
                        }
                    }else  {
                        model.getListRepas().removeAll(mesRepas);
                        model.getListRepas().addAll(mesRepas);
                    }

                }else messageErreur("Vous n'êtes pas autorisé à piocher des repas");

            }else messageErreur("Plus de carte relais");
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
    }

    public void piocheSouvenir(){
        if (model.getListJoueur().get(0).isPiocheSouvenir()) {
            if (model.getListSouvenir().size() > 3) {
                int prixTotal = 0;
                Souvenirs cartes1 = model.getListSouvenir().get(0);
                Souvenirs cartes2 = model.getListSouvenir().get(1);
                Souvenirs cartes3 = model.getListSouvenir().get(2);

                CheckBox checkBox1 = new CheckBox();
                CheckBox checkBox2 = new CheckBox();
                CheckBox checkBox3 = new CheckBox();

                checkBox1.setGraphic(new ImageView("/Vue/Images/" + cartes1.getNom() + ".jpg"));
                checkBox2.setGraphic(new ImageView("/Vue/Images/" + cartes2.getNom() + ".jpg"));
                checkBox3.setGraphic(new ImageView("/Vue/Images/" + cartes3.getNom() + ".jpg"));


                GridPane grille = new GridPane();
                grille.add(checkBox1, 1, 0);
                grille.add(checkBox2, 2, 0);
                grille.add(checkBox3, 3, 0);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setGraphic(grille);
                alert.setTitle("Echoppe");
                alert.setHeaderText("Veuillez cocher les cartes voulues");
                alert.showAndWait();

                ArrayList<Souvenirs> choice = new ArrayList<Souvenirs>();
                choice.add(cartes1);
                choice.add(cartes2);
                choice.add(cartes3);
                ArrayList<Souvenirs> toAdd = new ArrayList<Souvenirs>();
                if (checkBox1.isSelected()) {
                    choice.remove(cartes1);
                    toAdd.add(cartes1);
                    prixTotal += cartes1.getPrix();
                }
                if (checkBox2.isSelected()) {
                    choice.remove(cartes2);
                    toAdd.add(cartes2);
                    prixTotal += cartes2.getPrix();
                }
                if (checkBox3.isSelected()) {
                    choice.remove(cartes3);
                    toAdd.add(cartes3);
                    prixTotal += cartes3.getPrix();
                }
                if(model.getListJoueur().get(0) instanceof Sasayakko){
                    int prixMin =0;
                    if(toAdd.size()>=2){  // Si le joueur a selectionné 2 souvenirs ou plus
                        prixMin=toAdd.get(0).getPrix(); //prixMin devient le prix du premier d'entre eux
                        for (Souvenirs carte: toAdd){
                            if (prixMin>carte.getPrix()) prixMin = carte.getPrix();  //Si on trouve ensuite un souvenirs valant -cher, on remplace le prix max
                        }
                    }
                    prixTotal -= prixMin;
                }

                if (model.getListJoueur().get(0).getGold() >= prixTotal) {
                    for(Souvenirs carte: toAdd){
                        model.getListSouvenir().remove(carte);
                    }
                    for(Souvenirs carte: choice){
                        model.getListSouvenir().remove(carte);
                        model.getListSouvenir().add(carte);
                    }
                    model.getListJoueur().get(0).getCartes().addAll(toAdd);
                    model.getListJoueur().get(0).setGold(model.getListJoueur().get(0).getGold()-prixTotal);
                    model.getListJoueur().get(0).setPiocheSouvenir(false);

                } else {
                    for(Souvenirs carte: toAdd){
                        model.getListSouvenir().remove(carte);
                        model.getListSouvenir().add(carte);
                    }
                    for(Souvenirs carte: choice){
                        model.getListSouvenir().remove(carte);
                        model.getListSouvenir().add(carte);
                    }
                    messageErreur("Pas assez d'or");
                    piocheSouvenir();
                }
                model.getListSouvenir().remove(cartes1);
                model.getListSouvenir().remove(cartes2);
                model.getListSouvenir().remove(cartes3);

                model.getListSouvenir().add(cartes1);
                model.getListSouvenir().add(cartes2);
                model.getListSouvenir().add(cartes3);

            } else messageErreur("Pas assez de carte");
        }else messageErreur("Vous ne pouvez pas piocher");
        model.getListJoueur().get(0).setPiocheSouvenir(false);
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
    }

    private void rencontreTemple(Button button){
        if(model.getListJoueur().get(0).getGold()>0) {
            if (model.getListJoueur().get(0).isTemple()) {
                ArrayList<Integer> choix = new ArrayList<Integer>();
                choix.add(1);
                choix.add(2);
                choix.add(3);

                String nomImage = "/Vue/Images/Temple.png";
                ImageView image = new ImageView(nomImage);

                ChoiceDialog<Integer> dialog = new ChoiceDialog<Integer>(1, choix);

                dialog.setGraphic(image);
                dialog.setTitle("Temple");
                dialog.setHeaderText("Combien voulez vous donner au temple ?");
                dialog.setContentText("Donnation");

                Optional<Integer> result = dialog.showAndWait();
                if (result.isPresent()) {
                    if(model.getListJoueur().get(0).getGold() >= result.get()) {
                        model.getListJoueur().get(0).setGold(model.getListJoueur().get(0).getGold() - result.get());
                        model.getListJoueur().get(0).setPoints(model.getListJoueur().get(0).getPoints() + result.get());
                        model.getListJoueur().get(0).setOrTemple(model.getListJoueur().get(0).getOrTemple() + result.get());
                        model.getListJoueur().get(0).setTemple(false);
                    }else{
                        messageErreur("Vous n'avez pas autant d'argent...");
                        rencontreTemple(button);
                    }
                }else {
                    messageErreur("Vous devez faire un don !");
                    rencontreTemple(button);
                }
            }
        }else{
            messageErreur("Vous n'avez plus d'or, allez à un autre arrêt");
            button.setStyle("-fx-background-color: gray;");
        }
    }

    private void rencontreFerme() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ferme");
        alert.setHeaderText(null);
        alert.setGraphic(new ImageView("/Vue/Images/Ferme.png"));
        alert.setContentText("Vous arrivé à une ferme \n vous gagnez 3 pièce d'or");
        model.getListJoueur().get(0).setGold(model.getListJoueur().get(0).getGold()+3);
        alert.showAndWait();
    }



    /**
     * Partie Affichage
     */
    private void afficheCartes(){
        model.getListJoueur().get(0).trierCarte();
        grille.getChildren().clear();
        for (int i=0;i<model.getListJoueur().size();i++){
            Text descriptif = new Text(model.getListJoueur().get(i).getNom()+" \n or : "+model.getListJoueur().get(i).getGold()+" \n points : "+model.getListJoueur().get(i).getPoints());
            grille.add(descriptif,0,i);
            grille.add(new ImageView("/Vue/Images/"+model.getListJoueur().get(i).getNom()+".jpg"),1,i);
            for (int j=0;j<model.getListJoueur().get(i).getCartes().size();j++){
                grille.add(new ImageView("/Vue/Images/"+model.getListJoueur().get(i).getCartes().get(j).getNom()+".jpg"),j+2,i);
            }
        }
    }


    private void messageErreur(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void ajoutBouton() {
        boutonsPlateau.add(b0);
        boutonsPlateau.add(b1);
        boutonsPlateau.add(b2);
        boutonsPlateau.add(b3);
        boutonsPlateau.add(b4);
        boutonsPlateau.add(b5);
        boutonsPlateau.add(b6);
        boutonsPlateau.add(b7);
        boutonsPlateau.add(b8);

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

    public void restart() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Vue/luncher.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) b1.getScene().getWindow();
        Scene scene = new Scene(root);
        Stage secondaryStage = new Stage();
        secondaryStage.setScene(scene);
        secondaryStage.setMaximized(false);
        secondaryStage.setResizable(true);
        secondaryStage.setTitle("Launcher");
        secondaryStage.show();
        stage.close();


    }

    public void showReglement() {
        LauncherController.reglement();
    }

}
