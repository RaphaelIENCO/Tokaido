package Model;

public class Panoramas extends Cartes{
    private int point;
    private String type;

    public Panoramas(String nom, int point,String type){
        super(nom);
        this.point = point;
        this.type=type;
    }

    public int getPoint() {
        return point;
    }

    public String getType() {
        return type;
    }
}
