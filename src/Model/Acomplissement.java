package Model;

public class Acomplissement extends Cartes {
    protected int point;

    public Acomplissement(String nom){
        super(nom);
    }

    public Acomplissement(String nom, int point){
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
