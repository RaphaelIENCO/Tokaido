package Model;

public class ZenEmon extends Joueur {
    /**
     * Zen-emon peut acheter l’un des Souvenirs pour
     * 1 seule pièce au lieu du prix indiqué, une fois par
     * Echoppe.
     */
    public ZenEmon() {
        super();
        nom = "ZenEmon";
        gold = 6    ;
        couleur = "orangered";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
