package Model;

public class Panoramas extends Cartes{
    private int point;
    private String type;

    /**
     * Il existe 3 types de panorama Rizière, Montagne et Mer et chaque panorama est composé de plusieurs cartes (3,4 et 5)
     */
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
