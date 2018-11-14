package Model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class RepasTest {
    @Test
    public void testBonPrixSet(){
        Repas repas = new Repas("food");
        repas.setPrix(1);
        Assert.assertEquals(1,repas.getPrix());
    }

    @Test
    public void testBonPrixConstr(){
        Repas repas = new Repas("food",1,0);
        Assert.assertEquals(1,repas.getPrix());
    }

    @Test
    public void testScoreSet(){
        Repas repas = new Repas("food");
        repas.setPoint(3);
        Assert.assertEquals(3,repas.getPoint());
    }

    @Test
    public void testScoreBonConstr(){
        Repas repas = new Repas("food",0,6);
        Assert.assertEquals(6,repas.getPoint());
    }
}