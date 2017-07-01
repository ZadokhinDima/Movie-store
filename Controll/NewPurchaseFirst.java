package Controll;


import DBClasses.Firm;
import Model.ContragentsModel;
import Model.OrderModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static Controll.Main.currentOrder;

public class NewPurchaseFirst implements Initializable {


    @FXML
    TableView<Firm> firmTable;

    @FXML
    TableColumn<Firm, String> nameColumn;

    @FXML
    TableColumn<Firm, Long> numberColumn;

    @FXML
    TextField searchField;

    @FXML
    BorderPane workpane;


    public void search(String src){

        //Getting the list of suitable items
        ObservableList<Firm> items = ContragentsModel.getFirms(src);


        //Refreshing the list of items in the table
        firmTable.getItems().clear();
        firmTable.setItems(items);


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Deleting focus from the field and table
        searchField.setFocusTraversable(true);
        firmTable.setFocusTraversable(true);
        firmTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        nameColumn.setCellValueFactory(new PropertyValueFactory<Firm, String>("name"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<Firm, Long>("usreou"));

        firmTable.getColumns().clear();
        firmTable.getColumns().addAll(nameColumn, numberColumn);

        //Creating the auto update function
        searchField.textProperty().addListener((v, oldValue, newValue) -> search(newValue));

        //searching for all items
        search("");
    }


    @FXML
    public void toSecondPane(){


        if (!firmTable.getSelectionModel().getSelectedItems().isEmpty()) {
            //Chosen
            Firm chosen = firmTable.getSelectionModel().getSelectedItem();
            long chosenId = chosen.getId();


            //Створюємо документ про закупку.
            currentOrder = OrderModel.createPurchase(chosenId);

            Stage window = (Stage)workpane.getScene().getWindow();


            AnchorPane parent = (AnchorPane)workpane.getParent();

            parent.getChildren().remove(workpane);


            try {
                workpane = FXMLLoader.load(getClass().getResource("/View/PurchaseDialog.fxml"));
            }
            catch (IOException e){
                e.printStackTrace();
                Main.showError("Помилка", "Не вдалось завантажити ресурси.");
            }

            AnchorPane.setLeftAnchor(workpane, 204.0);
            AnchorPane.setRightAnchor(workpane, 0.0);
            AnchorPane.setBottomAnchor(workpane, 0.0);
            AnchorPane.setTopAnchor(workpane, 0.0);


            //calculating index of info label;

            parent.getChildren().add(workpane);
        } else {
            Main.showError("Помилка", "Будь-ласка, оберіть постачальника.");
        }


    }


    @FXML
    public void newFirm(){
        Stage newWindow = new Stage();
        newWindow.setMinWidth(400);
        newWindow.setMinHeight(224);
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/View/newFirm.fxml"));
            Scene scene = new Scene(root);
            newWindow.setScene(scene);
            newWindow.setResizable(false);
            newWindow.initModality(Modality.APPLICATION_MODAL);
            newWindow.showAndWait();



            search("");




        } catch (IOException e) {
            Main.showError("Помилка", "Помилка завантаження ресурсів");
        }


    }





}
