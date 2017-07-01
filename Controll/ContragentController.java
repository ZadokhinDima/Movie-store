package Controll;


import DBClasses.Individual;
import Model.CatalogueModel;
import Model.ContragentsModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static Model.ContragentsModel.currentIndividual;

public class ContragentController implements Initializable {


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (currentIndividual != null) {
            lastname.setText(currentIndividual.getLastname());
            firstname.setText(currentIndividual.getFirstname());
            middlename.setText(currentIndividual.getMiddlename());
            adress.setText(currentIndividual.getAdress());
            phone.setText(currentIndividual.getPhone() + "");
            birth.setValue(currentIndividual.getBirthDate());
        }
        phone.textProperty().addListener((v, oldValue, newValue) -> {
            try {
                if(!newValue.equals("")) {
                    if (oldValue == null)
                        oldValue = "";
                    long ph = Long.parseLong(newValue);
                    if (newValue.length() > 12)
                        phone.setText(oldValue);
                }
            } catch (NumberFormatException e) {
                phone.setText(oldValue);
            }


        });
    }

    @FXML
    TextField lastname, firstname, middlename, phone, adress;

    @FXML
    DatePicker birth;

    @FXML
    public void cancelButtonAction() {
        currentIndividual = null;
        Stage stage = (Stage) birth.getScene().getWindow();
        stage.close();

    }

    @FXML
    public void insert_individual() {
        try {
            //Checking if all fields are filled.
            if (lastname.getText() == null || firstname.getText() == null || middlename.getText() == null || adress.getText() == null || phone.getText() == null || birth.getValue() == null) {
                Main.showError("Невірно вказані дані", "Всі поля повинні бути заповнені.");
                return;
            }
            if (ContragentsModel.isHere(lastname + " " + firstname + " " + middlename)) {
                Main.showError("Помилка", "Клієнт вже існує.");
                return;
            }
            if (birth.getValue().isAfter(LocalDate.now())) {
                Main.showError("Невірно вказані дані", "Неможлива дата народження.");
                return;
            }
            if (lastname.getText().length() > 15) {
                Main.showError("Невірно вказані данні", "Занадто довге прізвище.");
                return;
            }
            if (firstname.getText().length() > 15) {
                Main.showError("Невірно вказані данні", "Занадто довге ім'я.");
                return;
            }
            if (middlename.getText().length() > 15) {
                Main.showError("Невірно вказані данні", "Занадто довге ім'я по батькові.");
                return;
            }
            if (adress.getText().length() > 40) {
                Main.showError("Невірно вказані данні", "Занадто довга адреса.");
                return;
            }
            if (phone.getText().length() > 12) {
                Main.showError("Невірно вказані данні", "Занадто довгий номер телефону.");
                return;
            }
            if (lastname.getText().length() < 2) {
                Main.showError("Невірно вказані данні", "Занадто коротке прізвище.");
                return;
            }
            if (firstname.getText().length() < 2) {
                Main.showError("Невірно вказані данні", "Занадто коротке ім'я.");
                return;
        }
            if (middlename.getText().length() < 2) {
                Main.showError("Невірно вказані данні", "Занадто коротке ім'я по батькові.");
                return;
            }
            if (adress.getText().length() < 5) {
                Main.showError("Невірно вказані данні", "Занадто коротка адреса.");
                return;
            }
            if (phone.getText().length() < 12) {
                Main.showError("Невірно вказані данні", "Занадто короткий номер телефону.");
                return;
            }

            //Constructing individual.
            Individual ind = new Individual(0, lastname.getText(), firstname.getText(),
                    middlename.getText(), adress.getText(),
                    Long.parseLong(phone.getText()), birth.getValue());
            if (currentIndividual == null) {
                ContragentsModel.insertIndividual(ind);
            } else {
                ContragentsModel.updateIndividual(ind, currentIndividual.getId());
            }

            Stage window = (Stage) firstname.getScene().getWindow();
            window.close();
            currentIndividual = null;


        } catch (NumberFormatException e) {

            Main.showError("Невірно вказані дані", "Невірний формат номеру телефону.");

        }


    }


}
