package Controller;

import com.sun.javaws.jnl.JavaFXAppDesc;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;


public class OptionController {

    public void leaveGame(){
        System.exit(0);
    }

    public void changeTailleEcranOption(Event event){
        System.out.println("Je change de fenetre");
    }

    public void selection(ActionEvent a){
        Button b;
        b = (Button)a.getSource();
        b.setStyle(
                "-fx-background-radius: 5em; " +
                        "-fx-min-width: 30px; " +
                        "-fx-min-height: 30px; " +
                        "-fx-max-width: 30px; " +
                        "-fx-max-height: 30px;" +
                        "-fx-background-color: #be1d00"
        );
    }
}
