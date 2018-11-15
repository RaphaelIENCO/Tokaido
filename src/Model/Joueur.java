package Model;

import java.util.ArrayList;

public class Joueur {
    int gold;
    ArrayList<Cartes> cartes;
    int points;
    String nom;

    //Crée un joueur différent des joueurs proposés.
    public Joueur(int g,String n){
        gold = g;
        nom = n;
        cartes = new ArrayList<>();
        points = 0;
   }

   public Joueur(){
       cartes = new ArrayList<>();
       points = 0;
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


}
