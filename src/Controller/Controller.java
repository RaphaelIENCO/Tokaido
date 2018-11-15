package Controller;

import Vue.Main;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

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


}
