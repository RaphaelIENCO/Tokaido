package Model;

import javafx.print.Collation;

import java.util.ArrayList;
import java.util.Collections;

public class Model {   // classe dans laquel on initie toutes les carte du joueur.
    private ArrayList<Rencontre>listRecontre = new ArrayList<Rencontre>();
    private ArrayList<Repas>listRepas  = new ArrayList<>();
    private ArrayList<Souvenirs>listSouvenir = new ArrayList<>();
    private ArrayList<Panoramas>listPanoramaMer = new ArrayList<>();
    private ArrayList<Panoramas>listPanoramaMontagnes = new ArrayList<>();
    private ArrayList<Panoramas>listPanoramaRiziere = new ArrayList<>();
    private ArrayList<Sources>listSource = new ArrayList<>();
    private ArrayList<Joueur>listJoueur = new ArrayList<Joueur>();

    public Model(){
        initPartie();
    }

    public void initPartie(){ //Creation de toutes les differentes cartes du jeu dans differents packets
        initRencontre();
        initRepas();
        initSouvenir();
        initPanorama();
        initSource();
        shuffle();
        listJoueur = new ArrayList<Joueur>();
        Joueur joueur= new Joueur(3,"bob");
        listJoueur.add(joueur);
    }

    public void shuffle(){
        Collections.shuffle(listRecontre);
        Collections.shuffle(listRepas);
        Collections.shuffle(listSouvenir);
        Collections.shuffle(listPanoramaRiziere);
        Collections.shuffle(listPanoramaMontagnes);
        Collections.shuffle(listPanoramaMer);
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
        for (int j=0 ; j<5 ;j++) {
            for (int i = 0; i < 5; i++) {
                if (j<3) {
                    listPanoramaRiziere.add(new Panoramas("Riziere" + j, j));
                }
                if (j<4) {
                    listPanoramaMontagnes.add(new Panoramas("Montagne" + j, j));
                }
                listPanoramaMer.add(new Panoramas("Mer" + j, j));
            }
        }
    }

    public void initSource(){ //Init des Cartes Source chaude
        for (int j=0 ; j<2 ;j++) {
            for (int i = 0; i < 6; i++) {
                listSource.add(new Sources("Source" + j, j+2));
            }
        }
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

    public void setListRepas(ArrayList<Repas> listRepas) {
        this.listRepas = listRepas;
    }

    public ArrayList<Joueur> getListJoueur() {
        return listJoueur;
    }

    public String getScore() {
        String str = "";
        for (int i=0 ; i<listJoueur.size() ;i++){
            str += listJoueur.get(i).getNom() + " a ";
            str += listJoueur.get(i).getPoints() + " points et dispose de ";
            str += listJoueur.get(i).getGold() + " gold. \n";
        }
        return str;
    }
}
