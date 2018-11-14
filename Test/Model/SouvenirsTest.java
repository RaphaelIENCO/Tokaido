package Model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SouvenirsTest {
    @Test
    public void testBonPrixSet(){
        Souvenirs souvenir = new Souvenirs("souv");
        souvenir.setPrix(1);
        Assert.assertEquals(1,souvenir.getPrix());
    }

    @Test
    public void testBonPrixConstr(){
        Souvenirs souvenir = new Souvenirs("souv",1,0);
        Assert.assertEquals(1,souvenir.getPrix());
    }

    @Test
    public void testSouvenirSet(){
        Souvenirs souvenir = new Souvenirs("souv");
        souvenir.setTypeSouvenir(3);
        Assert.assertEquals(3,souvenir.getTypeSouvenir());
    }

    @Test
    public void testSouvenirBonConstr(){
        Souvenirs souvenir = new Souvenirs("souv",0,6);
        Assert.assertEquals(6,souvenir.getTypeSouvenir());
    }

    @Test
    public void testSouvenirEntreMarge(){
        boolean verif = false;
        Souvenirs souvenirs = new Souvenirs("souv",0,-1);
        if (souvenirs.getTypeSouvenir() >0 && souvenirs.getTypeSouvenir() <= 4){
            verif=true;
        }
        Assert.assertFalse(verif);
    }
}