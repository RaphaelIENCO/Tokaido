package Model;

import java.util.ArrayList;

public class Joueur {
    int gold;
    ArrayList<Cartes> cartes;
    int points;
    String nom;
    boolean allowedToPlay;

    //Crée un joueur différent des joueurs proposés.
    public Joueur(int g,String n){
        gold = g;
        nom = n;
        cartes = new ArrayList<>();
        points = 0;
        allowedToPlay=false;
   }

   public Joueur(){
       cartes = new ArrayList<>();
       points = 0;
       allowedToPlay=false;
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

    public int getPoints() {
        return points;
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

    public boolean isAllowedToPlay() {
        return allowedToPlay;
    }

    public void setAllowedToPlay(boolean allowedToPlay) {
        this.allowedToPlay = allowedToPlay;
    }
}
