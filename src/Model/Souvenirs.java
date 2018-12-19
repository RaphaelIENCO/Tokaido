package Model;

public class Souvenirs extends Cartes{
    private int prix;
    private int typeSouvenir;

    /**
     * Les souvenirs possèdent un type et un prix
     */
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


    public int getTypeSouvenir() {
        return typeSouvenir;
    }


    @Override
    public String toString() {
        return "Souvenirs{" +
                "prix=" + prix +
                ", typeSouvenir=" + typeSouvenir +
                ", nom='" + nom + '\'' +
                '}';
    }
}
