package Controller;


import Model.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

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
    public void piocheRelais(ActionEvent event){
        System.out.println(event.getSource());
        ArrayList<Repas> piocheRelais= model.getListRepas();
        int a= (int) (Math.random()*piocheRelais.size());
        System.out.println(piocheRelais.get(a).toString());
        model.getListRepas().remove(a);


    }



}
