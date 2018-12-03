package Model;

import com.sun.corba.se.impl.interceptors.CDREncapsCodec;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

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

    @Test
    public void testUpdateScoreVide(){
        Joueur joueur = new Joueur();
        joueur.updateScore();

        Assert.assertEquals(0,joueur.getPoints());
    }

    @Test
    public void testUpdateScorePointSansCarte(){
        Joueur joueur = new Joueur();

        joueur.setPoints(10);
        joueur.updateScore();

        Assert.assertEquals(0,joueur.getPoints());
    }

    @Test
    public void testUpdateScorePointAvecCartePanorama(){
        Joueur joueur = new Joueur();
        Panoramas panoramas = new Panoramas("P1",10,"Riziere");

        joueur.addCarte(panoramas);
        joueur.updateScore();

        Assert.assertEquals(10,joueur.getPoints());
    }

    @Test
    public void testUpdateScorePointAvecCarteRepas(){
        Joueur joueur = new Joueur();
        Repas repas = new Repas("P1");
        repas.setPoint(10);

        joueur.addCarte(repas);
        joueur.updateScore();

        Assert.assertEquals(10,joueur.getPoints());
    }

    @Test
    public void testUpdateScorePointAvecCarteSources(){
        Joueur joueur = new Joueur();
        Sources sources = new Sources("P1",10);

        joueur.addCarte(sources);
        joueur.updateScore();

        Assert.assertEquals(10,joueur.getPoints());
    }

    @Test
    public void testUpdateScorePointAvecCarteAccomplissement(){
        Joueur joueur = new Joueur();
        Acomplissement acomplissement = new Acomplissement("AC");

        joueur.addCarte(acomplissement);
        joueur.updateScore();

        Assert.assertEquals(3,joueur.getPoints());
    }

    @Test
    public void testTrierCarteVide(){
        Joueur joueur = new Joueur();

        joueur.trierCarte();

        Assert.assertEquals(0,joueur.getCartes().size());
    }

    @Test
    public void testTrierCarte(){
        boolean verif = false;

        Joueur joueur = new Joueur();
        Repas repas = new Repas("R1");
        Souvenirs souvenirs = new Souvenirs("S1");
        Rencontre rencontre = new Rencontre("RE1") {
            @Override
            public void rencontre(Joueur joueur) {
            }

            @Override
            public String getDescription() {
                return null;
            }
        };
        Sources sources = new Sources("SO1",0);
        Acomplissement acomplissement = new Acomplissement("A1");

        joueur.addCarte(souvenirs);
        joueur.addCarte(repas);
        joueur.addCarte(rencontre);
        joueur.addCarte(acomplissement);
        joueur.addCarte(sources);

        Collections.shuffle(joueur.getCartes());

        joueur.trierCarte();

        if (joueur.getCartes().get(0) == repas && joueur.getCartes().get(1) == souvenirs
                && joueur.getCartes().get(2) == rencontre && joueur.getCartes().get(3) == sources
                && joueur.getCartes().get(4) == acomplissement){
            verif = true;
        }

        Assert.assertTrue(verif);
    }







}