package Model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class JoueurTest {

    @Test
    public void testContienRien(){
        Joueur joueur = new Joueur();

        Assert.assertFalse(joueur.contient(null));
    }

    @Test
    public void testContientRepas(){
        Joueur joueur = new Joueur();
        Repas repas = new Repas("R1");
        joueur.addCarte(repas);

        Assert.assertTrue(joueur.contient(repas));
    }

    @Test
    public void testContientRepasMultiple(){
        Joueur joueur = new Joueur();
        Repas repas = new Repas("R1");
        Repas repas1 = new Repas("R2");
        joueur.addCarte(repas);
        joueur.addCarte(repas1);

        Assert.assertTrue(joueur.contient(repas));
    }

    @Test
    public void testContientRepasMultipleInvalide(){
        Joueur joueur = new Joueur();
        Repas repas = new Repas("R1");
        Repas repas1 = new Repas("R2");
        Repas repas2 = new Repas("R3");
        joueur.addCarte(repas);
        joueur.addCarte(repas1);

        Assert.assertFalse(joueur.contient(repas2));
    }

}