package Model;

public abstract class Rencontre extends Cartes {
    public Rencontre(String nom){
        super(nom);
    }

    public abstract void rencontre(Joueur joueur);
    public abstract String getDescription();
}
