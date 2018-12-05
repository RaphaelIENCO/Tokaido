package Model;

public class Acomplissement extends Cartes {
    protected static final int point = 3;

    public Acomplissement(String nom){
        super(nom);
    }


    public int getPoints() {
        return point;
    }

}
