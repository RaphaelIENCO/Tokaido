package Model;

import Controller.GameController;
import Controller.LauncherController;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Collections;

public class Model {
    private boolean riziere;
    private boolean montagne;
    private boolean mer;

    private ArrayList<Rencontre>listRecontre = new ArrayList<Rencontre>();
    private ArrayList<Repas>listRepas  = new ArrayList<>();
    private ArrayList<Souvenirs>listSouvenir = new ArrayList<>();
    private ArrayList<Panoramas>listPanoramaMer = new ArrayList<>();
    private ArrayList<Panoramas>listPanoramaMontagnes = new ArrayList<>();
    private ArrayList<Panoramas>listPanoramaRiziere = new ArrayList<>();
    private ArrayList<Sources>listSource = new ArrayList<>();
    private ArrayList<Joueur>listJoueur = new ArrayList<Joueur>();
    private ArrayList<Integer>listOrTemple = new ArrayList<Integer>();
    private ArrayList<Acomplissement>listAcomplissement = new ArrayList<Acomplissement>();
    private ArrayList<Integer> listArretDouble= new ArrayList<Integer>();
    private ArrayList<Integer> listArretRelais = new ArrayList<>();



    private ArrayList<Joueur>recapJoueur = new ArrayList<Joueur>();
    public Model(){
        initPartie();
    }

    public void initPartie(){ //Creation de toutes les differentes cartes du jeu dans differents packets
        mer=false;
        montagne=false;
        riziere=false;
        initRencontre();
        initRepas();
        initSouvenir();
        initPanorama();
        initSource();
        initAccomplissement();
        initArretDouble();
        initRelais();
        shuffle();
    }
    private void initArretDouble(){
        listArretDouble.add(6);
        listArretDouble.add(11);
        listArretDouble.add(13);
        listArretDouble.add(15);
        listArretDouble.add(18);
        listArretDouble.add(21);
        listArretDouble.add(32);
        listArretDouble.add(34);
        listArretDouble.add(36);
        listArretDouble.add(38);
        listArretDouble.add(41);
        listArretDouble.add(44);
        listArretDouble.add(55);
        listArretDouble.add(58);
        listArretDouble.add(61);
        listArretDouble.add(64);
        listArretDouble.add(66);
        listArretDouble.add(70);
        listArretDouble.add(78);
        listArretDouble.add(81);
        listArretDouble.add(84);
        listArretDouble.add(86);
        listArretDouble.add(90);
        listArretDouble.add(92);

    }
    private void initRelais(){
        listArretRelais.add(1);
        listArretRelais.add(2);
        listArretRelais.add(3);
        listArretRelais.add(4);
        listArretRelais.add(25);
        listArretRelais.add(26);
        listArretRelais.add(27);
        listArretRelais.add(28);
        listArretRelais.add(48);
        listArretRelais.add(49);
        listArretRelais.add(50);
        listArretRelais.add(51);
        listArretRelais.add(72);
        listArretRelais.add(73);
        listArretRelais.add(74);
        listArretRelais.add(75);
        listArretRelais.add(95);
        listArretRelais.add(96);
        listArretRelais.add(97);
        listArretRelais.add(98);
    }

    private void initAccomplissement() {
        listAcomplissement.add(new Acomplissement("AcomplissementRiziere"));
        listAcomplissement.add(new Acomplissement("AcomplissementMontagne"));
        listAcomplissement.add(new Acomplissement("AcomplissementMer"));

    }

    public ArrayList<Integer> getListArretRelais() {
        return listArretRelais;
    }


    public void shuffle(){
        Collections.shuffle(listRecontre);
        Collections.shuffle(listRepas);
        Collections.shuffle(listSouvenir);
        Collections.shuffle(listSource);
    }

    public void initRencontre(){ //Init de toutes les rencontre
        Rencontre samurai = new Rencontre("Samurai") {
            @Override
            public void rencontre(Joueur joueur) {
                joueur.setPoints(joueur.getPoints()+3);
            }

            @Override
            public String getDescription() {
                return "Le samurai vous fait gagner trois points";
            }
        };
        listRecontre.add(samurai);
        listRecontre.add(samurai);
        Rencontre kude = new Rencontre("Kuge") {
            @Override
            public void rencontre(Joueur joueur) {
                joueur.setGold(joueur.getGold()+3);
            }
            @Override
            public String getDescription() {
                return "Vous gagnez 3 pieces d'or";
            }
        };
        listRecontre.add(kude);
        listRecontre.add(kude);
        Rencontre shokunin = new Rencontre("Shokunin") {
            @Override
            public void rencontre(Joueur joueur) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                String nomImage ="/Vue/Images/"+listSouvenir.get(0).getNom()+".jpg";
                ImageView imageView = new ImageView(new Image(nomImage));
                alert.setGraphic(imageView);
                alert.setTitle("Souvenirs");
                alert.setHeaderText("Félicitation le shokunin vous à fait gagner  \n "+listSouvenir.get(0).getNom());
                joueur.getCartes().add(listSouvenir.get(0));
                listSouvenir.remove(0);
                alert.showAndWait();
            }

            @Override
            public String getDescription() {
                return "Vous avez rencontré Shokunin \n vous gagnez une carte souvenir";
            }
        };
        listRecontre.add(shokunin);
        listRecontre.add(shokunin);

        Rencontre miko = new Rencontre("Miko") {
            @Override
            public void rencontre(Joueur joueur) {
                joueur.orTemple+=1;
            }

            @Override
            public String getDescription() {
                return "Vous placer une piece gratuite au temple \n et vous gagnez un point pour cette piece";
            }
        };
        listRecontre.add(miko);
        listRecontre.add(miko);

        Rencontre anaibito0 = new Rencontre("Anaibito0") {
            @Override
            public void rencontre(Joueur joueur) {
                if (joueur.getCartes().contains(listPanoramaRiziere.get(2))) {

                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Riziere");
                    alert.setContentText(null);

                    for (int i = 0; i < listPanoramaRiziere.size(); i++) {
                        if (!listJoueur.get(0).cartes.contains(listPanoramaRiziere.get(i))) {
                            listJoueur.get(0).addCarte(listPanoramaRiziere.get(i));
                            alert.setGraphic(new ImageView("/Vue/Images/" + listPanoramaRiziere.get(i).getNom() + ".jpg"));
                            System.out.println("/Vue/Images/" + getListPanoramaRiziere().get(i).getNom() + ".jpg");
                            alert.setHeaderText("Félicitation vous visiter une riziere \n vous obtenez donc :");
                            alert.showAndWait();
                            if (i == 2 && !isRiziere()) {
                                alert.setHeaderText("Félicitaion vous êtes le premier à débloquer \n tout les panoramas rizières vous gagnez donc :");
                                alert.setGraphic(new ImageView("/Vue/Images/AcomplissementRiziere.jpg"));
                                listJoueur.get(0).addCarte(getListAcomplissement().get(0));
                                setRiziere(true);
                                alert.showAndWait();
                            }
                            return;
                        }
                    }
                }
            }
            @Override
            public String getDescription() {
                return "Félicitation vous rencontrer anaibito \n celui ci vous offre une carte panorama";
            }
        };
        listRecontre.add(anaibito0);
        listRecontre.add(anaibito0);

        Rencontre anaibit02 = new Rencontre("Anaibito2") {
            @Override
            public void rencontre(Joueur joueur) {
                if (joueur.getCartes().contains(listPanoramaMer.get(3))){

                }else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Montagne");
                    alert.setContentText(null);

                    for (int i = 0; i < getListPanoramaMontagnes().size(); i++) {
                        System.out.println(listJoueur.get(0).getCartes().contains(getListPanoramaMontagnes().get(i)));
                        if (!listJoueur.get(0).getCartes().contains(getListPanoramaMontagnes().get(i))) {
                            listJoueur.get(0).addCarte(getListPanoramaMontagnes().get(i));
                            alert.setGraphic(new ImageView("/Vue/Images/" + getListPanoramaMontagnes().get(i).getNom() + ".jpg"));
                            System.out.println("/Vue/Images/" + getListPanoramaMontagnes().get(i).getNom() + ".jpg");
                            alert.setHeaderText("Félicitation vous visiter une montagne \n vous obtenez donc :");
                            alert.showAndWait();
                            if (i==3 && !isMontagne()){
                                alert.setHeaderText("Félicitaion vous êtes le premier à débloquer \n tout les panoramas montagnes vous gagnez donc :");
                                alert.setGraphic(new ImageView("/Vue/Images/AcomplissementMontagne.jpg"));
                                listJoueur.get(0).addCarte(getListAcomplissement().get(1));
                                setMontagne(true);
                                alert.showAndWait();
                            }
                            return;
                        }
                    }
                }
            }

            @Override
            public String getDescription() {
                return "Félicitation vous rencontrer anaibito \n celui ci vous offre une carte panorama";
            }
        };
        listRecontre.add(anaibit02);
        listRecontre.add(anaibit02);

        Rencontre anaibit01 = new Rencontre("Anaibito1") {
            @Override
            public void rencontre(Joueur joueur) {
                if (joueur.getCartes().contains(listPanoramaMer.get(4))){
                    choixPanorama();

                }else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Mer");
                    alert.setContentText(null);

                    for (int i = 0; i < getListPanoramaMer().size(); i++) {
                        System.out.println(listJoueur.get(0).getCartes().contains(getListPanoramaMer().get(i)));
                        if (!listJoueur.get(0).getCartes().contains(getListPanoramaMer().get(i))) {
                            listJoueur.get(0).addCarte(getListPanoramaMer().get(i));
                            alert.setGraphic(new ImageView("/Vue/Images/" + getListPanoramaMer().get(i).getNom() + ".jpg"));
                            System.out.println("/Vue/Images/" + getListPanoramaMer().get(i).getNom() + ".jpg");
                            alert.setHeaderText("Félicitation vous visiter la mer \n vous obtenez donc :");
                            alert.showAndWait();
                            if (i==4 && !isMer()){
                                alert.setHeaderText("Félicitaion vous êtes le premier à débloquer \n tout les panoramas mer vous gagnez donc :");
                                alert.setGraphic(new ImageView("/Vue/Images/AcomplissementMer.jpg"));
                                listJoueur.get(0).addCarte(getListAcomplissement().get(2));
                                setMer(true);
                                alert.showAndWait();
                            }
                            return;
                        }
                    }
                }
            }
            @Override
            public String getDescription() {
                return "Félicitation vous rencontrer anaibito \n celui ci vous offre une carte panorama";
            }
        };
        listRecontre.add(anaibit01);
        listRecontre.add(anaibit01);
    }

    private void choixPanorama() {
        if (listJoueur.get(0).getCartes().contains(listPanoramaRiziere.get(2)) && listJoueur.get(0).getCartes().contains(listPanoramaMontagnes.get(3)) && listJoueur.get(0).getCartes().contains(listPanoramaMer.get(4))) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setHeaderText(null);
            alert1.setContentText("Vous avez déja tout les panorama");
            alert1.showAndWait();
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Panorama");
        alert.setHeaderText("Choisisez le panorama que vous voulez obtenir");
        ToggleGroup toggleGroup= new ToggleGroup();
        GridPane gridPane = new GridPane();
        if (!listJoueur.get(0).getCartes().contains(listPanoramaRiziere.get(2))){
            RadioButton radioButton1 = new RadioButton("Riziere");
            radioButton1.setToggleGroup(toggleGroup);
            gridPane.add(radioButton1,0,0);
        }
        if (!listJoueur.get(0).getCartes().contains(listPanoramaMontagnes.get(3))){
            RadioButton radioButton2 = new RadioButton("Montagne");
            radioButton2.setToggleGroup(toggleGroup);
            gridPane.add(radioButton2,1,0);
        }
        if (!listJoueur.get(0).getCartes().contains(listPanoramaRiziere.get(4))){
            RadioButton radioButton3 = new RadioButton("Mer");
            radioButton3.setToggleGroup(toggleGroup);
            gridPane.add(radioButton3,2,0);
        }
        alert.setGraphic(gridPane);
        alert.showAndWait();




    }

    public void initRepas(){ //Init de touts les repas
        for(int i=0 ; i<3 ;i++){
            listRepas.add(new Repas("Misoshiru",1,6));
            listRepas.add(new Repas("Nigirineshi",1,6));
            listRepas.add(new Repas("Dango",1,6));
        }
        for (int i=0 ; i<2 ;i++){
            listRepas.add(new Repas("Yakitori",2,6));
            listRepas.add(new Repas("Soba",2,6));
            listRepas.add(new Repas("Sushi",2,6));
            listRepas.add(new Repas("Tofu",2,6));
            listRepas.add(new Repas("Tempura",2,6));
        }
        listRepas.add(new Repas("Unagi",3,6));
        listRepas.add(new Repas("Domburi",3,6));
        listRepas.add(new Repas("Udon",3,6));
        listRepas.add(new Repas("Fugu",3,6));
        listRepas.add(new Repas("TaiMeshi",3,6));
        listRepas.add(new Repas("Sashini",3,6));
    }

    public void initSouvenir(){ //Init de touts les souvenirs
        listSouvenir.add(new Souvenirs("Yunomi",1,1));
        listSouvenir.add(new Souvenirs("Gofu",1,1));
        listSouvenir.add(new Souvenirs("Koma",1,1));
        listSouvenir.add(new Souvenirs("Hashi",1,1));
        listSouvenir.add(new Souvenirs("Uchiwa",1,1));
        listSouvenir.add(new Souvenirs("Washi",1,1));

        listSouvenir.add(new Souvenirs("Konpeito",1,2));
        listSouvenir.add(new Souvenirs("Manju",1,2));
        listSouvenir.add(new Souvenirs("Kamaboko",1,2));
        listSouvenir.add(new Souvenirs("Daifuku",2,2));
        listSouvenir.add(new Souvenirs("Ucha",2,2));
        listSouvenir.add(new Souvenirs("Sake",2,2));

        listSouvenir.add(new Souvenirs("Sandogasa",2,3));
        listSouvenir.add(new Souvenirs("Yukata",2,3));
        listSouvenir.add(new Souvenirs("Furoshiki",2,3));
        listSouvenir.add(new Souvenirs("Geta",2,3));
        listSouvenir.add(new Souvenirs("KanZashi",2,3));
        listSouvenir.add(new Souvenirs("Haori",2,3));

        listSouvenir.add(new Souvenirs("Metsuke",2,4));
        listSouvenir.add(new Souvenirs("Jubako",2,4));
        listSouvenir.add(new Souvenirs("Shikki",2,4));
        listSouvenir.add(new Souvenirs("Shanisen",3,4));
        listSouvenir.add(new Souvenirs("Sumie",3,4));
        listSouvenir.add(new Souvenirs("Ukiyoe",3,4));
    }

    public void initPanorama(){ //Init des cartes Panoramas
        listPanoramaRiziere.add(new Panoramas("Riziere0",1,"Riziere"));
        listPanoramaRiziere.add(new Panoramas("Riziere1",2,"Riziere"));
        listPanoramaRiziere.add(new Panoramas("Riziere2",3,"Riziere"));

        listPanoramaMontagnes.add(new Panoramas("Montagne0",1,"Montagne"));
        listPanoramaMontagnes.add(new Panoramas("Montagne1",2,"Montagne"));
        listPanoramaMontagnes.add(new Panoramas("Montagne2",3,"Montagne"));
        listPanoramaMontagnes.add(new Panoramas("Montagne3",4,"Montagne"));

        listPanoramaMer.add(new Panoramas("Mer0",1,"Mer"));
        listPanoramaMer.add(new Panoramas("Mer1",2,"Mer"));
        listPanoramaMer.add(new Panoramas("Mer2",3,"Mer"));
        listPanoramaMer.add(new Panoramas("Mer3",4,"Mer"));
        listPanoramaMer.add(new Panoramas("Mer4",5,"Mer"));


    }

    public void initSource(){ //Init des Cartes Source chaude
        for (int j=0 ; j<2 ;j++) {
            for (int i = 0; i < 6; i++) {
                listSource.add(new Sources("Source" + j, j+2));
            }
        }
    }

    public ArrayList<Integer> getListArretDouble() {
        return listArretDouble;
    }

    public void setListArretDouble(ArrayList<Integer> listtArretDouble) {
        this.listArretDouble = listtArretDouble;
    }

    public boolean isRiziere() {
        return riziere;
    }

    public void setRiziere(boolean riziere) {
        this.riziere = riziere;
    }

    public boolean isMontagne() {
        return montagne;
    }

    public void setListAcomplissement(ArrayList<Acomplissement> listAcomplissement) {
        this.listAcomplissement = listAcomplissement;
    }
    public ArrayList<Acomplissement> getListAcomplissement(){
        return this.listAcomplissement;
    }


    public void setMontagne(boolean montagne) {
        this.montagne = montagne;
    }

    public boolean isMer() {
        return mer;
    }

    public void setMer(boolean mer) {
        this.mer = mer;
    }

    public ArrayList<Rencontre> getListRecontre() {
        return listRecontre;
    }

    public ArrayList<Souvenirs> getListSouvenir() {
        return listSouvenir;
    }

    public ArrayList<Panoramas> getListPanoramaMer() {
        return listPanoramaMer;
    }

    public ArrayList<Panoramas> getListPanoramaMontagnes() {
        return listPanoramaMontagnes;
    }

    public ArrayList<Panoramas> getListPanoramaRiziere() {
        return listPanoramaRiziere;
    }

    public ArrayList<Sources> getListSource() {
        return listSource;
    }

    public ArrayList<Repas> getListRepas() {
        return listRepas;
    }

    public ArrayList<Joueur> getListJoueur() {
        return listJoueur;
    }

    public ArrayList<Joueur> getRecapJoueur() {
        return recapJoueur;
    }

    public void setRecapJoueur(ArrayList<Joueur> recapJoueur) {
        this.recapJoueur = recapJoueur;
    }

    public void setListJoueur(ArrayList<Joueur> listJoueur) {
        this.listJoueur = listJoueur;
    }

    public ArrayList<Integer> getListOrTemple() {
        return listOrTemple;
    }

    public void setListOrTemple(ArrayList<Integer> listOrTemple) {
        this.listOrTemple = listOrTemple;
    }


    public void addJoueur(Joueur j){
        listJoueur.add(j);
        recapJoueur.add(j);
}

    public void trieJoueur() {
        boolean permut;
        Joueur tampon;
        int i;
        do{
            permut = false;
            for(i=0; i<listJoueur.size()-1; i++){
                if(listJoueur.get(i).getPositions() > listJoueur.get(i+1).getPositions()){
                    tampon = listJoueur.get(i);
                    listJoueur.set(i,listJoueur.get(i+1));
                    listJoueur.set(i+1,tampon);
                    permut = true;
                }
            }
        }while(permut);
        }

    public void trieJoueurScore() {
        boolean permut;
        Joueur tampon;
        int i;
        do{
            permut = false;
            for(i=0; i<listJoueur.size()-1; i++){
                if(listJoueur.get(i).getPoints() < listJoueur.get(i+1).getPoints()){
                    tampon = listJoueur.get(i);
                    listJoueur.set(i,listJoueur.get(i+1));
                    listJoueur.set(i+1,tampon);
                    permut = true;
                }
            }
        }while(permut);
    }

    public void majScore() {
        for (Joueur joueur : listJoueur) {
            joueur.updateScore();
        }
    }

    public void trieJoueurOrTemple(){
        boolean permut;
        Joueur tampon;
        int i;
        do{
            permut = false;
            for(i=0; i<listJoueur.size()-1; i++){
                if(listJoueur.get(i).getOrTemple() < listJoueur.get(i+1).getOrTemple()){
                    tampon = listJoueur.get(i);
                    listJoueur.set(i,listJoueur.get(i+1));
                    listJoueur.set(i+1,tampon);
                    permut = true;
                }
            }
        }while(permut);

    }
}
