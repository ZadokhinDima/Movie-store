package View;


import Controll.NewOrderController;
import DBClasses.AvailableItem;
import DBClasses.Order;
import Model.CatalogueModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import static Controll.Main.role;

public class ViewOrder {

    public static void viewOrder(Order order){

        Stage window = new Stage();

        window.setTitle(order.getName() + "  " + order.getTimestamp());


        TableView<AvailableItem> orderItems = new TableView<>();

        TableColumn<AvailableItem, Double> priceOrderColumn = new TableColumn<>("Вартість (грн)");

        TableColumn<AvailableItem, String> nameOrderColumn = new TableColumn<>("Назва");

        TableColumn<AvailableItem, Integer> quantityOrderColumn = new TableColumn<>("Кількість");

        //Adding columns to the table of Order items
        nameOrderColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityOrderColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceOrderColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        orderItems.getColumns().clear();
        orderItems.getColumns().addAll(nameOrderColumn, quantityOrderColumn);

        if(!order.getTypeOfOrder().equals("Списання"))
            orderItems.getColumns().add(priceOrderColumn);

        orderItems.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        priceOrderColumn.setPrefWidth(85);
        quantityOrderColumn.setPrefWidth(85);
        priceOrderColumn.setMaxWidth(85);
        quantityOrderColumn.setMaxWidth(85);
        priceOrderColumn.setMinWidth(85);
        quantityOrderColumn.setMinWidth(85);
        nameOrderColumn.setPrefWidth(250);

        ObservableList<AvailableItem> items = CatalogueModel.getOrderItems(order.getId());

        //Refreshing the list of items in the table
        orderItems.getItems().clear();
        orderItems.setItems(items);



        orderItems.setMouseTransparent(true);

        Scene scene = new Scene(orderItems, 500, 150);

        window.setScene(scene);
        window.setResizable(false);

        window.show();



    }

}
