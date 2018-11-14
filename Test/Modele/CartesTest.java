package Modele;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CartesTest {
    @Test
    public void testNomBonSet(){
        Cartes cartes = new Cartes();
        cartes.setNom("c1");
        Assert.assertEquals("c1",cartes.getNom());
    }

    @Test
    public void testNomBonConstr(){
        Cartes cartes = new Cartes("c1");
        Assert.assertEquals("c1",cartes.getNom());
    }
}