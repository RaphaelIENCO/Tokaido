package Model;

public class Yoshiyasu extends Joueur {
    /**
    Lors  de  chaque  rencontre,  Yoshiyasu  pioche  2
cartes  Rencontre,  conserve  celle  de  son  choix
et  replace  la  seconde  au-dessous  de  la  pioche
(sans la montrer aux autres joueurs).
     */

    public Yoshiyasu() {
        super();
        nom = "Yoshiyasu";
        gold = 9;
        couleur = "#3333CC";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
