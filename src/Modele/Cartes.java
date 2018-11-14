package Modele;

public class Cartes {
    protected String nom;

    public Cartes(){
    }

    public Cartes(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
