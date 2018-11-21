package Model;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ModelTest {
    @Test
    public void testInitRepasLg(){
        Model model = new Model();
        Assert.assertEquals(25,model.getListRepas().size());
    }

    @Test
    public void testInitSouvenirLg(){
        Model model = new Model();
        Assert.assertEquals(24,model.getListSouvenir().size());
    }

    @Test
    public void testInitPanoramaRizLg(){
        Model model = new Model();
        Assert.assertEquals(15,model.getListPanoramaRiziere().size());
    }

    @Test
    public void testInitPanoramaMonLg(){
        Model model = new Model();
        Assert.assertEquals(20,model.getListPanoramaMontagnes().size());
    }

    @Test
    public void testInitPanoramaMerLg(){
        Model model = new Model();
        Assert.assertEquals(25,model.getListPanoramaMer().size());
    }

    @Test
    public void testInitSourceLg(){
        Model model = new Model();
        Assert.assertEquals(12,model.getListSource().size());
    }

    @Test
    public void testListRepasNonNull(){
        Model model = new Model();
        boolean verif = true;
        for (int i=0; i<model.getListRepas().size() ; i++){
            if (model.getListRepas().get(i) == null) verif=false;
        }
        Assert.assertTrue(verif);
    }

    @Test
    public void testListSouvenirNonNull(){
        Model model = new Model();
        boolean verif = true;
        for (int i=0; i<model.getListSouvenir().size() ; i++){
            if (model.getListSouvenir().get(i) == null) verif=false;
        }
        Assert.assertTrue(verif);
    }

    @Test
    public void testListSourceNonNull(){
        Model model = new Model();
        boolean verif = true;
        for (int i=0; i<model.getListSource().size() ; i++){
            if (model.getListSource().get(i) == null) verif=false;
        }
        Assert.assertTrue(verif);
    }

    @Test
    public void testListPanoRiNonNull(){
        Model model = new Model();
        boolean verif = true;
        for (int i=0; i<model.getListPanoramaRiziere().size() ; i++){
            if (model.getListPanoramaRiziere().get(i) == null) verif=false;
        }
        Assert.assertTrue(verif);
    }

    @Test
    public void testListPanoMoNonNull(){
        Model model = new Model();
        boolean verif = true;
        for (int i=0; i<model.getListPanoramaMontagnes().size() ; i++){
            if (model.getListPanoramaMontagnes().get(i) == null) verif=false;
        }
        Assert.assertTrue(verif);
    }

    @Test
    public void testListPanoMeNonNull(){
        Model model = new Model();
        boolean verif = true;
        for (int i=0; i<model.getListPanoramaMer().size() ; i++){
            if (model.getListPanoramaMer().get(i) == null) verif=false;
        }
        Assert.assertTrue(verif);
    }
}