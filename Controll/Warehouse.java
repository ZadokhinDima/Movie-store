package Controll;


import DBClasses.AvailableItem;
import Model.CatalogueModel;
import Model.OtherModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class Warehouse implements Initializable {

    @FXML
    TableView<AvailableItem> CatalogueTable;

    @FXML
    TextField searchField;

    @FXML
    TableColumn<AvailableItem, String> nameColumn;

    @FXML
    TableColumn<AvailableItem, Integer> quantityColumn;

    @FXML
    TableColumn<AvailableItem, String> typeColumn;

    @FXML
    ChoiceBox<String> chooseType;



    public void search(String src){

        //Getting the list of suitable items
        ObservableList<AvailableItem> items = CatalogueModel.getAvailableItems(src, chooseType.getValue());


        //Refreshing the list of items in the table
        CatalogueTable.getItems().clear();
        CatalogueTable.setItems(items);





    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Deleting focus from the field and table
        searchField.setFocusTraversable(false);
        CatalogueTable.setFocusTraversable(false);

        //Initializing columns of the table
        nameColumn.setCellValueFactory(new PropertyValueFactory<AvailableItem, String>("name"));
        //setting the empty table message
        CatalogueTable.setPlaceholder(new Label("Немає підходящих найменувань."));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<AvailableItem, Integer>("quantity"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<AvailableItem, String>("type"));
        CatalogueTable.getColumns().clear();
        CatalogueTable.getColumns().addAll(nameColumn, quantityColumn, typeColumn);

        //Creating the auto update function
        searchField.textProperty().addListener((v, oldValue, newValue) -> search(newValue));

        ObservableList<String> types = OtherModel.getArticleTypes();

        types.add("Всі");

        chooseType.setItems(types);

        chooseType.setValue("Всі");

        chooseType.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> search(searchField.getText()));


        //searching for the all items
        search("");
    }
}