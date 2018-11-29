package Model;

public class Hirotada extends Joueur {
    /**
     * A chaque arrêt au Temple, le joueur peut prendre
     * une pièce de la réserve et la placer au Temple (il
     * marque  1  point  pour  cette  pièce).  Ce,  en  plus
     * des 1, 2 ou 3 pièces qu’il peut normalement offrir
     * au Temple.
     */
    public Hirotada() {
        super();
        nom = "Hirotada";
        gold = 8;
        couleur = "lawngreen";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
