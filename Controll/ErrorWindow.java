package Controll;

import javafx.fxml.FXML;


import javafx.scene.control.Button;
import javafx.stage.Stage;


public class ErrorWindow {


    @FXML
    Button but;


    @FXML
    public void close(){
        Stage stage = (Stage)but.getScene().getWindow();
        stage.close();
    }


}
