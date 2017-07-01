package Controll;

import DBClasses.AvailableItem;
import Model.CatalogueModel;
import Model.OtherModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Articles implements Initializable {

        @FXML
        TableView<AvailableItem> CatalogueTable;

        @FXML
        TextField searchField;

        @FXML
        TableColumn<AvailableItem, String> nameColumn;


        @FXML
        TableColumn<AvailableItem, String> typeColumn;

        @FXML
        ChoiceBox<String> chooseType;



    public void search(String src){

        //Getting the list of suitable items
        ObservableList<AvailableItem> items = CatalogueModel.getArticles(src, chooseType.getValue());


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
        CatalogueTable.getColumns().clear();
        CatalogueTable.getColumns().addAll(nameColumn, typeColumn);

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



    @FXML
    public void newArticle(){
        try {
            Stage newStage = new Stage();

            AnchorPane pane = FXMLLoader.load(getClass().getResource("/View/NewArticle.fxml"));
            Scene scene = new Scene(pane);
            newStage.setScene(scene);
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.setResizable(false);
            newStage.showAndWait();
            ObservableList<String> types = OtherModel.getArticleTypes();

            types.add("Всі");

            chooseType.setItems(types);

            chooseType.setValue("Всі");

            chooseType.setItems(types);
            search(searchField.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
