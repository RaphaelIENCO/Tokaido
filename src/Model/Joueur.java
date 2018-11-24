package Model;

import java.util.ArrayList;

public class Joueur {
    protected int gold;
    protected ArrayList<Cartes> cartes;
    protected int points;
    protected String nom;
    protected boolean piocheRelais;
    protected boolean piocheSource;
    protected boolean piocheSouvenir;
    protected boolean piocheRencontre;
    //Crée un joueur différent des joueurs proposés.
    public Joueur(int g,String n){
        gold = g;
        nom = n;
        cartes = new ArrayList<>();
        points = 0;
        piocheRelais =false;
        piocheRencontre=false;
        piocheSource=false;
        piocheSouvenir=false;
   }

   public Joueur(){
       cartes = new ArrayList<>();
       points = 0;
       piocheRelais =false;
       piocheRencontre=false;
       piocheSource=false;
       piocheSouvenir=false;
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
}
