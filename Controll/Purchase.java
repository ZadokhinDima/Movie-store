package Controll;


import DBClasses.AvailableItem;
import Model.CatalogueModel;
import Model.OrderModel;
import Model.OtherModel;
import Model.Report;
import View.PricePicker;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static Controll.Main.currentOrder;
import static Controll.Main.role;

public class Purchase implements Initializable{
    @FXML
    Button doneButton, cancelButton, plusButton, minusButton, addButton, deleteButton;

    @FXML
    TextField searchField, quantityField;

    @FXML
    TableView<AvailableItem> availableItems, orderItems;



    @FXML
    TableColumn<AvailableItem, String> nameAvailableColumn;

    @FXML
    TableColumn<AvailableItem, String> typeAvailableColumn;



    @FXML
    TableColumn<AvailableItem, Double> priceOrderColumn;

    @FXML
    TableColumn<AvailableItem, String> nameOrderColumn;

    @FXML
    TableColumn<AvailableItem, Integer> quantityOrderColumn;

    @FXML
    ChoiceBox<String> choiceBox;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        quantityField.setFocusTraversable(true);

        //setting the empty table message
        availableItems.setPlaceholder(new Label("Немає підходящих найменувань."));
        orderItems.setPlaceholder(new Label("Немає товарів у замовленні."));

        //Adding column factories to the table of catalogue
        nameAvailableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeAvailableColumn.setCellValueFactory(new PropertyValueFactory<AvailableItem, String>("type"));
        availableItems.getColumns().clear();
        availableItems.getColumns().addAll(nameAvailableColumn, typeAvailableColumn);

        //Adding columns to the table of Order items
        nameOrderColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityOrderColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceOrderColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        orderItems.getColumns().clear();
        orderItems.getColumns().addAll(nameOrderColumn, quantityOrderColumn, priceOrderColumn);

        //Creating the auto update function
        searchField.textProperty().addListener((v, oldValue, newValue) -> search(newValue));

        ObservableList<String> filmTypes;

        filmTypes = OtherModel.getArticleTypes();



        filmTypes.add("Всі");
        choiceBox.setItems(filmTypes);

        choiceBox.setValue("Всі");

        choiceBox.getSelectionModel().selectedItemProperty().addListener((v, o, n) -> search(searchField.getText()));

        //searching for the all items
        search("");

        update();

        orderItems.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> selectedOrderItem());
        availableItems.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> selectedAvailableItem());

        //doing some job with quantity field
        quantityField.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                {
                    quantityField.setText("");
                }
                else
                {
                    try {
                        if(quantityField.getText().equals(""))
                            quantityField.setText("0");
                        int maxvalue;
                        AvailableItem selectedItem;
                        if(orderItems.getSelectionModel().getSelectedItem() != null){
                            selectedItem = orderItems.getSelectionModel().getSelectedItem();
                            maxvalue = selectedItem.getQuantity();
                        }
                        else {
                            selectedItem = availableItems.getSelectionModel().getSelectedItem();
                            maxvalue = 1000;
                        }
                        int currvalue = Integer.parseInt(quantityField.getText());

                        plusButton.setDisable(false);
                        minusButton.setDisable(false);

                        if(currvalue >= maxvalue) {
                            if(currvalue > maxvalue) {
                                quantityField.setText(maxvalue + "");
                                quantityField.requestFocus();
                            }

                            plusButton.setDisable(true);
                        }

                        if(currvalue <= 1){
                            if(currvalue < 1)
                                quantityField.requestFocus();
                            currvalue = 1;
                            minusButton.setDisable(true);
                        }

                        quantityField.setText(currvalue + "");

                    } catch (NumberFormatException e) {
                        Main.showError("Помилка", "Невірно введене число.");
                        quantityField.setText("1");
                    }
                    catch (NullPointerException e){
                        plusButton.setDisable(true);
                        minusButton.setDisable(true);
                        addButton.setDisable(true);
                        deleteButton.setDisable(true);
                        quantityField.setDisable(true);
                    }

                }
            }
        });

        availableItems.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                {

                    search(searchField.getText());
                    update();
                    orderItems.getSelectionModel().clearSelection();
                    availableItems.requestFocus();
                }
                else
                {



                }
            }
        });


        orderItems.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                {

                    search(searchField.getText());
                    update();
                    availableItems.getSelectionModel().clearSelection();
                    orderItems.requestFocus();
                }
                else
                {


                }
            }
        });

    }

    @FXML
    public void plusClicked(){
        int current = Integer.parseInt(quantityField.getText()) + 1;
        quantityField.setText(current + "");

        //Checking which table is under action
        if(orderItems.getSelectionModel().getSelectedItem() != null){

            AvailableItem selectedItem = orderItems.getSelectionModel().getSelectedItem();
            if (selectedItem.getQuantity() == current) {
                deleteButton.requestFocus();
                plusButton.setDisable(true);
            }

            minusButton.setDisable(false);

        }
        else if (availableItems.getSelectionModel().getSelectedItem() != null){



            plusButton.setDisable(false);
            minusButton.setDisable(false);

        }
    }

    @FXML
    public void minusClicked(){

        int current = Integer.parseInt(quantityField.getText()) - 1;
        quantityField.setText(current + "");


        if (current == 1){
            minusButton.setDisable(true);
            addButton.requestFocus();
            deleteButton.requestFocus();
        }
        plusButton.setDisable(false);

    }

    @FXML
    public void addButtonClicked(){

        if(Integer.parseInt(quantityField.getText()) > 100){
            Main.showError("Помилка", "Занадто велика кількість.");
            return;
        }


        AvailableItem selectedItem = availableItems.getSelectionModel().getSelectedItem();
        
        double price = -1;

        for(AvailableItem item : orderItems.getItems()){
            if(item.getId() == selectedItem.getId())
                price = selectedItem.getPrice();
        }

        if(price == -1){
            price = (double)Math.round(PricePicker.pickPrice()*100) / 100.0;
            if(price == -1)
                return;
            OrderModel.insertItemToOrder(currentOrder, (int)selectedItem.getId(),Integer.parseInt(quantityField.getText()), price);
        }
        else{
            OrderModel.addItemToOrder(currentOrder, (int)selectedItem.getId(),
                    Integer.parseInt(quantityField.getText()));
        }

        search(searchField.getText());
        update();



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

            choiceBox.setItems(types);

            choiceBox.setValue("Всі");

            choiceBox.setItems(types);
            search(searchField.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteButtonClicked(){

        AvailableItem selectedItem = orderItems.getSelectionModel().getSelectedItem();
        OrderModel.deleteItemFromOrder(currentOrder, (int)selectedItem.getId(), Integer.parseInt(quantityField.getText()));

        search(searchField.getText());
        update();


    }

    private void selectedAvailableItem() {
        //Disabling selected orderItem
        orderItems.getSelectionModel().clearSelection();



        //Activating and disabling suitable buttons
        AvailableItem selectedItem = availableItems.getSelectionModel().getSelectedItem();
        minusButton.setDisable(true);

        if(selectedItem != null) {

            plusButton.setDisable(false);

            quantityField.setDisable(false);

            addButton.setDisable(false);
            deleteButton.setDisable(true);

            //Initialising field
            quantityField.setText("1");
        }
        else {
            plusButton.setDisable(true);
            minusButton.setDisable(true);
            addButton.setDisable(true);
            deleteButton.setDisable(true);
            quantityField.setDisable(true);
        }


    }

    private void selectedOrderItem(){
        //Disselect item from availableItems
        availableItems.getSelectionModel().clearSelection();
        //Activating and disabling suitable buttons
        AvailableItem selectedItem = orderItems.getSelectionModel().getSelectedItem();
        minusButton.setDisable(true);
        if(selectedItem != null) {
            if (selectedItem.getQuantity() > 1)
                plusButton.setDisable(false);

            quantityField.setDisable(false);

            addButton.setDisable(true);
            deleteButton.setDisable(false);

            //Initialising field
            quantityField.setText("1");
        }
        else {
            plusButton.setDisable(true);
            minusButton.setDisable(true);
            addButton.setDisable(true);
            deleteButton.setDisable(true);
            quantityField.setDisable(true);
        }


    }

    private void update(){

        //Getting the list of items in the current order
        ObservableList<AvailableItem> items = CatalogueModel.getOrderItems(currentOrder);

        //Refreshing the list of items in the table
        orderItems.getItems().clear();
        orderItems.setItems(items);
    }


    private void search(String src){

        ObservableList<AvailableItem> items;

        //Getting the list of suitable items

        items = CatalogueModel.getArticles(src, choiceBox.getValue());


        //Refreshing the list of items in the table
        availableItems.getItems().clear();
        availableItems.setItems(items);

    }


    @FXML
    public void cancelButtonClicked(){

        OrderModel.deleteOrder(currentOrder);
        currentOrder = 0;
        OrderModel.clearOrders();

        try {
            AnchorPane root = new AnchorPane();
            Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
            Stage window = (Stage)doneButton.getScene().getWindow();

            if(role.equals("warehouse")){
                root = FXMLLoader.load(getClass().getResource("/View/WarehouseScene.fxml"));
            }

            window.setScene(new Scene(root, window.getWidth(), window.getHeight()));


        } catch (IOException e) {
            Main.showError("Помилка", "Помилка завантаження ресурсів");
        }

    }

    @FXML
    public void doneButtonClicked(){



        if(orderItems.getItems().isEmpty())
            Main.showError("Помилка", "Неможливо створити пусту закупку.");
        else{
            Report.showReport(OrderModel.getOrder(currentOrder));
            currentOrder = 0;
            try {
                AnchorPane root;

                Stage window = (Stage)doneButton.getScene().getWindow();


                root = FXMLLoader.load(getClass().getResource("/View/WarehouseScene.fxml"));

                window.setScene(new Scene(root, window.getWidth(), window.getHeight()));

            } catch (IOException e) {
                Main.showError("Помилка", "Помилка завантаження ресурсів");
            }
        }

    }


}

