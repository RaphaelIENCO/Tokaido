package Model;

public class Panoramas extends Cartes{
    private int point;

    public Panoramas(String nom, int point){
        super(nom);
        this.point = point;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
