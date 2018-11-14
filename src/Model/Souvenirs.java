package Model;

public class Souvenirs extends Cartes{
    protected int prix;
    protected int typeSouvenir;

    public Souvenirs(String nom){
        super(nom);
    }

    public Souvenirs(String nom, int prix, int typeSouvenir){
        super(nom);
        this.prix = prix;
        this.typeSouvenir = typeSouvenir;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getTypeSouvenir() {
        return typeSouvenir;
    }

    public void setTypeSouvenir(int typeSouvenir) {
        this.typeSouvenir = typeSouvenir;
    }
}
