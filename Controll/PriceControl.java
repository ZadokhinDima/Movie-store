package Controll;


import DBClasses.PricePolicy;
import Model.OtherModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class PriceControl implements Initializable {

    @FXML
    TextField baseUp, oftenClientOff, usualClientOff, alwaysClientOff, seasonOff, birthDayOff,
            oftenClient, usualClient, alwaysClient, speed;

    @FXML
    CheckBox sumOffers;

    @FXML
    public void submit() {


        try {
            if (baseUp.getText().length() == 0 || oftenClientOff.getText().length() == 0 || oftenClient.getText().length() == 0 ||
                    usualClient.getText().length() == 0 || usualClientOff.getText().length() == 0 ||
                    alwaysClient.getText().length() == 0 || alwaysClientOff.getText().length() == 0 ||
                    seasonOff.getText().length() == 0 || birthDayOff.getText().length() == 0 || speed.getText().length() == 0) {
                Main.showError("Помилка", "Всі поля мають бути заповнені.");
                return;
            }

            if (Float.parseFloat(baseUp.getText()) <= 13) {
                Main.showError("Помилка", "Надто мала націнка.");
                return;
            }

            if (Float.parseFloat(oftenClientOff.getText()) < 0) {
                Main.showError("Помилка", "Знижка повинна бути додатньою.");
                return;
            }
            if (Float.parseFloat(usualClientOff.getText()) < 0) {
                Main.showError("Помилка", "Знижка повинна бути додатньою.");
                return;
            }
            if (Float.parseFloat(alwaysClientOff.getText()) < 0) {
                Main.showError("Помилка", "Знижка повинна бути додатньою.");
                return;
            }
            if (Float.parseFloat(seasonOff.getText()) < 0) {
                Main.showError("Помилка", "Знижка повинна бути додатньою.");
                return;
            }
            if (Float.parseFloat(birthDayOff.getText()) < 0) {
                Main.showError("Помилка", "Знижка повинна бути додатньою.");
                return;
            }
            if (Float.parseFloat(oftenClientOff.getText()) > 50) {
                Main.showError("Помилка", "Завелика знижка.");
                return;
            }
            if (Float.parseFloat(usualClientOff.getText()) > 50) {
                Main.showError("Помилка", "Завелика знижка.");
                return;
            }
            if (Float.parseFloat(alwaysClientOff.getText()) > 50) {
                Main.showError("Помилка", "Завелика знижка.");
                return;
            }
            if (Float.parseFloat(seasonOff.getText()) > 50) {
                Main.showError("Помилка", "Завелика знижка.");
                return;
            }
            if (Float.parseFloat(birthDayOff.getText()) > 50) {
                Main.showError("Помилка", "Завелика знижка.");
                return;
            }

            if (Integer.parseInt(oftenClient.getText()) < 1) {
                Main.showError("Помилка", "Кількість замовлень повинна бути додатньою (не нульовою).");
                return;
            }
            if (Integer.parseInt(usualClient.getText()) < 1) {
                Main.showError("Помилка", "Кількість замовлень повинна бути додатньою (не нульовою).");
                return;
            }
            if (Integer.parseInt(alwaysClient.getText()) < 1) {
                Main.showError("Помилка", "Кількість замовлень повинна бути додатньою (не нульовою).");
                return;
            }
            if (Integer.parseInt(speed.getText()) < 0) {
                Main.showError("Помилка", "Кількість діб повинна бути додатньою (не нульовою).");
                return;
            }
            if (Integer.parseInt(oftenClient.getText()) > 20) {
                Main.showError("Помилка", "Завелика кількість замовлень для частого клієнта.");
                return;
            }
            if (Integer.parseInt(usualClient.getText()) > 40) {
                Main.showError("Помилка", "Завелика кількість замовлень для постійного клієнта.");
                return;
            }
            if (Integer.parseInt(alwaysClient.getText()) > 80) {
                Main.showError("Помилка", "Завелика кількість замовлень для \"Друга магазину\".");
                return;
            }

            if(!((Integer.parseInt(oftenClient.getText()) < Integer.parseInt(usualClient.getText()))
                    && Integer.parseInt(usualClient.getText()) < Integer.parseInt(alwaysClient.getText()))){

                Main.showError("Помилка", "Кількості замовлень для клієнтів розташовані не в зростаючому порядку.");
                return;

            }
            if(!((Float.parseFloat(oftenClientOff.getText()) < Float.parseFloat(usualClientOff.getText()))
                    && Float.parseFloat(usualClientOff.getText()) < Float.parseFloat(alwaysClientOff.getText()))){

                Main.showError("Помилка", "Скидки для клієнтів розташовані не в зростаючому порядку.");
                return;

            }

            PricePolicy pricePolicy = new PricePolicy(Float.parseFloat(baseUp.getText()), Float.parseFloat(oftenClientOff.getText()),
                    Float.parseFloat(usualClientOff.getText()), Float.parseFloat(alwaysClientOff.getText()),
                    Float.parseFloat(seasonOff.getText()), Float.parseFloat(birthDayOff.getText()),
                    Integer.parseInt(oftenClient.getText()), Integer.parseInt(usualClient.getText()),
                    Integer.parseInt(alwaysClient.getText()), Integer.parseInt(speed.getText()),
                    sumOffers.isSelected()
                    );


            OtherModel.setPricePolicy(pricePolicy);

            init();


        } catch (NullPointerException e) {
            Main.showError("Помилка", "Всі поля мають бути заповнені.");
        } catch (NumberFormatException e) {
            Main.showError("Помилка", "Перевірте правильність введених чисел.");
        }


    }

    @FXML
    public void init() {

        PricePolicy pricePolicy = OtherModel.getPricePolicy();

        baseUp.setText(pricePolicy.getBaseUp() + "");
        oftenClient.setText(pricePolicy.getOftenClient() + "");
        oftenClientOff.setText(pricePolicy.getOftenClientOff() + "");
        usualClient.setText(pricePolicy.getUsualClient() + "");
        usualClientOff.setText(pricePolicy.getUsualClientOff() + "");
        alwaysClient.setText(pricePolicy.getAlwaysClient() + "");
        alwaysClientOff.setText(pricePolicy.getAlwaysClientOff() + "");
        seasonOff.setText(pricePolicy.getSeasonOff() + "");
        birthDayOff.setText(pricePolicy.getBirthDayOff() + "");
        speed.setText(pricePolicy.getSpeed() + "");

        sumOffers.setSelected(pricePolicy.isSumming());

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        PricePolicy pricePolicy = OtherModel.getPricePolicy();

        baseUp.setText(pricePolicy.getBaseUp() + "");
        oftenClient.setText(pricePolicy.getOftenClient() + "");
        oftenClientOff.setText(pricePolicy.getOftenClientOff() + "");
        usualClient.setText(pricePolicy.getUsualClient() + "");
        usualClientOff.setText(pricePolicy.getUsualClientOff() + "");
        alwaysClient.setText(pricePolicy.getAlwaysClient() + "");
        alwaysClientOff.setText(pricePolicy.getAlwaysClientOff() + "");
        seasonOff.setText(pricePolicy.getSeasonOff() + "");
        birthDayOff.setText(pricePolicy.getBirthDayOff() + "");
        speed.setText(pricePolicy.getSpeed() + "");

        sumOffers.setSelected(pricePolicy.isSumming());

    }
}
