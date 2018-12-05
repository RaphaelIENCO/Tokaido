package Model;

public class Satsuki extends Joueur {
    /**
     * Lors de son arrivée au Relais, Satsuki reçoit aléatoire-
     * ment et gratuitement 1 carte Repas.
     * Précision : elle peut éventuellement renoncer à
     * cette carte gratuite pour acheter un Repas selon
     * la règle usuelle.
     */
    public Satsuki() {
        super();
        nom = "Satsuki";
        gold = 2;
        couleur = "#9900FF";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
