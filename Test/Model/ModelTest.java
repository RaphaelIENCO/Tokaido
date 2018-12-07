package Model;

import org.junit.Assert;
import org.junit.Test;

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
        Assert.assertEquals(3,model.getListPanoramaRiziere().size());
    }
    @Test
    public void testInitPanoramaMonLg(){
        Model model = new Model();
        Assert.assertEquals(4,model.getListPanoramaMontagnes().size());
    }
    @Test
    public void testInitPanoramaMerLg(){
        Model model = new Model();
        Assert.assertEquals(5,model.getListPanoramaMer().size());
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
    @Test
    public void testAddJoueur(){
        boolean verif = false;
        Model model = new Model();
        Joueur j = new Joueur();
        model.addJoueur(j);
        if (model.getListJoueur().get(0) == j && model.getRecapJoueur().get(0) == j){
            verif = true;
        }
        Assert.assertTrue(verif);
    }
    @Test
    public void testTrieJoueurInitiale(){
        Model model = new Model();
        Joueur j1 = new Joueur();
        Joueur j2 = new Joueur();
        j1.setPositions(0);
        j2.setPositions(0);
        model.addJoueur(j1);
        model.addJoueur(j2);
        model.trieJoueur();
        Assert.assertEquals(j1,model.getListJoueur().get(0));
    }
    @Test
    public void testTrieJoueurDiffPos(){
        boolean verif = false;
        Model model = new Model();
        Joueur j1 = new Joueur();
        Joueur j2 = new Joueur();
        j1.setPositions(1);
        j2.setPositions(0);
        model.addJoueur(j1);
        model.addJoueur(j2);
        model.trieJoueur();
        if (model.getListJoueur().get(0) == j2 && model.getListJoueur().get(1) == j1){
            verif = true;
        }
        Assert.assertTrue(verif);
    }
    @Test
    public void testTrieJoueurScoreInit(){
        Model model = new Model();
        Joueur j1 = new Joueur();
        Joueur j2 = new Joueur();
        j1.setPoints(0);
        j2.setPoints(0);
        model.addJoueur(j1);
        model.addJoueur(j2);
        model.trieJoueurScore();
        Assert.assertEquals(j1,model.getListJoueur().get(0));
        Assert.assertEquals(j2,model.getListJoueur().get(1));
    }
    @Test
    public void testTrieJoueurScoreDiff(){
        Model model = new Model();
        Joueur j1 = new Joueur();
        Joueur j2 = new Joueur();
        j1.setPoints(10);
        j2.setPoints(100);
        model.addJoueur(j1);
        model.addJoueur(j2);
        model.trieJoueurScore();
        Assert.assertEquals(j1,model.getListJoueur().get(1));
        Assert.assertEquals(j2,model.getListJoueur().get(0));
    }
    @Test
    public void testMajScore(){
        Model model = new Model();
        Joueur j1 = new Joueur();
        j1.setPoints(10);
        model.addJoueur(j1);
        model.majScore();
        Assert.assertEquals(0,model.getListJoueur().get(0).getPoints());
    }
}