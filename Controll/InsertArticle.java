package Controll;


import Model.CatalogueModel;
import Model.OtherModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class InsertArticle implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> types = OtherModel.getArticleTypes();
        comboBox.setItems(types);
    }

    @FXML
    TextField newArticle;

    @FXML
    ComboBox<String> comboBox;

    @FXML
    public void insertArticle(){
        if (comboBox.getValue() == null || newArticle.getText() == null){
            Main.showError("Помилка", "Заповніть всі поля.");
            return;
        }
        if (comboBox.getValue().length() < 4){
            Main.showError("Помилка", "Занадто коротке ім'я для категорії.");
            return;
        }
        if (newArticle.getText().length() < 4){
            Main.showError("Помилка", "Занадто коротке ім'я для найменування.");
            return;
        }
        if (comboBox.getValue().length() > 15){
            Main.showError("Помилка", "Занадто довге ім'я для категорії.");
            return;
        }
        if (newArticle.getText().length() > 30){
            Main.showError("Помилка", "Занадто довге ім'я для найменування.");
            return;
        }
        CatalogueModel.createArticle(newArticle.getText(), comboBox.getValue());
        ((Stage)newArticle.getScene().getWindow()).close();
    }

    @FXML
    public void cancel(){

        ((Stage)newArticle.getScene().getWindow()).close();

    }


}
