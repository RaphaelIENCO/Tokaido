package Model;

public class Kinko extends Joueur {
    /**
     * Les cartes Repas achetées par Kinko lui coûtent
     * une pièce de moins (les Repas de valeur 1 sont
     * donc gratuits).
     */


    public Kinko() {
        super();
        nom = "Kinko";
        gold = 7;
        couleur = "#33CCFF";
    }

    @Override
    public String toString() {
        return super.toString();
    }


}
