package Controller;


import Model.*;
import Model.sonTokaido.Son;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;


public class GameController {
    private Model model;
    private ArrayList<Button> boutonsPlateau;
    @FXML Button b010;
    @FXML Button b011;
    @FXML Button b012;
    @FXML Button b013;
    @FXML Button b544;
    @FXML Button b543;
    @FXML Button b542;
    @FXML Button b541;
    @FXML Button b511;
    @FXML Button b521;
    @FXML Button b471;
    @FXML Button b481;
    @FXML Button b431;
    @FXML Button b451;
    @FXML Button b414;
    @FXML Button b413;
    @FXML Button b412;
    @FXML Button b411;
    @FXML Button b371;
    @FXML Button b401;
    @FXML Button b341;
    @FXML Button b361;
    @FXML Button b301;
    @FXML Button b321;
    @FXML Button b0;
    @FXML Button b101;
    @FXML Button b1;
    @FXML Button b144;
    @FXML Button b143;
    @FXML Button b142;
    @FXML Button b141;
    @FXML Button b2;
    @FXML Button b3;
    @FXML Button b4;
    @FXML Button b5;
    @FXML Button b501;
    @FXML Button b6;
    @FXML Button b601;
    @FXML Button b7;
    @FXML Button b701;
    @FXML Button b8;
    @FXML Button b901;
    @FXML Button b9;
    @FXML Button b10;
    @FXML Button b111;
    @FXML Button b11;
    @FXML Button b12;
    @FXML Button b13;
    @FXML Button b14;
    @FXML Button b15;
    @FXML Button b16;
    @FXML Button b171;
    @FXML Button b17;
    @FXML Button b181;
    @FXML Button b18;
    @FXML Button b191;
    @FXML Button b19;
    @FXML Button b201;
    @FXML Button b20;
    @FXML Button b221;
    @FXML Button b21;
    @FXML Button b22;
    @FXML Button b23;
    @FXML Button b241;
    @FXML Button b24;
    @FXML Button b25;
    @FXML Button b26;
    @FXML Button b271;
    @FXML Button b272;
    @FXML Button b273;
    @FXML Button b274;
    @FXML Button b27;
    @FXML Button b28;
    @FXML Button b29;
    @FXML Button b30;
    @FXML Button b31;
    @FXML Button b32;
    @FXML Button b33;
    @FXML Button b34;
    @FXML Button b35;
    @FXML Button b36;
    @FXML Button b37;
    @FXML Button b38;
    @FXML Button b39;
    @FXML Button b40;
    @FXML Button b41;
    @FXML Button b42;
    @FXML Button b43;
    @FXML Button b44;
    @FXML Button b45;
    @FXML Button b46;
    @FXML Button b47;
    @FXML Button b48;
    @FXML Button b49;
    @FXML Button b50;
    @FXML Button b51;
    @FXML Button b52;
    @FXML Button b53;
    @FXML Button b54;
    @FXML Label affichageJoueur;
    private boolean relanceRelais;
    private boolean equilibrage;
    private FXMLLoader loader;
    Thread sonMusique = new Son("src/Model/sonTokaido/musique.wav");
    ArrayList<Repas> mesRepas;


    /*
        Constructeur du controller, instancie les variables et le loader pour la seconde fenetre
    */
    public GameController(){
        mesRepas = new ArrayList<Repas>();
        boutonsPlateau = new ArrayList<>();
        relanceRelais = false;
        equilibrage = false;
        sonMusique.start();
        loader = new FXMLLoader(getClass().getClassLoader().getResource("Vue/affichageCarte.fxml"));

    }

    /*
    Methode controllant les differents bouton du plateau
     */
    public void actionColor(ActionEvent event){
        Button button=(Button) event.getSource();
        int indice=0;
        for (int i = 0; i < boutonsPlateau.size(); i++) {
            if (button.equals(boutonsPlateau.get(i)) && i<=model.getListJoueur().get(0).getPositions()){
                messageErreur("Vous devez avancer");
                return;
            }else if (button==boutonsPlateau.get(i)){
                indice=i;
            }
        }
        for (Joueur joueur :model.getListJoueur()){
            if (joueur.getPositions()==indice){
                messageErreur("Il y a déjà quelqu'un");
                return;
            }
        }
        if (indice>28 && !model.getListJoueur().get(0).isRelais1()){
            messageErreur("Vous devez vous arreter au premier relais");
            return;
        }
        if (indice>51 && !model.getListJoueur().get(0).isRelais2()){
            messageErreur("Vous devez vous arreter au second relais");
            return;
        }
        if (indice>75 && !model.getListJoueur().get(0).isRelais3()){
            messageErreur("Vous devez vous arreter au troisieme relais");
            return;
        }
        updatePos(event);
        if (button.getId().contains("relais")) {
            piocheRelais(event);
            boolean notClear=true;
            switch (button.getId()){
                case "relais1":
                    for(Joueur j: model.getListJoueur()) if(!j.isRelais1()) notClear=false;
                    if(notClear){
                        mesRepas.clear();
                    }
                    break;
                case "relais2":
                    for(Joueur j: model.getListJoueur()) if(!j.isRelais2()) notClear=false;
                    if(notClear){
                        mesRepas.clear();
                    }
                    break;
                case "relais3":
                    for(Joueur j: model.getListJoueur()) if(!j.isRelais3()) notClear=false;
                    if(notClear){
                        mesRepas.clear();
                    }
                    break;
                case "relais4":
                    for(Joueur j: model.getListJoueur()) if(!j.isRelais4()) notClear=false;
                    if(notClear){
                        mesRepas.clear();
                    }
                    break;
            }
        }

        if (!model.getListJoueur().get(0).getNom().equals("VoyageurNeutre")) {
             if (button.getId().equals("source")) {
                piocheSourceChaude();
            } else if (button.getId().equals("souvenir")) {
                ;
                piocheSouvenir();
            } else if (button.getId().equals("rencontre")) {
                piocheRencontre();
            } else if (button.getId().equals("temple")) {
                ;
                rencontreTemple(button);
            } else if (button.getId().contains("ferme")) {
                rencontreFerme();
            } else if (button.getId().contains("riziere")) {
                panoramaRiziere();
            } else if (button.getId().contains("montagne")) {
                panoramaMontagne();
            } else if (button.getId().contains("mer")) {
                panoramaMer();
            }
        }
        button.setStyle("-fx-background-color: "+model.getListJoueur().get(0).getCouleur()+";");
        model.trieJoueur();
        model.majScore();
        loader.<RecapController>getController().afficheCartes();
        if (model.getListJoueur().size()>=4) afficheArretDouble();
        afficheArretRelais();
        if (model.getListJoueur().get(0).getPositions()==boutonsPlateau.size()-(model.getListJoueur().size())){
            try {
                finDePartie();
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        affichageJoueur.setText("Au tour de : "+model.getListJoueur().get(0).getNom());
        if(!model.getListJoueur().get(0).getNom().equals("VoyageurNeutre")) affichageJoueur.setGraphic(new ImageView("/Vue/Images/"+model.getListJoueur().get(0).getNom()+"1.jpg"));
        else affichageJoueur.setGraphic(null);
    }


/*
    Methode qui met e jour la position du joueur sur le plateau
 */
    private void updatePos(ActionEvent event) {
        Button button = (Button) event.getSource();
        for (int i = 0; i < boutonsPlateau.size(); i++) {
            if (button.equals(boutonsPlateau.get(i))) {
                model.getListJoueur().get(0).setPositions(i);
            }
        }
        colorieCase();

    }

    /*
    Change la couleur du bouton en fct du joueur present ou non dessus
     */
    public void colorieCase(){
        int indice=0;
        Boolean exist = false;
        for (int i = 0; i < boutonsPlateau.size(); i++) {
            for (int j = 0; j < model.getListJoueur().size(); j++) {
                if (model.getListJoueur().get(j).getPositions() == i) {
                    exist = true;
                    indice=j;
                }
            }
            if (!exist) {
                boutonsPlateau.get(i).setStyle("-fx-background-color: white;");
                exist = false;
            } else {
                boutonsPlateau.get(i).setStyle("-fx-background-color:"+model.getListJoueur().get(indice).getCouleur()+";");
                exist=false;

            }
        }
    }

    private void afficheArretDouble(){
       for (Integer integer:model.getListArretDouble()){
           cacheBouton(integer);
       }
    }

    private void afficheArretRelais(){
        for (Integer integer:model.getListArretRelais()){
            cacheBouton(integer);
        }
    }

    /*
    Methode qui affiche ou non les arrets doubles et les cases relais si un joueur est sur une position integer
     */
    private void cacheBouton(Integer integer) {
        boolean aCacher;
        aCacher=true;
        for (Joueur joueur: model.getListJoueur()){
            if (joueur.getPositions()==integer){
                aCacher=false;
            }
        }
        if (aCacher){
            boutonsPlateau.get(integer-1).setVisible(false);
        } else  boutonsPlateau.get(integer-1).setVisible(true);
    }

    /**
     * Partie pioche
     */

    /*
    Methode qui gere: Le tirage des cartes Repas, et le pouvoir des personnages en rapport avec les relais
     */
    public void piocheRelais(ActionEvent event) {
        if(model.getListJoueur().get(0).getPositions()==28 || model.getListJoueur().get(0).getPositions()==51 || model.getListJoueur().get(0).getPositions()==75 || model.getListJoueur().get(0).getPositions()==98 ){
            for (int i = 0; i < model.getListJoueur().size()+1; i++) {
                mesRepas.add(model.getListRepas().get(i));
            }
        }
        Thread sonPiece = new Son("src/Model/sonTokaido/piece.wav");
        sonPiece.start();
        Button button=(Button)event.getSource();
            if (!model.getListRepas().isEmpty()){
                    int nbCartes=0;
                    switch (button.getId()){
                        case "relais1":
                            model.getListJoueur().get(0).setRelais1(true);
                            break;
                        case "relais2":
                            model.getListJoueur().get(0).setRelais2(true);
                            break;
                        case "relais3":
                            model.getListJoueur().get(0).setRelais3(true);
                            break;
                        case "relais4":
                            model.getListJoueur().get(0).setRelais4(true);
                            break;
                    }
                    if (model.getListJoueur().get(0).getNom().equals("VoyageurNeutre")) return;
                    if (model.getListJoueur().get(0).getNom().equals("Chuubei") && !relanceRelais){
                        piocheRencontre();
                    }
                    if(model.getListJoueur().get(0).getNom().equals("Hiroshige") && !relanceRelais){
                        ToggleGroup group = new ToggleGroup();
                        GridPane grille = new GridPane();
                        RadioButton mer = new RadioButton("Panorama Mer");
                        RadioButton riziere = new RadioButton("Panorama Riziere");
                        RadioButton montagne = new RadioButton("Panorama Montagne");
                        mer.setToggleGroup(group);
                        riziere.setToggleGroup(group);
                        montagne.setToggleGroup(group);
                        if(!model.isMer())grille.add(mer,0,0);
                        if(!model.isRiziere())grille.add(riziere,0,1);
                        if(!model.isMontagne())grille.add(montagne,0,2);

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setGraphic(grille);
                        alert.setTitle("Hiroshige");
                        alert.setHeaderText("Choisissez un panorama : ");
                        alert.showAndWait();

                        if(mer.isSelected()){
                            panoramaMer();
                        }
                        if(riziere.isSelected()){
                            panoramaRiziere();
                        }
                        if(montagne.isSelected()){
                            panoramaMontagne();
                        }
                    }
                    if (model.getListJoueur().get(0).getNom().equals("Satsuki") && !relanceRelais){
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Satsuki");
                        alert.setHeaderText("En tant que satsuki vous avez le droit à un repas gratuit \n que vous pouvez refuser pour acceder au relais normal");
                        alert.setGraphic(new ImageView("/Vue/Images/"+model.getListRepas().get(0).getNom()+".jpg"));
                        if (model.getListJoueur().get(0).contient(model.getListRepas().get(0))){
                            alert.setContentText("Vous l'avez déjà gouté");
                            ButtonType refuser = new ButtonType("Refuser");
                            alert.getButtonTypes().setAll(refuser);
                            Optional<ButtonType> result = alert.showAndWait();
                            return;
                        }else  {
                            alert.setContentText(null);
                            ButtonType accepter = new ButtonType("Accepter");
                            ButtonType refuser = new ButtonType("Refuser");
                            alert.getButtonTypes().setAll(accepter,refuser);
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == accepter) {
                                model.getListJoueur().get(0).addCarte(model.getListRepas().get(0));
                                model.getListRepas().remove(0);
                                return;
                            }
                        }
                    }
                    GridPane gridPane = new GridPane();
                    ArrayList<RadioButton> rbox = new ArrayList<RadioButton>();
                    ToggleGroup group = new ToggleGroup();
                    for (int i=0;i<mesRepas.size();i++){
                        RadioButton radioButton = new RadioButton();
                        radioButton.setGraphic(new ImageView("/Vue/Images/"+mesRepas.get(i).getNom()+".jpg"));
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
                        if (!model.getListJoueur().get(0).contient(mesRepas.get(indice))) {
                            if (model.getListJoueur().get(0).getNom().equals("Kinko")) {
                                if (model.getListJoueur().get(0).getGold() >= (mesRepas.get(indice).getPrix() - 1)) {
                                    model.getListJoueur().get(0).setGold(model.getListJoueur().get(0).getGold() - (mesRepas.get(indice).getPrix() - 1));
                                }else {
                                    messageErreur("Vous n'avez pas les fonds nécéssaires pour : \n"+mesRepas.get(indice).getNom());
                                    relanceRelais=true;
                                    piocheRelais(event);
                                    return;
                                }
                            } else {
                                if (model.getListJoueur().get(0).getGold() >= mesRepas.get(indice).getPrix()) {
                                    model.getListJoueur().get(0).setGold(model.getListJoueur().get(0).getGold() - mesRepas.get(indice).getPrix());

                                } else {
                                    messageErreur("Vous n'avez pas les fonds nécéssaires pour : \n"+mesRepas.get(indice).getNom());
                                    relanceRelais = true;
                                    piocheRelais(event);
                                    return;
                                }
                            }
                            model.getListJoueur().get(0).addCarte(mesRepas.get(indice));
                            model.getListRepas().removeAll(mesRepas);
                            mesRepas.remove(indice);
                            model.getListRepas().addAll(mesRepas);
                        }

                        else {
                            messageErreur("Vous avez déjà gouté "+mesRepas.get(indice).getNom());
                            relanceRelais=true;
                            piocheRelais(event);
                            return;
                        }
                    }else  {
                        model.getListRepas().removeAll(mesRepas);
                        model.getListRepas().addAll(mesRepas);
                    }

                }else messageErreur("Vous n'êtes pas autorisé à piocher des repas");

    }

    /*
    Methode qui gere la pioche des sources chaudes
     */
    private void piocheSourceChaude(){
        Thread sonPiece = new Son("src/Model/sonTokaido/source.wav");
        sonPiece.start();
        if (!model.getListSource().isEmpty()) {
                double a= Math.random();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Source chaude");
                alert.setHeaderText(null);
                if (a<0.9 && model.getListSource().size()>=2 && model.isCreateur()){
                   alert.setHeaderText("Vous rencontrer jean no dans la source chaude \n celui ci vous donne 2 cartes source chaudes");
                   GridPane gridPane = new GridPane();
                   gridPane.add(new ImageView("/Vue/Images/JeanNoel.jpg"),0,0);
                   gridPane.add(new ImageView("/Vue/Images/"+model.getListSource().get(0).getNom()+".jpg"),0,1);
                   gridPane.add(new ImageView("/Vue/Images/"+model.getListSource().get(1).getNom()+".jpg"),1,1);
                   alert.setGraphic(gridPane);
                   model.getListJoueur().get(0).addCarte(model.getListSource().remove(0));
                   model.getListJoueur().get(0).addCarte(model.getListSource().remove(0));
                }else {
                    alert.setGraphic(new ImageView("/Vue/Images/"+model.getListSource().get(0).getNom()+".jpg"));
                    alert.setContentText("Vous vous baignez et gaghez donc une carte source chaude");
                    model.getListJoueur().get(0).addCarte(model.getListSource().remove(0));
                }alert.showAndWait();

        }else messageErreur("Plus de cartes source chaude");
    }

    /*
    Methode qui gere l'achat et le tirage des souvenirs
     */
    private void piocheSouvenir(){
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
                if(model.getListJoueur().get(0).getNom().equals("ZenEmon")){
                    ArrayList<RadioButton> listeButton = new ArrayList<RadioButton>();
                    GridPane grille2 = new GridPane();
                    ToggleGroup group = new ToggleGroup();
                    for (int i = 0; i < toAdd.size(); i++) {
                        RadioButton radioButton = new RadioButton();
                        radioButton.setGraphic(new ImageView("/Vue/Images/" + toAdd.get(i).getNom() + ".jpg"));
                        radioButton.setToggleGroup(group);
                        listeButton.add(radioButton);
                        grille2.add(radioButton,i,0);
                    }


                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setGraphic(grille2);
                    alert2.setTitle("ZenEmon");
                    alert2.setHeaderText("Veuillez cocher la carte que vous voulez payer à 1 piece");
                    alert2.showAndWait();

                    for (int i = 0; i < listeButton.size(); i++) {
                        if(listeButton.get(i).isSelected()){
                            prixTotal-= toAdd.get(i).getPrix();
                            prixTotal+=1;
                        }
                    }
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
                    Thread sonPiece = new Son("src/Model/sonTokaido/piece.wav");
                    sonPiece.start();

                } else {
                    messageErreur("Pas assez d'or");
                    piocheSouvenir();
                    return;
                }
                model.getListSouvenir().remove(cartes1);
                model.getListSouvenir().remove(cartes2);
                model.getListSouvenir().remove(cartes3);

                model.getListSouvenir().add(cartes1);
                model.getListSouvenir().add(cartes2);
                model.getListSouvenir().add(cartes3);

            } else messageErreur("Pas assez de cartes");
    }

    /*
    Methode qui gere la pioche des rencontre, l'affichage et les effets des cartes
     */
    private void piocheRencontre() {
        if (model.getListJoueur().get(0).getNom().equals("Yoshiyasu")) piocheYoshiyasu();
        if (!model.getListRecontre().isEmpty()) {
            if (model.getListJoueur().get(0).getNom().equals("Umegae")){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setGraphic(null);
                alert.setHeaderText("En tant que Umegae vous gagnez une piece et un point");
                alert.setContentText(null);
                alert.showAndWait();
                model.getListJoueur().get(0).setGold(model.getListJoueur().get(0).getGold()+1);
            }

            if(model.getListRecontre().get(0).getNom().equals("Miko")){
                Thread sonHeyFemme = new Son("src/Model/sonTokaido/heyFemme.wav");
                sonHeyFemme.start();
            }else if(model.getListRecontre().get(0).getNom().equals("Samurai")){
                Thread sonSamurai = new Son("src/Model/sonTokaido/samurai.wav");
                sonSamurai.start();
            }else{
                Thread sonHuh = new Son("src/Model/sonTokaido/huh.wav");
                sonHuh.start();
            }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Rencontre");
                alert.setGraphic(new ImageView("/Vue/Images/"+model.getListRecontre().get(0).getNom()+".jpg"));
                alert.setHeaderText("Vous avez rencontré "+model.getListRecontre().get(0).getNom());
                alert.setContentText(model.getListRecontre().get(0).getDescription());
                alert.showAndWait();
                switch (model.getListRecontre().get(0).getNom()){
                    case "Kuge":
                        model.getListJoueur().get(0).setGold(model.getListJoueur().get(0).getGold()+3);
                        break;
                    case "Miko":
                        model.getListJoueur().get(0).setOrTemple(model.getListJoueur().get(0).getOrTemple()+1);
                        break;
                    case "Shokunin":
                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle(null);
                        if (model.getListSouvenir().isEmpty()){
                            alert1.setHeaderText("Plus de cartes souvenir");
                        }else {
                            alert1.setHeaderText("Vous gagnez donc");
                            alert1.setGraphic(new ImageView("/Vue/Images/" + model.getListSouvenir().get(0).getNom() + ".jpg"));
                            model.getListJoueur().get(0).addCarte(model.getListSouvenir().remove(0));
                        }
                        alert1.showAndWait();
                        break;
                    case "Anaibito0":
                        if (model.getListJoueur().get(0).getCartes().contains(model.getListPanoramaRiziere().get(2))){
                            recommencerPanorama("riziere");
                        }else panoramaRiziere();
                        break;
                    case "Anaibito1":
                        if (model.getListJoueur().get(0).getCartes().contains(model.getListPanoramaMer().get(4))){
                            recommencerPanorama("mer");
                        }else panoramaMer();
                        break;
                    case "Anaibito2":
                        if (model.getListJoueur().get(0).getCartes().contains(model.getListPanoramaMontagnes().get(3))){
                            recommencerPanorama("montagne");
                        }else panoramaMontagne();
                        break;
                    case "Raphael":
                        Thread sonRire = new Son("src/Model/sonTokaido/rire.wav");
                        sonRire.start();
                        if(model.getListJoueur().get(0).getGold()-2<0){
                            model.getListJoueur().get(0).setGold(0);
                        }else model.getListJoueur().get(0).setGold(model.getListJoueur().get(0).getGold()-2);
                        break;
                    case "Aurélien":
                        alert.setTitle("Ah l'alsace");
                        alert.setHeaderText("Il vous donne donc :");
                        alert.setContentText(null);
                        if (!model.getListJoueur().get(0).contient(model.getChoucroute())){
                            alert.setGraphic(new ImageView("/Vue/Images/Choucroute.jpg"));
                            model.getListJoueur().get(0).addCarte(model.getChoucroute());
                        }
                        else if (!model.getListJoueur().get(0).contient(model.getFischer())){
                            alert.setGraphic(new ImageView("/Vue/Images/Fischer.jpg"));
                            model.getListJoueur().get(0).addCarte(model.getFischer());
                        }else {
                            alert.setGraphic(null);
                            alert.setHeaderText("Vous avez déjà tout gouter");
                        }
                        alert.showAndWait();
                        break;
                }
            model.getListJoueur().get(0).addCarte(model.getListRecontre().remove(0));


        } else messageErreur("Plus de cartes rencontre");
    }

    /*
    Methode de pioche des rencontres speciale pour Yoshiyasu (Pouvoir de choisir entre 2 rencontre)
     */
    private void piocheYoshiyasu(){
        if (model.getListRecontre().size()>=2) {
            GridPane gridPane = new GridPane();
            RadioButton radioButton1 = new RadioButton();
            RadioButton radioButton2 = new RadioButton();
            ToggleGroup toggleGroup = new ToggleGroup();
            radioButton1.setToggleGroup(toggleGroup);
            radioButton2.setToggleGroup(toggleGroup);
            radioButton1.setGraphic(new ImageView("/Vue/Images/"+model.getListRecontre().get(0).getNom()+".jpg"));
            radioButton2.setGraphic(new ImageView("/Vue/Images/"+model.getListRecontre().get(1).getNom()+".jpg"));
            gridPane.add(radioButton1,0,0);
            gridPane.add(radioButton2,0,1);
            Alert alert= new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("En tant que yoshiyasu vous avez le choix \n entre deux cartes");
            alert.setGraphic(gridPane);
            alert.setContentText(null);
            alert.showAndWait();
            if (radioButton1.isSelected()){
                Rencontre rencontre=model.getListRecontre().get(1);
                model.getListRecontre().remove(rencontre);
                model.getListRecontre().add(rencontre);
            }else if(radioButton2.isSelected()){
                Rencontre rencontre=model.getListRecontre().get(0);
                model.getListRecontre().remove(rencontre);
                model.getListRecontre().add(rencontre);
            }else {
                messageErreur("Vous devez selectionner une carte");
                piocheYoshiyasu();
            }

        }else messageErreur("Pas assez de cartes pour en tirer deux yoshiyasu");

    }

    /*
    Methode en cas de rencontre avec Anaibito si le joueur possede deja le panorama
     */
    private void recommencerPanorama(String s) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Vous avez déja tout les panorama "+s+"\n veuillez en prendre un autre");
        alert.setTitle("Panorama");

        alert.showAndWait();
        alert.setHeaderText(null);
        RadioButton radioButton1 = null;
        RadioButton radioButton2 = null;
        RadioButton radioButton3 = null;
        GridPane gridPane = new GridPane();
        ToggleGroup toggleGroup = new ToggleGroup();
        if (!model.getListJoueur().get(0).contient(model.getListPanoramaRiziere().get(2))){
            radioButton1 = new RadioButton("Riziere");
            radioButton1.setToggleGroup(toggleGroup);
            gridPane.add(radioButton1,0,0);
        }
        if (!model.getListJoueur().get(0).contient(model.getListPanoramaMer().get(4))){
            radioButton2 = new RadioButton("Mer");
            radioButton2.setToggleGroup(toggleGroup);
            gridPane.add(radioButton2,0,1);
        }
        if (!model.getListJoueur().get(0).contient(model.getListPanoramaMontagnes().get(3))){
            radioButton3 = new RadioButton("Montagne");
            radioButton3.setToggleGroup(toggleGroup);
            gridPane.add(radioButton3,0,2);
        }
        if (model.getListJoueur().get(0).contient(model.getListPanoramaRiziere().get(2)) && model.getListJoueur().get(0).contient(model.getListPanoramaMer().get(4)) && model.getListJoueur().get(0).contient(model.getListPanoramaMontagnes().get(3)) ){
            alert.setHeaderText("Vous avez déjà tout les panoramas");
            alert.setGraphic(null);
        }else {
            alert.setGraphic(gridPane);
            alert.setHeaderText("Veuillez choisir le panorama voulu");

        }
        alert.showAndWait();
        if (radioButton1!=null &&radioButton1.isSelected()) panoramaRiziere();
        else if (radioButton2!=null &&radioButton2.isSelected()) panoramaMer();
        else if (radioButton3 !=null && radioButton3.isSelected()) panoramaMontagne();

    }


    /*
    Methode qui gere la pioche des panoramas riziere
     */
    private void panoramaRiziere() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Riziere");
        alert.setContentText(null);

        for (int i = 0; i < model.getListPanoramaRiziere().size(); i++) {
            if (!model.getListJoueur().get(0).getCartes().contains(model.getListPanoramaRiziere().get(i))) {
                model.getListJoueur().get(0).addCarte(model.getListPanoramaRiziere().get(i));
                alert.setGraphic(new ImageView("/Vue/Images/" + model.getListPanoramaRiziere().get(i).getNom() + ".jpg"));
                alert.setHeaderText("Félicitation vous visitez une riziere \n vous obtenez donc :");
                alert.showAndWait();
                if (i==2 && !model.isRiziere()){
                    alert.setHeaderText("Félicitaion vous êtes le premier à débloquer \n tous les panoramas rizières vous gagnez donc :");
                    alert.setGraphic(new ImageView("/Vue/Images/AcomplissementRiziere.jpg"));
                    model.getListJoueur().get(0).addCarte(model.getListAcomplissement().get(0));
                    model.setRiziere(true);
                    alert.showAndWait();
                }
                return;
            }
        }
        alert.setGraphic(null);
        alert.setHeaderText("Vous avez déjà débloqué tous les panoramas rizieres");
        alert.showAndWait();
        }

    /*
    Methode qui gere la pioche des panoramas Montagne
     */
    private void panoramaMontagne() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Montagne");
        alert.setContentText(null);

        for (int i = 0; i < model.getListPanoramaMontagnes().size(); i++) {
        if (!model.getListJoueur().get(0).getCartes().contains(model.getListPanoramaMontagnes().get(i))) {
            model.getListJoueur().get(0).addCarte(model.getListPanoramaMontagnes().get(i));
            alert.setGraphic(new ImageView("/Vue/Images/" + model.getListPanoramaMontagnes().get(i).getNom() + ".jpg"));
            alert.setHeaderText("Félicitation vous visitez une montagne \n vous obtenez donc :");
            alert.showAndWait();
            if (i==3 && !model.isMontagne()){
                alert.setHeaderText("Félicitaion vous êtes le premier à débloquer \n tous les panoramas montagnes vous gagnez donc :");
                alert.setGraphic(new ImageView("/Vue/Images/AcomplissementMontagne.jpg"));
                model.getListJoueur().get(0).addCarte(model.getListAcomplissement().get(1));
                model.setMontagne(true);
                alert.showAndWait();
            }
            return;
        }
    }
        alert.setGraphic(null);
        alert.setHeaderText("Vous avez déjà débloqué tous les panoramas montagnes");
        alert.showAndWait();
}

    /*
    Methode qui gere la pioche des panoramas Mer
     */
    private void panoramaMer(){
        Thread sonMer = new Son("src/Model/sonTokaido/mer.wav");
        sonMer.start();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mer");
        alert.setContentText(null);

        for (int i = 0; i < model.getListPanoramaMer().size(); i++) {
            if (!model.getListJoueur().get(0).getCartes().contains(model.getListPanoramaMer().get(i))) {
                model.getListJoueur().get(0).addCarte(model.getListPanoramaMer().get(i));
                alert.setGraphic(new ImageView("/Vue/Images/" + model.getListPanoramaMer().get(i).getNom() + ".jpg"));
                alert.setHeaderText("Félicitation vous visitez la mer \n vous obtenez donc :");
                alert.showAndWait();
                if (i==4 && !model.isMer()){
                    alert.setHeaderText("Félicitaion vous êtes le premier à débloquer \n tous les panoramas mer vous gagnez donc :");
                    alert.setGraphic(new ImageView("/Vue/Images/AcomplissementMer.jpg"));
                    model.getListJoueur().get(0).addCarte(model.getListAcomplissement().get(2));
                    model.setMer(true);
                    alert.showAndWait();
                }
                return;
            }
        }
        alert.setGraphic(null);
        alert.setHeaderText("Vous avez déjà débloqué tous les panoramas mer");
        alert.showAndWait();

    }

    /*
    Methode qui gere l'affichage et les dons aux temples et les pouvoirs des personnages en rapport avec le temple
     */
    private void rencontreTemple(Button button){
        Thread sonTemple = new Son("src/Model/sonTokaido/temple.wav");
        sonTemple.start();
        if(model.getListJoueur().get(0).getGold()>0) {
                if (model.getListJoueur().get(0).getNom().equals("Hirotada")){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Hirotada");
                    alert.setHeaderText("En tant qu'hirotada vous pouvez  \n ajouter une piece au temple si vous le voulez \n et cela gratuitement");
                    ButtonType faireDon = new ButtonType("Faire ce don");
                    ButtonType refuser = new ButtonType("Refuser");
                    alert.getButtonTypes().setAll(faireDon,refuser);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == faireDon) {
                        model.getListJoueur().get(0).setOrTemple(model.getListJoueur().get(0).getOrTemple()+1);
                        model.getListJoueur().get(0).setPoints(model.getListJoueur().get(0).getPoints()+1);
                    }
                    }
                ArrayList<Integer> choix = new ArrayList<Integer>();
                choix.add(1);
                choix.add(2);
                choix.add(3);
                int i=3;
                for (Cartes cartes :model.getListJoueur().get(0).getCartes()){
                    if (cartes.getNom().equals("Antoine")){
                        i++;
                        choix.add(i);
                    }
                }
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
                    }else{
                        messageErreur("Vous n'avez pas autant d'argent...");
                        rencontreTemple(button);
                    }
                }else {
                    messageErreur("Vous devez faire un don !");
                    rencontreTemple(button);
                }
            }
         button.setStyle("-fx-background-color: white;");
        }

    /*
    Methode qui gere l'affichage et les dons des fermes + la rencontre Olivier
     */
    private void rencontreFerme() {

        double a=Math.random();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ferme");
        alert.setHeaderText(null);
        if (a<0.9 && model.isCreateur()) {
            Thread sonRire = new Son("src/Model/sonTokaido/rire.wav");
            sonRire.start();
            alert.setContentText("Pas de chance olivier est déjà la \n vous ne gagnez donc pas de pièce");
            alert.setGraphic(new ImageView("/Vue/Images/Olivier.jpg"));
        }else {
            Thread sonPiece = new Son("src/Model/sonTokaido/piece.wav");
            sonPiece.start();
            alert.setGraphic(new ImageView("/Vue/Images/Ferme.png"));
            alert.setContentText("Vous arrivez à une ferme \n vous gagnez 3 pièce d'or");
            model.getListJoueur().get(0).setGold(model.getListJoueur().get(0).getGold() + 3);
        }
        alert.showAndWait();

    }

    /*
    Methode generant les messages d'erreur simple
     */
    private void messageErreur(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /*
    Ajout de tous les boutons du jeu dans la liste boutonsPlateau
    Met les boutons des arrets doubles/relais non visible
     */
    private void ajoutBouton() {
        boutonsPlateau.add(b013);
        b013.setVisible(false);
        boutonsPlateau.add(b012);
        b012.setVisible(false);
        boutonsPlateau.add(b011);
        b011.setVisible(false);
        boutonsPlateau.add(b010);
        b010.setVisible(false);
        boutonsPlateau.add(b0);
        boutonsPlateau.add(b101);
        b101.setVisible(false);
        boutonsPlateau.add(b1);
        boutonsPlateau.add(b2);
        boutonsPlateau.add(b3);
        boutonsPlateau.add(b4);
        boutonsPlateau.add(b501);
        b501.setVisible(false);
        boutonsPlateau.add(b5);
        boutonsPlateau.add(b601);
        b601.setVisible(false);
        boutonsPlateau.add(b6);
        boutonsPlateau.add(b701);
        b701.setVisible(false);
        boutonsPlateau.add(b7);
        boutonsPlateau.add(b8);
        boutonsPlateau.add(b901);
        b901.setVisible(false);
        boutonsPlateau.add(b9);
        boutonsPlateau.add(b10);
        boutonsPlateau.add(b111);
        b111.setVisible(false);
        boutonsPlateau.add(b11);
        boutonsPlateau.add(b12);
        boutonsPlateau.add(b13);
        boutonsPlateau.add(b144);
        b144.setVisible(false);
        boutonsPlateau.add(b143);
        b143.setVisible(false);
        boutonsPlateau.add(b142);
        b142.setVisible(false);
        boutonsPlateau.add(b141);
        b141.setVisible(false);
        boutonsPlateau.add(b14);
        boutonsPlateau.add(b15);
        boutonsPlateau.add(b16);
        boutonsPlateau.add(b171);
        b171.setVisible(false);
        boutonsPlateau.add(b17);
        boutonsPlateau.add(b181);
        b181.setVisible(false);
        boutonsPlateau.add(b18);
        boutonsPlateau.add(b191);
        b191.setVisible(false);
        boutonsPlateau.add(b19);
        boutonsPlateau.add(b201);
        b201.setVisible(false);
        boutonsPlateau.add(b20);
        boutonsPlateau.add(b21);
        boutonsPlateau.add(b221);
        b221.setVisible(false);
        boutonsPlateau.add(b22);
        boutonsPlateau.add(b23);
        boutonsPlateau.add(b241);
        b241.setVisible(false);
        boutonsPlateau.add(b24);
        boutonsPlateau.add(b25);
        boutonsPlateau.add(b26);
        boutonsPlateau.add(b274);
        b274.setVisible(false);
        boutonsPlateau.add(b273);
        b273.setVisible(false);
        boutonsPlateau.add(b272);
        b272.setVisible(false);
        boutonsPlateau.add(b271);
        b271.setVisible(false);
        boutonsPlateau.add(b27);
        boutonsPlateau.add(b28);
        boutonsPlateau.add(b29);
        boutonsPlateau.add(b301);
        b301.setVisible(false);
        boutonsPlateau.add(b30);
        boutonsPlateau.add(b31);
        boutonsPlateau.add(b321);
        b321.setVisible(false);
        boutonsPlateau.add(b32);
        boutonsPlateau.add(b33);
        boutonsPlateau.add(b341);
        b341.setVisible(false);
        boutonsPlateau.add(b34);
        boutonsPlateau.add(b35);
        boutonsPlateau.add(b361);
        b361.setVisible(false);
        boutonsPlateau.add(b36);
        boutonsPlateau.add(b371);
        b371.setVisible(false);
        boutonsPlateau.add(b37);
        boutonsPlateau.add(b38);
        boutonsPlateau.add(b39);
        boutonsPlateau.add(b401);
        b401.setVisible(false);
        boutonsPlateau.add(b40);
        boutonsPlateau.add(b414);
        b414.setVisible(false);
        boutonsPlateau.add(b413);
        b413.setVisible(false);
        boutonsPlateau.add(b412);
        b412.setVisible(false);
        boutonsPlateau.add(b411);
        b411.setVisible(false);
        boutonsPlateau.add(b41);
        boutonsPlateau.add(b42);
        boutonsPlateau.add(b431);
        b431.setVisible(false);
        boutonsPlateau.add(b43);
        boutonsPlateau.add(b44);
        boutonsPlateau.add(b451);
        b451.setVisible(false);
        boutonsPlateau.add(b45);
        boutonsPlateau.add(b46);
        boutonsPlateau.add(b471);
        b471.setVisible(false);
        boutonsPlateau.add(b47);
        boutonsPlateau.add(b481);
        b481.setVisible(false);
        boutonsPlateau.add(b48);
        boutonsPlateau.add(b49);
        boutonsPlateau.add(b50);
        boutonsPlateau.add(b511);
        b511.setVisible(false);
        boutonsPlateau.add(b51);
        boutonsPlateau.add(b521);
        b521.setVisible(false);
        boutonsPlateau.add(b52);
        boutonsPlateau.add(b53);
        boutonsPlateau.add(b544);
        b544.setVisible(false);
        boutonsPlateau.add(b543);
        b543.setVisible(false);
        boutonsPlateau.add(b542);
        b542.setVisible(false);
        boutonsPlateau.add(b541);
        b541.setVisible(false);
        boutonsPlateau.add(b54);


    }

    /*
    Gere l'affichage de la fenetre de in de partie
     */
    public void finDePartie() throws IOException {
        Thread sonVictoire = new Son("src/Model/sonTokaido/victoire.wav");
        sonVictoire.start();
        distribAvancement();
        model.majScore();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Fin de partie");
        alert.setHeaderText("Accomplissements : ");
        model.trieJoueurScore();
        String classement="Classement final :\n";
        for (int i=0;i<model.getListJoueur().size();i++){
            classement+=i+1+" ---> "+model.getListJoueur().get(i).getNom()+" avec : "+model.getListJoueur().get(i).getPoints()+" points \n";
        }
        GridPane accomplissement = new GridPane();
        int comptJ=0;
        for(Joueur j: model.getListJoueur()){
            int posColumn=1;
            for(Cartes c: j.getCartes()){
                if(c instanceof Acomplissement) {
                    ImageView imgJ = new ImageView("/Vue/Images/" + j.getNom() + ".jpg");
                    imgJ.setFitHeight(100);imgJ.setFitWidth(90);
                    ImageView imgA = new ImageView("/Vue/Images/" + c.getNom() + ".jpg");
                    imgA.setFitHeight(80);imgA.setFitWidth(70);
                    accomplissement.add(imgJ, 0, comptJ);
                    accomplissement.add(imgA, posColumn, comptJ);
                    posColumn++;
                }
            }
            comptJ++;
        }
        alert.setGraphic(accomplissement);
        alert.setResizable(true);
        alert.setContentText(classement);
        ButtonType buttonResetGame = new ButtonType("Recommencer");
        ButtonType buttonRestart = new ButtonType("Retour a l'accueil");
        ButtonType buttonQuiter = new ButtonType("Quitter");
        alert.getButtonTypes().setAll(buttonResetGame,buttonRestart, buttonQuiter);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonRestart) {
          //  sonMusique.s();
            restart();
        } else if (result.get()==buttonResetGame) {
            restartGame();
        } else {
            System.exit(0);
        }
    }

    /*
    Methode qui controle le bouton de restart
     */
    @FXML
    private void restartGame() {
        for (Button button:boutonsPlateau){ button.setStyle("-fx-background-color: white"); }
        ArrayList<Joueur> listeJoueur = model.getListJoueur();
        boolean recommencer=model.isCreateur();
        this.model= new Model();
        this.model.setCreateur(recommencer);
        for (Joueur joueur:listeJoueur){
            switch (joueur.getNom()){
                case "Kinko":
                    model.addJoueur(new Kinko());
                    break;
                case "Sasayakko":
                    model.addJoueur(new Sasayakko());
                    break;
                case"Chuubei":
                    model.addJoueur(new Chuubei());
                    break;
                case "Hiroshige":
                    model.addJoueur(new Hiroshige());
                    break;
                case "Hirotada":
                    model.addJoueur(new Hirotada());
                    break;
                case "Mitsukuni":
                    model.addJoueur(new Mitsukuni());
                    break;
                case "Umegae":
                    model.addJoueur(new Umegae());
                    break;
                case "Yoshiyasu":
                    model.addJoueur(new Yoshiyasu());
                    break;
                case "ZenEmon":
                    model.addJoueur(new ZenEmon());
                    break;
                case "Satsuki":
                    model.addJoueur(new Satsuki());
                    break;
            }
        }
        boutonsPlateau.clear();
        Collections.shuffle(model.getListJoueur());
        ajoutBouton();
        initPartie();
        loader.<RecapController>getController().setData(this.model);
        loader.<RecapController>getController().afficheCartes();
    }

    /*
    initialise le model /  les boutons / le placement des joueurs
     */
    private void initPartie() {
        model.initPartie();
        initJoueur();
        afficheArretRelais();
        colorieCase();
        affichageJoueur.setText(model.getListJoueur().get(0).getNom()+" commence la partie");
        if(!model.getListJoueur().get(0).getNom().equals("VoyageurNeutre")) affichageJoueur.setGraphic(new ImageView("/Vue/Images/"+model.getListJoueur().get(0).getNom()+"1.jpg"));
    }

    /*
    Initialise les position des joueurs
     */
    private void initJoueur() {
        switch (model.getListJoueur().size()){
            case 2:
                model.getListJoueur().get(0).setPositions(3);
                model.getListJoueur().get(1).setPositions(4);
                break;
            case 3:
                model.getListJoueur().get(0).setPositions(2);
                model.getListJoueur().get(1).setPositions(3);
                model.getListJoueur().get(2).setPositions(4);
                break;
            case 4:
                model.getListJoueur().get(0).setPositions(1);
                model.getListJoueur().get(1).setPositions(2);
                model.getListJoueur().get(2).setPositions(3);
                model.getListJoueur().get(3).setPositions(4);
                break;
            case 5:
                model.getListJoueur().get(0).setPositions(0);
                model.getListJoueur().get(1).setPositions(1);
                model.getListJoueur().get(2).setPositions(2);
                model.getListJoueur().get(3).setPositions(3);
                model.getListJoueur().get(4).setPositions(4);
                break;
        }
    }

    /*
    Methode permettant de recuperer le model cree dans le launcher et gere la demande pour les variantes de jeu
     */
    public void setData(Model m) throws IOException {
        this.model =m;
        if (model.getListJoueur().size()==2){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Voyageur neutre");
            alert.setGraphic(null);
            alert.setHeaderText("Voulez vous jouer avec le voyageur neutre ?");
            ButtonType oui = new ButtonType("Oui");
            ButtonType non = new ButtonType("Non");
            alert.getButtonTypes().setAll(oui,non);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get()==oui){
                model.getListJoueur().add(new Neutre());
            }

        }
        if(model.getListJoueur().size()>=4){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Voyageur neutre");
            alert.setGraphic(null);
            alert.setHeaderText("Voulez vous jouer en équilibrant les départs ?");
            ButtonType oui = new ButtonType("Oui");
            ButtonType non = new ButtonType("Non");
            alert.getButtonTypes().setAll(oui,non);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get()==oui){
                equilibrage = true;
            }
        }
        Collections.shuffle(model.getListJoueur());
        if(equilibrage){
            for (int i = 0; i < model.getListJoueur().size(); i++) {
                if(i==0) model.getListJoueur().get(i).setGold(model.getListJoueur().get(i).getGold()-1);
                if(i==1) model.getListJoueur().get(i).setGold(model.getListJoueur().get(i).getGold()-1);
                if(i==model.getListJoueur().size()-2 && model.getListJoueur().size()==5) model.getListJoueur().get(i).setGold(model.getListJoueur().get(i).getGold()+1);
                if(i==model.getListJoueur().size()-1) model.getListJoueur().get(i).setGold(model.getListJoueur().get(i).getGold()+2);
            }
        }
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage secondaryStage = new Stage();
        loader.<RecapController>getController().setData(this.model);
        loader.<RecapController>getController().afficheCartes();
        secondaryStage.setScene(scene);
        secondaryStage.setMaximized(false);
        secondaryStage.setResizable(false);
        secondaryStage.setTitle("Recapitulatif");
        secondaryStage.show();
        ajoutBouton();
        initPartie();

    }

    /**
     * Partie menu
     */

    public void leaveGame(){
        System.exit(0);
    }

    /*
    Methode relancant la partie avec les memes joueurs
     */
    public void restart() throws IOException {
            sonMusique.stop();
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
            this.loader.<RecapController>getController().fermerStage();
        }

    public void showReglement() {
        LauncherController.reglement();
    }

    /*
    Methode gerant la distribution des avancements en fin de partie
     */
    public void distribAvancement(){
        model.trieJoueurOrTemple(); // tri OK
        if(model.getListJoueur().size()>=3){
            int maxOrTemple=model.getListJoueur().get(0).getOrTemple();
            model.getListOrTemple().add(maxOrTemple);
            int compteur=0;
            for (int i = 0; i < model.getListJoueur().size(); i++) {
                if(compteur==3) break;
                if(model.getListJoueur().get(i).getOrTemple()<maxOrTemple){
                    maxOrTemple = model.getListJoueur().get(i).getOrTemple();
                    model.getListOrTemple().add(maxOrTemple);
                    compteur++;
                }
            }
        }else if(model.getListJoueur().size()==2){
            int maxOrTemple=model.getListJoueur().get(0).getOrTemple();
            model.getListOrTemple().add(maxOrTemple);
            int compteur=0;
            for (int i = 0; i < model.getListJoueur().size(); i++) {
                if(compteur==2) break;
                if(model.getListJoueur().get(i).getOrTemple()<maxOrTemple){
                    maxOrTemple = model.getListJoueur().get(i).getOrTemple();
                    model.getListOrTemple().add(maxOrTemple);
                    compteur++;
                }
            }
        }


        for (int i = 0; i < model.getListJoueur().size(); i++) {
            boolean ptsDonnee = false;
            if(model.getListJoueur().get(i).getOrTemple()!=0){
                for (int j = 0; j < model.getListOrTemple().size(); j++) {
                    if(model.getListJoueur().get(i).getOrTemple()==model.getListOrTemple().get(j)){
                        switch (j){
                            case 0:
                                model.getListJoueur().get(i).setPoints(model.getListJoueur().get(i).getPoints() + 10);
                                ptsDonnee = true;
                                break;
                            case 1:
                                model.getListJoueur().get(i).setPoints(model.getListJoueur().get(i).getPoints() + 7);
                                ptsDonnee = true;
                                break;
                            case 2:
                                model.getListJoueur().get(i).setPoints(model.getListJoueur().get(i).getPoints() + 4);
                                ptsDonnee = true;
                                break;

                        }
                        if(ptsDonnee) break;
                    }else if(j==2){
                        model.getListJoueur().get(i).setPoints(model.getListJoueur().get(i).getPoints() + 2);
                    }
                }
            }else{
                model.getListJoueur().get(i).setPoints(model.getListJoueur().get(i).getPoints());
            }
        }

        int prixRepasTotal = 0;
        ArrayList<Integer> positionJ = new ArrayList<Integer>();
        int compteurJ = 0;

        for(Joueur j: model.getListJoueur()){
            int prixRepas =0;
            for (int i = 0; i < j.getCartes().size(); i++) {
                if(j.getCartes().get(i) instanceof Repas){
                    prixRepas +=((Repas) j.getCartes().get(i)).getPrix();
                }
            }
            if(prixRepas> prixRepasTotal){
                prixRepasTotal = prixRepas;
                positionJ.add(0,compteurJ);
            }else if(prixRepas==prixRepasTotal && prixRepasTotal!=0){
                positionJ.add(positionJ.size(),compteurJ);
            }
            compteurJ++;
        }
        for(int pos: positionJ){
            model.getListJoueur().get(pos).addCarte(new Acomplissement("Gourmet"));
        }

        compteurJ =0;
        positionJ.clear();
        int nbSourceTotal=0;
        for(Joueur j: model.getListJoueur()){
            int nbSource =0;
            for (int i = 0; i < j.getCartes().size(); i++) {
                if(j.getCartes().get(i) instanceof Sources){
                    nbSource ++;
                }
            }
            if(nbSource> nbSourceTotal){
                nbSourceTotal = nbSource;
                positionJ.add(0,compteurJ);
            }else if(nbSource==nbSourceTotal && nbSourceTotal!=0){
                positionJ.add(positionJ.size(),compteurJ);
            }
            compteurJ++;
        }
        for(int pos: positionJ){
            model.getListJoueur().get(pos).addCarte(new Acomplissement("Baigneur"));

        }

        compteurJ =0;
        positionJ.clear();
        int nbRencontreTotal=0;
        for(Joueur j: model.getListJoueur()){
            int nbRencontre =0;
            for (int i = 0; i < j.getCartes().size(); i++) {
                if(j.getCartes().get(i) instanceof Rencontre){
                    nbRencontre ++;
                }
            }
            if(nbRencontre> nbRencontreTotal){
                nbRencontreTotal = nbRencontre;
                positionJ.add(0,compteurJ);
            }else if(nbRencontre==nbRencontreTotal && nbRencontreTotal!=0){
                positionJ.add(positionJ.size(),compteurJ);
            }
            compteurJ++;
        }
        for(int pos: positionJ){
            model.getListJoueur().get(pos).addCarte(new Acomplissement("Bavard"));

        }

        compteurJ =0;
        positionJ.clear();
        int nbSouvenirTotal=0;
        for(Joueur j: model.getListJoueur()){
            int nbSouvenir =0;
            for (int i = 0; i < j.getCartes().size(); i++) {
                if(j.getCartes().get(i) instanceof Souvenirs){
                    nbSouvenir ++;
                }
            }
            if(nbSouvenir> nbSouvenirTotal){
                nbSouvenirTotal = nbSouvenir;
                positionJ.add(0,compteurJ);
            }else if(nbSouvenir==nbSouvenirTotal && nbSouvenirTotal!=0){
                positionJ.add(positionJ.size(),compteurJ);
            }
            compteurJ++;
        }
        for(int pos: positionJ){
            model.getListJoueur().get(pos).addCarte(new Acomplissement("Collectionneur"));
        }
    }

}
