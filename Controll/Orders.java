package Controll;

import DBClasses.AvailableItem;
import DBClasses.Order;
import Model.OrderModel;
import Model.Report;
import View.ViewOrder;
import com.sun.org.apache.xpath.internal.operations.Or;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Timestamp;

import java.time.LocalDate;
import java.util.ResourceBundle;

public class Orders implements Initializable{
    @FXML
    TableView<Order> ordersTable;

    @FXML
    TableColumn<Order, String> nameColumn;

    @FXML
    TableColumn<Order, String> stateColumn;

    @FXML
    TableColumn<Order, String> dateColumn;

    @FXML
    DatePicker from, to;

    @FXML
    TextField searchField;

    @FXML
    Button view, closeButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Initializing columns of the table
        nameColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("name"));
        //setting the empty table message
        ordersTable.setPlaceholder(new Label("Немає підходящих замовлень."));
        stateColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("state"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("timestamp"));
        ordersTable.getColumns().clear();
        ordersTable.getColumns().addAll(nameColumn, dateColumn, stateColumn);

        //Creating the auto update function
        searchField.textProperty().addListener((v, oldValue, newValue) -> search(newValue));


        from.valueProperty().addListener((v, oldValue, newValue) -> search(searchField.getText()));
        to.valueProperty().addListener((v, oldValue, newValue) -> search(searchField.getText()));

        ordersTable.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) ->  orderSelected());


        //searching for the all items
        search("");


    }


    private void orderSelected(){

        Order selectedOrder = ordersTable.getSelectionModel().getSelectedItem();
        if(selectedOrder != null) {
            view.setDisable(false);


            if(selectedOrder.getState().equals("Відкрито")){
                closeButton.setDisable(false);
            }
            else
                closeButton.setDisable(true);
        }
    }

    private void search(String toSearch){

        ordersTable.getSelectionModel().clearSelection();
        closeButton.setDisable(true);
        view.setDisable(true);


        //Getting the list of suitable items
        ObservableList<Order> orders = OrderModel.getOrders(toSearch, "Продаж", from.getValue(), to.getValue());


        //Refreshing the list of items in the table
        ordersTable.getItems().clear();
        ordersTable.setItems(orders);

    }



    @FXML
    public void printClicked(){

        Order selectedOrder = ordersTable.getSelectionModel().getSelectedItem();
        if(selectedOrder == null)
            Main.showError("Помилка", "Оберіть замовлення.");
        else {

           Report.showReport(selectedOrder);

        }

    }

    @FXML
    public void closeButtonClicked(){

        Order selectedOrder = ordersTable.getSelectionModel().getSelectedItem();
        if(selectedOrder == null)
            Main.showError("Помилка", "Оберіть замовлення.");
        else {

            OrderModel.closeOrder(selectedOrder.getId());
            selectedOrder.setState("Закрито");
            ordersTable.refresh();
            closeButton.setDisable(true);

        }

    }
}
