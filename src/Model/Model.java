package Model;

import java.util.ArrayList;
import java.util.Collections;

public class Model {
    private boolean riziere;
    private boolean montagne;
    private boolean mer;
    private boolean createur;
    private ArrayList<Rencontre>listRecontre ;
    private ArrayList<Repas>listRepas  ;
    private ArrayList<Souvenirs>listSouvenir ;
    private ArrayList<Panoramas>listPanoramaMer ;
    private ArrayList<Panoramas>listPanoramaMontagnes ;
    private ArrayList<Panoramas>listPanoramaRiziere ;
    private ArrayList<Sources>listSource ;
    private ArrayList<Joueur>listJoueur ;
    private ArrayList<Integer>listOrTemple ;
    private ArrayList<Acomplissement>listAcomplissement ;
    private ArrayList<Integer> listArretDouble;
    private ArrayList<Integer> listArretRelais ;
    private ArrayList<Joueur>recapJoueur;
    private Repas choucroute;
    private Repas fischer;

    public Model(){
        createur=false;
        listRecontre = new ArrayList<Rencontre>();
        listRepas = new ArrayList<Repas>();
        listSouvenir = new ArrayList<Souvenirs>();
        listPanoramaMer = new ArrayList<Panoramas>();
        listPanoramaMontagnes = new ArrayList<Panoramas>();
        listPanoramaRiziere = new ArrayList<Panoramas>();
        listSource = new ArrayList<Sources>();
        listJoueur = new ArrayList<Joueur>();
        listOrTemple = new ArrayList<Integer>();
        listAcomplissement = new ArrayList<Acomplissement>();
        listArretDouble = new ArrayList<Integer>();
        listArretRelais = new ArrayList<Integer>();
        recapJoueur = new ArrayList<Joueur>();
        choucroute = new Repas("Choucroute",0,6);
        fischer = new Repas("Fischer",0,6);
    }

    /**
     * Initialise la partie en appelant les différentes fonctions INIT qui mélanges les cartes et les instancient
     */
    public void initPartie(){
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

    /**
     * Donne la position de chaque arrêt double du jeu (ne marche qu'à 4 ou 5 joueurs)
     */
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

    /**
     * Même principe que pour les arrets doubles
     */
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

    /**
     * Initie les accomplissements panoramas
     */
    private void initAccomplissement() {
        listAcomplissement.add(new Acomplissement("AcomplissementRiziere"));
        listAcomplissement.add(new Acomplissement("AcomplissementMontagne"));
        listAcomplissement.add(new Acomplissement("AcomplissementMer"));

    }

    /**
     * Initie les différentes rencontres et si l'utilisateur à cocher les cartes créatrices les instancie et les ajoute dans la liste
     */
    private void initRencontre(){
        listRecontre.add(new Rencontre("Samurai","Vous gagnez 3 points"));
        listRecontre.add(new Rencontre("Samurai","Vous gagnez 3 points"));
        listRecontre.add(new Rencontre("Kuge","Vous gagnez 3 pieces d'or"));
        listRecontre.add(new Rencontre("Kuge","Vous gagnez 3 pieces d'or"));
        listRecontre.add(new Rencontre("Miko","Vous placez une piece au temple \n vous gagnez un point"));
        listRecontre.add(new Rencontre("Miko","Vous placez une piece au temple \n vous gagnez un point"));
        listRecontre.add(new Rencontre("Shokunin","Vous gagnez une carte souvenir"));
        listRecontre.add(new Rencontre("Shokunin","Vous gagnez une carte souvenir"));
        listRecontre.add(new Rencontre("Anaibito0","Vous gagnez une carte panorama riziere"));
        listRecontre.add(new Rencontre("Anaibito0","Vous gagnez une carte panorama riziere"));
        listRecontre.add(new Rencontre("Anaibito1","Vous gagnez une carte panorama mer"));
        listRecontre.add(new Rencontre("Anaibito1","Vous gagnez une carte panorama mer"));
        listRecontre.add(new Rencontre("Anaibito2","Vous gagnez une carte panorama montagne"));
        listRecontre.add(new Rencontre("Anaibito2","Vous gagnez une carte panorama montagne"));
        if (createur){
            listRecontre.add(new Rencontre("Raphael","HAHAHA , vous perdez 2 pièce d'or"));
            listRecontre.add(new Rencontre("Raphael","HAHAHA , vous perdez 2 pièce d'or"));
            listRecontre.add(new Rencontre("Antoine","Vous pouvez désormais donner une pièce de plus \n au temple"));
            listRecontre.add(new Rencontre("Antoine","Vous pouvez désormais donner une pièce de plus \n au temple"));
            listRecontre.add(new Rencontre("Aurélien","Vous rencontré l'ami alsacien \n il vous offre en cadeau un repas typique"));
            listRecontre.add(new Rencontre("Aurélien","Vous rencontré l'ami alsacien \n il vous offre en cadeau un repas typique"));

        }
    }

    /**
     * Initialise les différentes cartes repas du jeu et les ajoute dans la liste
     */
    private void initRepas(){
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

    /**
     * Initialise les souvenirs ainsi que leurs types
     */
    private void initSouvenir(){
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

    /**
     * Initialise les panoramas avec le nombre de points pour chaque carte et les ajoute dans des listes propres à chaque panorama
     */
    private void initPanorama(){
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

    /**
     * Initialise les sources celle qui valent 2 points et celle qui en valen 3
     */
    private void initSource(){
        for (int j=0 ; j<2 ;j++) {
            for (int i = 0; i < 6; i++) {
                listSource.add(new Sources("Source" + j, j+2));
            }
        }
    }

    /**
     * Mélange toutes les cartes
     */
    private void shuffle(){
        Collections.shuffle(listRecontre);
        Collections.shuffle(listRepas);
        Collections.shuffle(listSouvenir);
        Collections.shuffle(listSource);
    }

    /**
     * Trie les joueurs en fonction de leur position ainsi le dernier joueur sur le plateau est placé en début de la liste
     */
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

    /**
     * Trie les joueurs en fonction de leur score pour le classement en fin de partie
     */
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

    /**
     * Trie les joueurs en fonction de leurs or donner au temple pour la fin de partie
     */
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

    /**
     * Applique la fonction qui calcule le score à tous les joueurs
     */
    public void majScore() {
        for (Joueur joueur : listJoueur) {
            joueur.updateScore();
        }
    }

    public ArrayList<Integer> getListArretDouble() {
        return listArretDouble;
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

    public ArrayList<Integer> getListOrTemple() {
        return listOrTemple;
    }

    public Repas getFischer() {
        return fischer;
    }

    public Repas getChoucroute() {
        return choucroute;
    }

    public void addJoueur(Joueur j){
        listJoueur.add(j);
        recapJoueur.add(j);
}
    public ArrayList<Integer> getListArretRelais() {
        return listArretRelais;
    }

    public boolean isCreateur() {
        return createur;
    }

    public void setCreateur(boolean createur) {
        this.createur = createur;
    }

}
