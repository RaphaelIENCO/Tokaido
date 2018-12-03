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
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;



public class GameController {
    private Model model;
    private ArrayList<Button> boutonsPlateau;
    @FXML Button b0;
    @FXML Button b101;
    @FXML Button b1;
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
    @FXML Button b17;
    @FXML Button b18;
    @FXML Button b19;
    @FXML Button b20;
    @FXML Button b21;
    @FXML Button b22;
    @FXML Button b23;
    @FXML Button b24;
    @FXML Button b25;
    @FXML Button b26;
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
    @FXML GridPane grille;
    @FXML Label affichageJoueur;

    public GameController(){
        boutonsPlateau = new ArrayList<>();
    }

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
                messageErreur("Il y à déja quelqu'un");
                return;
            }
        }
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
        } else if(button.getId().contains("riziere")){
            panoramaRiziere();
        }else if(button.getId().contains("montagne")){
            panoramaMontagne();
        }else if(button.getId().contains("mer")){
            panoramaMer();
        }
        updatePos(event);
        button.setStyle("-fx-background-color: "+model.getListJoueur().get(0).getCouleur()+";");
        model.trieJoueur();
        model.majScore();
        afficheCartes();
        if (model.getListJoueur().size()>=4) afficheArretDouble();
        if (model.getListJoueur().get(0).getPositions()>=boutonsPlateau.size()-1){
            try {
                finDePartie();
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        affichageJoueur.setText("Au tour de : "+model.getListJoueur().get(0).getNom());
    }



    private void updatePos(ActionEvent event) {
        Boolean exist = false;
        Button button = (Button) event.getSource();
        for (int i = 0; i < boutonsPlateau.size(); i++) {
            if (button.equals(boutonsPlateau.get(i))) {
                model.getListJoueur().get(0).setPositions(i);
            }
        }
        int indice=0;
        for (int i = 0; i < boutonsPlateau.size(); i++) {
            for (int j = 0; j < model.getListJoueur().size(); j++) {
                if (model.getListJoueur().get(j).getPositions() == i) {
                    exist = true;
                    indice=j;
                }
            }
            if (!exist) {
                boutonsPlateau.get(i).setStyle("-fx-background-color: gray;");
                exist = false;
            } else {
                boutonsPlateau.get(i).setStyle("-fx-background-color:"+model.getListJoueur().get(indice).getCouleur()+";");
                        exist=false;

            }
        }
    }

    private void afficheArretDouble(){
        boolean aCacher;
       for (Integer integer:model.getListArretDouble()){
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
    }

    /**
     * Partie pioche
     */
    public void piocheRelais() {
            if (!model.getListRepas().isEmpty()){
                if (model.getListJoueur().get(0).isPiocheRelais()){
                    if (model.getListJoueur().get(0).getNom().equals("Chuubei")){
                        model.getListJoueur().get(0).setPiocheRencontre(true);
                        piocheRencontre();
                    }
                    if (model.getListJoueur().get(0).getNom().equals("Satsuki")){
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Satsuki");
                        alert.setHeaderText("En tant que satsuki vous avez le droit à un repas gratuit \n que vous pouvez refuser pour acceder au relais normal");
                        alert.setGraphic(new ImageView("/Vue/Images/"+model.getListRepas().get(0).getNom()+".jpg"));
                        if (model.getListJoueur().get(0).contient(model.getListRepas().get(0))){
                            alert.setContentText("Vous l'avez déja gouté");
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
                        if (!model.getListJoueur().get(0).contient(mesRepas.get(indice))) {
                            if (model.getListJoueur().get(0).getNom().equals("Kinko")) {
                                if (model.getListJoueur().get(0).getGold() >= (mesRepas.get(indice).getPrix() - 1)) {
                                    model.getListJoueur().get(0).setGold(model.getListJoueur().get(0).getGold() - (mesRepas.get(indice).getPrix() - 1));
                                } else {
                                    messageErreur("Vous n'avez pas les fond nécéssaires pour : \n"+mesRepas.get(indice).getNom());
                                }
                            } else {
                                if (model.getListJoueur().get(0).getGold() >= mesRepas.get(indice).getPrix()) {
                                    model.getListJoueur().get(0).setGold(model.getListJoueur().get(0).getGold() - mesRepas.get(indice).getPrix());

                                } else {
                                    messageErreur("Vous n'avez pas les fond nécéssaires pour : \n"+mesRepas.get(indice).getNom());
                                }
                            }
                            model.getListJoueur().get(0).addCarte(mesRepas.get(indice));
                            model.getListRepas().removeAll(mesRepas);
                            mesRepas.remove(indice);
                            model.getListRepas().addAll(mesRepas);
                        }

                        else {
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

    private void piocheSourceChaude(){
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

    private void piocheSouvenir(){
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
                if(model.getListJoueur().get(0).getNom().equals("ZenEmon")){
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

    private void piocheRencontre(){
        if(!model.getListRecontre().isEmpty()) {
            if (model.getListJoueur().get(0).isPiocheRencontre()) {
                if (model.getListJoueur().get(0).getNom().equals("Umegae")) {
                    model.getListJoueur().get(0).setPoints(model.getListJoueur().get(0).getPoints()+1);
                    model.getListJoueur().get(0).setGold(model.getListJoueur().get(0).getGold()+1);
                }
                    if (model.getListJoueur().get(0).getNom().equals("Yoshiyasu") && model.getListRecontre().size()>=2) {
                        GridPane gridPane = new GridPane();
                        RadioButton radioButton1 = new RadioButton();
                        RadioButton radioButton2 = new RadioButton();
                        ToggleGroup toggleGroup = new ToggleGroup();
                        Rencontre rencontre1= model.getListRecontre().get(0);
                        Rencontre rencontre2= model.getListRecontre().get(1);

                        radioButton1.setGraphic(new ImageView("/Vue/Images/" + model.getListRecontre().get(0).getNom() + ".jpg"));
                        radioButton2.setGraphic(new ImageView("/Vue/Images/" + model.getListRecontre().get(1).getNom() + ".jpg"));
                        radioButton1.setToggleGroup(toggleGroup);
                        radioButton2.setToggleGroup(toggleGroup);
                        gridPane.add(radioButton1,0,0);
                        gridPane.add(radioButton2,1,0);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Rencontre");
                        alert.setGraphic(gridPane);
                        alert.setHeaderText(null);
                        alert.setContentText("Veuillez choisir une cartes");
                        alert.showAndWait();
                        if (radioButton1.isSelected()) rencontreYoshiyasu(rencontre1,rencontre1);
                        else if (radioButton2.isSelected())rencontreYoshiyasu(rencontre1,rencontre2);

                } else {
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
                }
            } else {
                messageErreur("Vous ne pouvez pas piocher de carte Rencontre");
            }
        }else messageErreur("Plus de cartes rencontre");
    }

    private void panoramaRiziere() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Riziere");
        alert.setContentText(null);

        for (int i = 0; i < model.getListPanoramaRiziere().size(); i++) {
            if (!model.getListJoueur().get(0).getCartes().contains(model.getListPanoramaRiziere().get(i))) {
                model.getListJoueur().get(0).addCarte(model.getListPanoramaRiziere().get(i));
                alert.setGraphic(new ImageView("/Vue/Images/" + model.getListPanoramaRiziere().get(i).getNom() + ".jpg"));
                System.out.println("/Vue/Images/" + model.getListPanoramaRiziere().get(i).getNom() + ".jpg");
                alert.setHeaderText("Félicitation vous visiter une riziere \n vous obtenez donc :");
                alert.showAndWait();
                if (i==2 && !model.isRiziere()){
                    alert.setHeaderText("Félicitaion vous êtes le premier à débloquer \n tout les panoramas rizières vous gagnez donc :");
                    alert.setGraphic(new ImageView("/Vue/Images/AcomplissementRiziere.jpg"));
                    model.getListJoueur().get(0).addCarte(model.getListAcomplissement().get(0));
                    model.setRiziere(true);
                    alert.showAndWait();
                }
                return;
            }
        }
        alert.setGraphic(null);
        alert.setHeaderText("Vous avez déjà débloqué tout les panoramas rizieres");
        alert.showAndWait();
        }

    private void panoramaMontagne() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Montagne");
        alert.setContentText(null);

        for (int i = 0; i < model.getListPanoramaMontagnes().size(); i++) {
        System.out.println(model.getListJoueur().get(0).getCartes().contains(model.getListPanoramaMontagnes().get(i)));
        if (!model.getListJoueur().get(0).getCartes().contains(model.getListPanoramaMontagnes().get(i))) {
            model.getListJoueur().get(0).addCarte(model.getListPanoramaMontagnes().get(i));
            alert.setGraphic(new ImageView("/Vue/Images/" + model.getListPanoramaMontagnes().get(i).getNom() + ".jpg"));
            System.out.println("/Vue/Images/" + model.getListPanoramaMontagnes().get(i).getNom() + ".jpg");
            alert.setHeaderText("Félicitation vous visiter une montagne \n vous obtenez donc :");
            alert.showAndWait();
            if (i==3 && !model.isMontagne()){
                alert.setHeaderText("Félicitaion vous êtes le premier à débloquer \n tout les panoramas montagnes vous gagnez donc :");
                alert.setGraphic(new ImageView("/Vue/Images/AcomplissementMontagne.jpg"));
                model.getListJoueur().get(0).addCarte(model.getListAcomplissement().get(1));
                model.setMontagne(true);
                alert.showAndWait();
            }
            return;
        }
    }
        alert.setGraphic(null);
        alert.setHeaderText("Vous avez déjà débloqué tout les panoramas montatagnes");
        alert.showAndWait();
}

    private void panoramaMer(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mer");
        alert.setContentText(null);

        for (int i = 0; i < model.getListPanoramaMer().size(); i++) {
            System.out.println(model.getListJoueur().get(0).getCartes().contains(model.getListPanoramaMer().get(i)));
            if (!model.getListJoueur().get(0).getCartes().contains(model.getListPanoramaMer().get(i))) {
                model.getListJoueur().get(0).addCarte(model.getListPanoramaMer().get(i));
                alert.setGraphic(new ImageView("/Vue/Images/" + model.getListPanoramaMer().get(i).getNom() + ".jpg"));
                System.out.println("/Vue/Images/" + model.getListPanoramaMer().get(i).getNom() + ".jpg");
                alert.setHeaderText("Félicitation vous visiter la mer \n vous obtenez donc :");
                alert.showAndWait();
                if (i==4 && !model.isMer()){
                    alert.setHeaderText("Félicitaion vous êtes le premier à débloquer \n tout les panoramas mer vous gagnez donc :");
                    alert.setGraphic(new ImageView("/Vue/Images/AcomplissementMer.jpg"));
                    model.getListJoueur().get(0).addCarte(model.getListAcomplissement().get(2));
                    model.setMer(true);
                    alert.showAndWait();
                }
                return;
            }
        }
        alert.setGraphic(null);
        alert.setHeaderText("Vous avez déjà débloqué tout les panoramas mer");
        alert.showAndWait();
    }


    private void rencontreTemple(Button button){
        if(model.getListJoueur().get(0).getGold()>0) {
            if (model.getListJoueur().get(0).isTemple()) {
                if (model.getListJoueur().get(0).getNom().equals("Hirotada")){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Hirotada");
                    alert.setHeaderText("En tant qu'hirotada vous pouvez  \n ajouter une piece au temple si vous le voulez \n et cela gratuitement");
                    ButtonType accepter = new ButtonType("Accepter");
                    ButtonType refuser = new ButtonType("Refuser");

                    alert.getButtonTypes().setAll(accepter,refuser);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == accepter) {
                        model.getListJoueur().get(0).setOrTemple(model.getListJoueur().get(0).getOrTemple()+1);
                        model.getListJoueur().get(0).setPoints(model.getListJoueur().get(0).getPoints()+1);
                    }
                    }
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

    private void rencontreYoshiyasu(Rencontre rencontre1, Rencontre rencontre2){
        rencontre2.rencontre(model.getListJoueur().get(0));
        model.getListJoueur().get(0).addCarte(rencontre2);
        model.getListRecontre().remove(rencontre1);
        model.getListRecontre().remove(rencontre2);
        model.getListRecontre().add(rencontre1);

    }



    /**
     * Partie Affichage
     */
    private void afficheCartes(){
        model.getListJoueur().get(0).trierCarte();
        grille.getChildren().clear();
        for (int i=0;i<model.getRecapJoueur().size();i++){
            Text descriptif = new Text(model.getRecapJoueur().get(i).getNom()+" \n or : "+model.getRecapJoueur().get(i).getGold()+" \n points : "+model.getRecapJoueur().get(i).getPoints()+" \n or temple: "+model.getRecapJoueur().get(i).getOrTemple());
            grille.add(descriptif,0,i);
            if(model.getRecapJoueur().get(i).getNom().equals(model.getListJoueur().get(0).getNom())){
                grille.add(new ImageView("/Vue/Images/" + model.getRecapJoueur().get(i).getNom() + "1.jpg"), 1, i);
            } else grille.add(new ImageView("/Vue/Images/" + model.getRecapJoueur().get(i).getNom() + ".jpg"), 1, i);

            for (int j=0;j<model.getRecapJoueur().get(i).getCartes().size();j++){
                grille.add(new ImageView("/Vue/Images/"+model.getRecapJoueur().get(i).getCartes().get(j).getNom()+".jpg"),j+2,i);
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
        boutonsPlateau.add(b14);
        boutonsPlateau.add(b15);
        boutonsPlateau.add(b16);
        boutonsPlateau.add(b17);
        boutonsPlateau.add(b18);
        boutonsPlateau.add(b19);
        boutonsPlateau.add(b20);
        boutonsPlateau.add(b21);
        boutonsPlateau.add(b22);
        boutonsPlateau.add(b23);
        boutonsPlateau.add(b24);
        boutonsPlateau.add(b25);
        boutonsPlateau.add(b26);
        boutonsPlateau.add(b27);
        boutonsPlateau.add(b28);
        boutonsPlateau.add(b29);
        boutonsPlateau.add(b30);
        boutonsPlateau.add(b31);
        boutonsPlateau.add(b32);
        boutonsPlateau.add(b33);
        boutonsPlateau.add(b34);
        boutonsPlateau.add(b35);
        boutonsPlateau.add(b36);
        boutonsPlateau.add(b37);
        boutonsPlateau.add(b38);
        boutonsPlateau.add(b39);
        boutonsPlateau.add(b40);
        boutonsPlateau.add(b41);
        boutonsPlateau.add(b42);
        boutonsPlateau.add(b43);
        boutonsPlateau.add(b44);
        boutonsPlateau.add(b45);
        boutonsPlateau.add(b46);
        boutonsPlateau.add(b47);
        boutonsPlateau.add(b48);
        boutonsPlateau.add(b49);
        boutonsPlateau.add(b50);
        boutonsPlateau.add(b51);
        boutonsPlateau.add(b52);
        boutonsPlateau.add(b53);
        boutonsPlateau.add(b54);


    }
    private void finDePartie() throws IOException {
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
                        //System.out.println(j);
                        if(model.getListJoueur().get(i).getOrTemple()==model.getListOrTemple().get(j)){
                        switch (j){
                            case 0:
                                //System.out.println("+10");
                                model.getListJoueur().get(i).setPoints(model.getListJoueur().get(i).getPoints() + 10);
                                ptsDonnee = true;
                                break;
                            case 1:
                                //System.out.println("+7");
                                model.getListJoueur().get(i).setPoints(model.getListJoueur().get(i).getPoints() + 7);
                                ptsDonnee = true;
                                break;
                            case 2:
                                //System.out.println("+4");
                                model.getListJoueur().get(i).setPoints(model.getListJoueur().get(i).getPoints() + 4);
                                ptsDonnee = true;
                                break;

                        }
                        if(ptsDonnee) break;
                    }else if(j==2){
                        //System.out.println("+2");
                        model.getListJoueur().get(i).setPoints(model.getListJoueur().get(i).getPoints() + 2);
                    }
                }
            }else{
                    System.out.println("+ rien");
                    model.getListJoueur().get(i).setPoints(model.getListJoueur().get(i).getPoints());
                }
        }


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Fin de partie");
        alert.setHeaderText("Classements :");
        model.trieJoueurScore();
        String classement="";
        for (int i=0;i<model.getListJoueur().size();i++){
            classement+=i+1+" ---> "+model.getListJoueur().get(i).getNom()+" avec : "+model.getListJoueur().get(i).getPoints()+" points \n";
        }
        alert.setContentText(classement);
        ButtonType buttonResetGame = new ButtonType("Recommencer");
        ButtonType buttonRestart = new ButtonType("Retour a l'accueil");
        ButtonType buttonQuiter = new ButtonType("Quitter");
        alert.getButtonTypes().setAll(buttonResetGame,buttonRestart, buttonQuiter);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonRestart) {
            restart();
        } else if (result.get()==buttonResetGame) {
            restartGame();

        } else {
            System.exit(0);
        }
    }

    @FXML
    private void restartGame() {
        for (Button button:boutonsPlateau){ button.setStyle("-fx-background-color: gray"); }
        ArrayList<Joueur> listeJoueur = model.getListJoueur();
        this.model= new Model();
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
        ajoutBouton();
        afficheCartes();
        affichageJoueur.setText(model.getListJoueur().get(0).getNom()+" commence la partie");
    }

    public void setData(Model m){
        this.model =m;
        afficheCartes();
        ajoutBouton();
        affichageJoueur.setText(model.getListJoueur().get(0).getNom()+" commence la partie");
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
