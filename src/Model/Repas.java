package Model;

public class Repas extends Cartes{
    protected int prix;
    protected int point;

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

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getPoint(){
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
