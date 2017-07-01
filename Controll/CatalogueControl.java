package Controll;

import DBClasses.AvailableItem;
import Model.CatalogueModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;


public class CatalogueControl implements Initializable{

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
    ChoiceBox<String> choiceBox;



    public void search(String src){

        //Getting the list of suitable items
        ObservableList<AvailableItem> items = CatalogueModel.getAvailableFilms(src, choiceBox.getValue());


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

        typeColumn.setCellValueFactory(new PropertyValueFactory<AvailableItem, String>("type"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<AvailableItem, Integer>("quantity"));
        CatalogueTable.getColumns().clear();
        CatalogueTable.getColumns().addAll(nameColumn, typeColumn,quantityColumn);

        ObservableList<String> filmTypes = FXCollections.observableArrayList();

        filmTypes.add("Фільм");
        filmTypes.add("Мультфільм");
        filmTypes.add("Серіал");
        filmTypes.add("Мультсеріал");
        filmTypes.add("ТВ шоу");
        filmTypes.add("Музика");
        filmTypes.add("Всі");

        choiceBox.setItems(filmTypes);

        choiceBox.setValue("Всі");

        choiceBox.getSelectionModel().selectedItemProperty().addListener((v, o, n) -> search(searchField.getText()));

        //Creating the auto update function
        searchField.textProperty().addListener((v, oldValue, newValue) -> search(newValue));



        //searching for the all items
        search("");
    }
}
