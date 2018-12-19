package Model;

public class Sources extends Cartes {
    private int point;

    /**
     * Les cartes sources chaudes rapportent deux ou trois points
     **/
    public Sources(String nom, int point){
        super(nom);
        this.point = point;
    }

    public int getPoint() {
        return point;
    }

}
