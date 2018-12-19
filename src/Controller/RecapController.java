package Controller;

import Model.Model;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RecapController {
    private Model model;
     @FXML GridPane grille;

    /**
     * Fonction pour actualiser le model
     */
    public void setData(Model model){
        this.model=model;
    }

    /**
     * Fonction qui affiche toutes les cartes de chaque joueur
     */
    public void afficheCartes(){
        model.getListJoueur().get(0).trierCarte();
        grille.getChildren().clear();
        for (int i=0;i<model.getRecapJoueur().size();i++){
            model.getRecapJoueur().get(i).trierCarte();
            Text descriptif = new Text(model.getRecapJoueur().get(i).getNom()+" \n or : "+model.getRecapJoueur().get(i).getGold()+" \n points : "+model.getRecapJoueur().get(i).getPoints()+" \n or temple: "+model.getRecapJoueur().get(i).getOrTemple());
            grille.add(descriptif,0,i);
            if(model.getRecapJoueur().get(i).getNom().equals(model.getListJoueur().get(0).getNom())){
                grille.add(new ImageView("/Vue/Images/" + model.getRecapJoueur().get(i).getNom() + "1.jpg"), 1, i);
            } else grille.add(new ImageView("/Vue/Images/" + model.getRecapJoueur().get(i).getNom() + ".jpg"), 1, i);

            for (int j=0;j<model.getRecapJoueur().get(i).getCartes().size();j++){
                grille.add(new ImageView("/Vue/Images/"+model.getRecapJoueur().get(i).getCartes().get(j).getNom()+".jpg"),j+2,i);
            }
        }
    }

    /**
     * Ferme la fenetre
     */
    public void fermerStage(){
        Stage stage1 = (Stage) grille.getScene().getWindow();
        stage1.close();
    }
}
