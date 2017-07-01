package Controll;


import Model.ContragentsModel;
import Model.OrderModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static Controll.Main.role;
import static Controll.Main.currentOrder;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewOrderController implements Initializable{


    @FXML
    ListView<String> names;

    @FXML
    TextField searchField;

    @FXML
    BorderPane workpane;


    public void search(String src){

        //Getting the list of suitable items
        ObservableList<String> items = ContragentsModel.getIndividuals(src);


        //Refreshing the list of items in the table
        names.getItems().clear();
        names.setItems(items);


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Deleting focus from the field and table
        searchField.setFocusTraversable(true);
        names.setFocusTraversable(true);
        names.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);


        //Creating the auto update function
        searchField.textProperty().addListener((v, oldValue, newValue) -> search(newValue));

        //searching for all items
        search("");
    }


    @FXML
    public void toSecondPane(){


        if (!names.getSelectionModel().getSelectedItems().isEmpty()) {
            //Chosen
            String chosen = names.getSelectionModel().getSelectedItem();
            int chosenId = ContragentsModel.get_ind_id(chosen);


            //Створюємо документ про оренду.
            currentOrder = OrderModel.createRent(chosenId);



            AnchorPane parent = (AnchorPane)workpane.getParent();

            parent.getChildren().remove(workpane);


            try {
                workpane = FXMLLoader.load(getClass().getResource("/View/GoodsDialog.fxml"));
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
            Main.showError("Помилка", "Будь-ласка, оберіть замовника.");
        }


    }


    @FXML
    public void newCustomer(){
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





}
