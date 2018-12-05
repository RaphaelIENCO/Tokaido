package Model;

public class Umegae extends Joueur {
    /**
     * Umegae  gagne  1  point  et  1  pièce  lors  de
     * chaque rencontre.
     * Précision : ce gain a lieu avant d’appliquer les
     * effets de la carte Rencontre piochée.
     */
    public Umegae() {
        super();
        nom = "Umegae";
        gold = 5;
        couleur = "#CC0033";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
