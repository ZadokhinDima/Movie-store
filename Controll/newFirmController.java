package Controll;


import DBClasses.Firm;
import Model.ContragentsModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class newFirmController implements Initializable{




    @FXML
    TextField name, number;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        number.textProperty().addListener((v, oldValue, newValue) -> {
            try {
                if(!newValue.equals("")) {
                    if (oldValue == null)
                        oldValue = "";
                    long edpou = Long.parseLong(newValue);
                    if (newValue.length() > 8)
                        number.setText(oldValue);
                }
            } catch (NumberFormatException e) {
                number.setText(oldValue);
            }


        });
    }

    @FXML
    public void insertFirm(){

        try {
            if(name.getText() == null || number.getText() == null){
                Main.showError("Невірно вказані дані", "Всі поля мають бути заповнені.");
                return;
            }
            if(name.getText().length() < 4){
                Main.showError("Невірно вказані дані", "Занадто коротка назва підприємства.");
                return;
            }
            if(name.getText().length() > 30){
                Main.showError("Невірно вказані дані", "Занадто довга назва підприємства.");
                return;
            }
            if(number.getText().length() != 8){
                Main.showError("Невірно вказані дані", "Номер ЄДРПОУ повинен містити 8 цифр.");
                return;
            }
            if(name.getText() == null || number.getText() == null){
                Main.showError("Невірно вказані дані", "Всі поля мають бути заповнені.");
                return;
            }
            if(!ContragentsModel.getFirms(number.getText()).isEmpty()){
                Main.showError("Невірно вказані дані", "Підприємство з таким ЄДРПОУ вже є в базі.");
                return;
            }
            Firm toInsert = new Firm(0, Long.parseLong(number.getText()), name.getText());
            ContragentsModel.insertFirm(toInsert);
            cancel();
        } catch (NumberFormatException e) {
            Main.showError("Невірно вказані дані", "Невірний формат вводу ЄДРПОУ.");
        }


    }

    @FXML
    public void cancel(){

        ((Stage)name.getScene().getWindow()).close();

    }


}
