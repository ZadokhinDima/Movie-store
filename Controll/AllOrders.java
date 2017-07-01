package Controll;


import DBClasses.Order;
import Model.OrderModel;
import Model.Report;
import View.ViewOrder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class AllOrders implements Initializable {
    @FXML
    TableView<Order> ordersTable;

    @FXML
    TableColumn<Order, String> nameColumn;

    @FXML
    TableColumn<Order, String> typeColumn;

    @FXML
    TableColumn<Order, String> dateColumn;

    @FXML
    ChoiceBox<String> chooseType;

    @FXML
    DatePicker from, to;

    @FXML
    TextField searchField;

    @FXML
    Button view;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        //Initializing columns of the table
        nameColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("name"));
        //setting the empty table message
        ordersTable.setPlaceholder(new Label("Немає підходящих замовлень."));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("typeOfOrder"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("timestamp"));
        ordersTable.getColumns().clear();
        ordersTable.getColumns().addAll(nameColumn, dateColumn, typeColumn);

        //Creating the auto update function
        searchField.textProperty().addListener((v, oldValue, newValue) -> search(newValue));


        from.valueProperty().addListener((v, oldValue, newValue) -> search(searchField.getText()));
        to.valueProperty().addListener((v, oldValue, newValue) -> search(searchField.getText()));

        ordersTable.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> orderSelected());


        ObservableList<String> types = FXCollections.observableArrayList();
        types.add("Закупка");
        types.add("Продаж");
        types.add("Списання");
        types.add("Всі");

        chooseType.setItems(types);

        chooseType.setValue("Всі");

        chooseType.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> search(searchField.getText()));


        //searching for the all items
        search("");


    }


    private void orderSelected() {

        Order selectedOrder = ordersTable.getSelectionModel().getSelectedItem();
        if (selectedOrder != null) {
            view.setDisable(false);


        }
    }

    private void search(String toSearch) {

        ordersTable.getSelectionModel().clearSelection();
        view.setDisable(true);


        //Getting the list of suitable items
        ObservableList<Order> orders = OrderModel.getOrders(toSearch, chooseType.getSelectionModel().getSelectedItem(), from.getValue(), to.getValue());


        //Refreshing the list of items in the table
        ordersTable.getItems().clear();
        ordersTable.setItems(orders);

    }


    @FXML
    public void printClicked() {

        Order selectedOrder = ordersTable.getSelectionModel().getSelectedItem();
        if (selectedOrder == null)
            Main.showError("Помилка", "Оберіть замовлення.");
        else {

            Report.showReport(selectedOrder);

        }

    }


}