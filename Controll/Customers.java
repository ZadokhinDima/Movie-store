package Controll;

import DBClasses.Individual;
import Model.ConnectionModel;
import Model.ContragentsModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Customers implements Initializable{

    @FXML
    TableView<Individual> customersTable;

    @FXML
    TableColumn<Individual, String> nameColumn, adressColumn;

    @FXML
    TableColumn<Individual, Long> phoneColumn;

    @FXML
    TableColumn<Individual, LocalDate> birthColumn;

    @FXML
    Button change;

    @FXML
    TextField searchField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        nameColumn.setCellValueFactory(new PropertyValueFactory<Individual, String>("fullname"));
        adressColumn.setCellValueFactory(new PropertyValueFactory<Individual, String>("adress"));
        birthColumn.setCellValueFactory(new PropertyValueFactory<Individual, LocalDate>("birthDate"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Individual, Long>("phone"));

        customersTable.getColumns().clear();
        customersTable.getColumns().addAll(nameColumn, adressColumn, birthColumn, phoneColumn);

        searchField.textProperty().addListener((v, oldValue, newValue) -> search(newValue));

        customersTable.getSelectionModel().selectedItemProperty().addListener((v, old, newVal) -> {

            if(customersTable.getSelectionModel().getSelectedItem() != null) {
                change.setDisable(false);
            }
            else
                change.setDisable(true);

        });


        search("");

    }

    @FXML
    public void newCustomer(){
        ContragentsModel.currentIndividual = null;

        Stage newWindow = new Stage();
        newWindow.setMinWidth(600);
        newWindow.setMinHeight(400);
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/View/newIndividual.fxml"));
            Scene scene = new Scene(root);
            newWindow.setScene(scene);
            newWindow.initModality(Modality.APPLICATION_MODAL);
            newWindow.showAndWait();

            search("");

        } catch (IOException e) {
            Main.showError("Помилка", "Помилка завантаження ресурсів");
        }

    }

    @FXML
    public void changeClicked(){
        Stage newWindow = new Stage();
        newWindow.setMinWidth(600);
        newWindow.setMinHeight(400);
        try {
            ContragentsModel.currentIndividual = customersTable.getSelectionModel().getSelectedItem();
            AnchorPane root = FXMLLoader.load(getClass().getResource("/View/newIndividual.fxml"));
            Scene scene = new Scene(root);
            newWindow.setScene(scene);
            newWindow.initModality(Modality.APPLICATION_MODAL);
            newWindow.showAndWait();

            search("");

        } catch (IOException e) {
            Main.showError("Помилка", "Помилка завантаження ресурсів");
        }

    }

    private void search(String toSearch){

        //Getting the list of suitable items
        ObservableList<Individual> items = ContragentsModel.getAllIndividuals(toSearch);

        //Refreshing the list of items in the table
        customersTable.getItems().clear();
        customersTable.setItems(items);

    }
}
