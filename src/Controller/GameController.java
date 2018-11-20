package Controller;


import Model.*;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Optional;

public class GameController {
    private Model model;
//    public GameController(Model model){
//        this.model=model;
//    }
    public GameController(){
        model=new Model();
    }

    public void survolEntered(MouseEvent event){
        Button button =(Button) event.getSource();
        if (button.getId().equals("true")){
            button.setStyle("-fx-background-color: green;"+"-fx-border-style: dashed;"+"-fx-border-color: red;");
        } else button.setStyle("-fx-background-color: gray;"+"-fx-border-style: dashed;"+"-fx-border-color: red;");

    }

    public void survolExited(MouseEvent event){
        Button button =(Button) event.getSource();
        if (button.getId().equals("true")){
            button.setStyle("-fx-background-color: green;"+"-fx-border-style: solid;"+"-fx-border-color: black;");
        } else button.setStyle("-fx-background-color: gray;"+"-fx-border-style: solid;"+"-fx-border-color: black;");



    }
    public void actionColor(javafx.event.ActionEvent event){
        Button button=(Button) event.getSource();
        button.setId("true");
        button.setStyle("-fx-background-color: green;");

    }


    /**
     * Partie menu
     */
    public void leaveGame(){
        System.exit(0);
    }
    public void restart(){
    }
    public void showReglement(){
        if( Desktop.isDesktopSupported() )
        {
            new Thread(() -> {
                try {
                    Desktop.getDesktop().browse( new URI( "http://www.funforge.fr/US/files/tokaido/Tokaido_rules_FR_LD.pdf" ) );
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }).start();
        }

    }
    public void piocheRelais(javafx.event.ActionEvent event){
        System.out.println(event.getSource());
        ArrayList<Repas> piocheRelais= model.getListRepas();
        int a= (int) (Math.random()*piocheRelais.size());
        Cartes repas = piocheRelais.get(a);
        Alert show = new Alert(Alert.AlertType.CONFIRMATION);
        ImageView imageView = new ImageView(new Image("/Vue/relais.jpg"));
        show.setGraphic(imageView);
        show.setTitle("Relais");
        show.setHeaderText("Carte repas :");
        show.setContentText(repas.toString());
        ButtonType btnAcheter = new ButtonType("Acheter");
        ButtonType btnRefuser = new ButtonType("Refuser", ButtonBar.ButtonData.CANCEL_CLOSE);
        show.getButtonTypes().setAll(btnAcheter,btnRefuser);
        Optional<ButtonType> choice = show.showAndWait();
        if (choice.get()==btnAcheter){
            model.getListRepas().remove(repas);
        } else {

        }



    }



}
