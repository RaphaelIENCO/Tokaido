package Model;

public class Acomplissement extends Cartes {
    private static final int point = 3;

    /**
     *Les accomplissements sont des cartes distribuer en fin de partie et qui dépendent de chaque joueur
     */
    public Acomplissement(String nom){
        super(nom);
    }


    public int getPoints() {
        return point;
    }

}
