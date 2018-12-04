package Model;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
        listArretDouble.add(2);
        listArretDouble.add(7);
        listArretDouble.add(9);
        listArretDouble.add(11);
        listArretDouble.add(14);
        listArretDouble.add(17);
        listArretDouble.add(28);
        listArretDouble.add(30);
        listArretDouble.add(32);
        listArretDouble.add(34);
        listArretDouble.add(37);
        listArretDouble.add(40);
        listArretDouble.add(51);
        listArretDouble.add(54);
        listArretDouble.add(57);
        listArretDouble.add(60);
        listArretDouble.add(62);
        listArretDouble.add(66);
        listArretDouble.add(74);
        listArretDouble.add(77);
        listArretDouble.add(80);
        listArretDouble.add(82);
        listArretDouble.add(86);
        listArretDouble.add(88);

    }
    private void initRelais(){
        listArretRelais.add(21);
        listArretRelais.add(22);
        listArretRelais.add(23);
        listArretRelais.add(24);
        listArretRelais.add(44);
        listArretRelais.add(45);
        listArretRelais.add(46);
        listArretRelais.add(47);
        listArretRelais.add(68);
        listArretRelais.add(69);
        listArretRelais.add(70);
        listArretRelais.add(71);
        listArretRelais.add(91);
        listArretRelais.add(92);
        listArretRelais.add(93);
        listArretRelais.add(94);
    }

    private void initAccomplissement() {
        listAcomplissement.add(new Acomplissement("AcomplissementRiziere"));
        listAcomplissement.add(new Acomplissement("AcomplissementMontagne"));
        listAcomplissement.add(new Acomplissement("AcomplissementMer"));

    }

    public ArrayList<Integer> getListArretRelais() {
        return listArretRelais;
    }

    public void setListArretRelais(ArrayList<Integer> listArretRelais) {
        this.listArretRelais = listArretRelais;
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
