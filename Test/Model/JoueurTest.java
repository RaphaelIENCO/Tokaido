package Model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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
    public void testUpdateScoreAvecMitsu(){
        Joueur joueur = new Joueur();
        joueur.setNom("Mitsukuni");

        Sources sources = new Sources("S1",0);

        joueur.addCarte(sources);

        joueur.updateScore();

        Assert.assertEquals(1,joueur.getPoints());
    }

    @Test
    public void testUpdateScoreAvecMitsuAccom(){
        Joueur joueur = new Joueur();
        joueur.setNom("Mitsukuni");

        Acomplissement acomplissement = new Acomplissement("A1");

        joueur.addCarte(acomplissement);

        joueur.updateScore();

        Assert.assertEquals(4,joueur.getPoints());
    }

    @Test
    public void testRencontreSamuraiUpdate(){
        Joueur joueur = new Joueur();
        Rencontre rencontre = new Rencontre("Samurai","");

        joueur.addCarte(rencontre);
        joueur.updateScore();

        Assert.assertEquals(3,joueur.getPoints());
    }

    @Test
    public void testRencontreMikoUpdate(){
        Joueur joueur = new Joueur();
        Rencontre rencontre = new Rencontre("Miko","");

        joueur.addCarte(rencontre);
        joueur.updateScore();

        Assert.assertEquals(1,joueur.getPoints());
    }

    @Test
    public void testRencontreUmegaeUpdate(){
        Joueur joueur = new Joueur();
        joueur.setNom("Umegae");
        Rencontre rencontre = new Rencontre("","");

        joueur.addCarte(rencontre);
        joueur.updateScore();

        Assert.assertEquals(1,joueur.getPoints());
    }

    @Test
    public void testTrierCarte(){
        Joueur joueur = new Joueur();
        Repas repas = new Repas("R1");
        Souvenirs souvenirs = new Souvenirs("S1");
        Rencontre rencontre = new Rencontre("R1","");
        Sources sources = new Sources("S1",3);
        Acomplissement acomplissement = new Acomplissement("A1");
        Panoramas panoramasRi = new Panoramas("P1",0,"Riziere");
        Panoramas panoramasMe = new Panoramas("P2",0,"Mer");
        Panoramas panoramasMo = new Panoramas("P3",0,"Montagne");

        ArrayList<Cartes> lCarte = new ArrayList<>();
        ArrayList<Cartes> toCompare = new ArrayList<>();

        lCarte.add(repas);
        lCarte.add(souvenirs);
        lCarte.add(rencontre);
        lCarte.add(sources);
        lCarte.add(acomplissement);
        lCarte.add(panoramasRi);
        lCarte.add(panoramasMo);
        lCarte.add(panoramasMe);

        toCompare.addAll(lCarte);
        Collections.shuffle(lCarte);
        joueur.setCartes(lCarte);
        joueur.trierCarte();

        Assert.assertEquals(toCompare,joueur.getCartes());

    }




}