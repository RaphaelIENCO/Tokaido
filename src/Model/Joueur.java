package Model;

import java.util.ArrayList;

public class Joueur {
    protected int gold;
    protected ArrayList<Cartes> cartes;
    protected int points = 0;
    protected String nom;
    protected boolean piocheRelais;
    protected boolean piocheSource;
    protected boolean piocheSouvenir;
    protected boolean piocheRencontre;
    protected int positions;
    protected String couleur;
    protected int orTemple;


    protected boolean temple;
    //Crée un joueur différent des joueurs proposés.
    public Joueur(int g,String n){
        gold = g;
        nom = n;
        cartes = new ArrayList<Cartes>();
        points = 0;
        piocheRelais =false;
        piocheRencontre=false;
        piocheSource=false;
        piocheSouvenir=false;
        temple=false;
        orTemple=0;
        positions=0;
   }

   public Joueur(){
       cartes = new ArrayList<Cartes>();
       points = 0;
       piocheRelais =false;
       piocheRencontre=false;
       piocheSource=false;
       piocheSouvenir=false;
       temple = false;
       orTemple=0;
       positions=0;
   }

    public void updateScore(){
       int total = 0;

        for (int i=0; i<cartes.size() ;i++){
            if (cartes.get(i) instanceof Repas){
                Repas repas = (Repas) cartes.get(i);
                total+= repas.getPoint();
            }
            else if (cartes.get(i) instanceof Sources){
                Sources sources = (Sources) cartes.get(i);
                total += sources.getPoint();
            }
            else if (cartes.get(i) instanceof Panoramas){
                Panoramas panoramas = (Panoramas) cartes.get(i);
                total += panoramas.getPoint();
            }
            else if (cartes.get(i) instanceof Acomplissement){
                Acomplissement acomplis = (Acomplissement) cartes.get(i);
                total += acomplis.getPoints();
            }else if(cartes.get(i) instanceof Rencontre && cartes.get(i).getNom().equals("Samurai")) total+=3;
            else if(cartes.get(i) instanceof Rencontre && cartes.get(i).getNom().equals("Miko")) total+=1;

        }
        total += getScoreSouvenir();
        points=total;
   }

    private int getScoreSouvenir(){
        int total = 0;
        int coef = 1;
        boolean verif1 = true;
        boolean verif2 = true;
        boolean verif3 = true;
        boolean verif4 = true;
        ArrayList<Souvenirs> souv = new ArrayList<Souvenirs>();
        int tab[] = new int[8];
       for (Object carte : cartes) {
           if (carte instanceof Souvenirs) {
               souv.add((Souvenirs) carte);
           }
       }
        for (int i=0; i<souv.size() ;i++){
            for (int j=1 ; j<5 ;j++){
                if (souv.get(i).getTypeSouvenir() == j){
                    tab[j-1]++;
                }
            }
        }
        for (int i=0; i<souv.size() ;i++){
            if (souv.get(i).getTypeSouvenir() == 1 && verif1){
                total += tab[0]*coef;
                coef += 2;
                verif1 = false;
            }
            if (souv.get(i).getTypeSouvenir() == 2 && verif2){
                total += tab[1]*coef;
                coef += 2;
                verif2 = false;
            }
            if (souv.get(i).getTypeSouvenir() == 3 && verif3){
                total += tab[2]*coef;
                coef += 2;
                verif3 = false;
            }
            if (souv.get(i).getTypeSouvenir() == 4 && verif4){
                total += tab[3]*coef;
                coef += 2;
                verif4 = false;
            }
        }
        return total;
   }

    public int getPoints(){
        return this.points;
   }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addCarte(Cartes carte){
        cartes.add(carte);
    }

    public ArrayList<Cartes> getCartes() {
        return cartes;
    }

    public void setCartes(ArrayList<Cartes> cartes) {
        this.cartes = cartes;
    }

    public int getPositions() {
        return positions;
    }

    public void setPositions(int positions) {
        this.positions = positions;
    }

    public boolean isPiocheRelais() {
        return piocheRelais;
    }

    public void setPiocheRelais(boolean piocheRelais) {
        this.piocheRelais = piocheRelais;
    }

    public boolean isPiocheSource() {
        return piocheSource;
    }

    public void setPiocheSource(boolean piocheSource) {
        this.piocheSource = piocheSource;
    }

    public boolean isPiocheSouvenir() {
        return piocheSouvenir;
    }

    public void setPiocheSouvenir(boolean piocheSouvenir) {
        this.piocheSouvenir = piocheSouvenir;
    }

    public boolean isPiocheRencontre() {
        return piocheRencontre;
    }

    public void setPiocheRencontre(boolean piocheRencontre) {
        this.piocheRencontre = piocheRencontre;
    }

    public boolean isTemple() {
        return temple;
    }

    public void setTemple(boolean temple) {
        this.temple = temple;
    }

    public int getOrTemple() {
        return orTemple;
    }

    public void setOrTemple(int orTemple) {
        this.orTemple = orTemple;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public boolean contient(Repas repas) {
        for (Cartes cartes : cartes){
            if (cartes instanceof Repas){
                if (cartes.getNom().equals(repas.getNom())) return true;
            }
        }
        return false;
    }
    public void trierCarte(){
        ArrayList<Cartes> repas = new ArrayList<Cartes>();
        ArrayList<Cartes> souvenirs = new ArrayList<Cartes>();
        ArrayList<Cartes> rencontres = new ArrayList<Cartes>();
        ArrayList<Cartes> sources = new ArrayList<Cartes>();
        ArrayList<Cartes> acomplissements = new ArrayList<Cartes>();
        for (int i = 0; i < cartes.size(); i++) {
            if (cartes.get(i) instanceof Repas ){
                repas.add(cartes.get(i));
            }
            if (cartes.get(i) instanceof Souvenirs){
                souvenirs.add(cartes.get(i));
            }
            if (cartes.get(i) instanceof Rencontre){
                rencontres.add(cartes.get(i));
            }
            if (cartes.get(i) instanceof Sources){
                sources.add(cartes.get(i));
            }
            if (cartes.get(i) instanceof Acomplissement){
                acomplissements.add(cartes.get(i));
            }
        }
        cartes.clear();
        cartes.addAll(repas);
        cartes.addAll(souvenirs);
        cartes.addAll(rencontres);
        cartes.addAll(sources);
        cartes.addAll(acomplissements);
    }
}
