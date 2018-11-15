package Controller;


import Model.BrowserControl;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Controller {

    public void survolEntered(MouseEvent event){
        Button button =(Button) event.getSource();
        button.setStyle("-fx-border-style: dashed;"+"-fx-border-color: red");
    }

    public void survolExited(MouseEvent event){
        Button button =(Button) event.getSource();
        button.setStyle("-fx-border-style: solid;"+"-fx-border-color: black;");
    }
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


}
