package Model;

public class Acomplis extends Cartes {
    protected int point;

    public Acomplis(String nom){
        super(nom);
    }

    public Acomplis(String nom, int point){
        super(nom);
        this.point = point;
    }

    public int getPoints() {
        return point;
    }

    public void setPoints(int point) {
        this.point = point;
    }
}
