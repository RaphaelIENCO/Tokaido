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
}