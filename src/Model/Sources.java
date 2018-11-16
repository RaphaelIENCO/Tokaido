package Model;

public class Sources extends Cartes {
    private int point;

    public Sources(String nom, int point){
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
