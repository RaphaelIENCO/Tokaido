package Model;

public class Repas extends Cartes{
    private int prix;
    private int point;


    /**
     * Les repas s'achètent aux relais et ont un prix entre 1 et 3 PO et ils rapportent tous 6 points
     **/
    public Repas(String nom){
        super(nom);
    }

    public Repas(String nom,int prix,int point) {
        super(nom);
        this.prix = prix;
        this.point = point;
    }

    public int getPrix() {
        return prix;
    }

    public int getPoint(){
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "Repas{" +
                "prix=" + prix +
                ", point=" + point +
                ", nom='" + nom + '\'' +
                '}';
    }
}
