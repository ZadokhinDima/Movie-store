package Controll;


import DBClasses.Order;
import Model.ConnectionModel;
import Model.OrderModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import static Controll.Main.role;
import static Controll.Main.currentOrder;

import java.io.IOException;
import java.sql.SQLException;

public class WarehouseController {

    @FXML
    BorderPane workPane;

    @FXML
    AnchorPane box;

    @FXML
    Label newPurchaseButton, ordersButton, deleteButton, articlesButton, warehouseButton;

    @FXML
    public void warehouseClicked() throws IOException{

        if(currentOrder != 0)
            OrderModel.deleteOrder(currentOrder);
        OrderModel.clearOrders();

        warehouseButton.setStyle("-fx-background-color: lightgray;\n" +
                "-fx-text-fill: black");
        newPurchaseButton.setStyle("-fx-background-color:  #6D6F6F;");
        ordersButton.setStyle("-fx-background-color:  #6D6F6F;");
        deleteButton.setStyle("-fx-background-color:  #6D6F6F;");
        articlesButton.setStyle("-fx-background-color:  #6D6F6F;");

        box.getChildren().remove(workPane);
        workPane = FXMLLoader.load(getClass().getResource("/View/WarehousePane.fxml"));
        AnchorPane.setLeftAnchor(workPane, 204.0);
        AnchorPane.setRightAnchor(workPane, 0.0);
        AnchorPane.setBottomAnchor(workPane, 0.0);
        AnchorPane.setTopAnchor(workPane, 0.0);
        box.getChildren().add(workPane);

    }

    @FXML
    public void articlesButtonClicked() throws IOException{

        if(currentOrder != 0)
            OrderModel.deleteOrder(currentOrder);
        OrderModel.clearOrders();

        articlesButton.setStyle("-fx-background-color: lightgray;\n" +
                "-fx-text-fill: black");
        newPurchaseButton.setStyle("-fx-background-color:  #6D6F6F;");
        ordersButton.setStyle("-fx-background-color:  #6D6F6F;");
        deleteButton.setStyle("-fx-background-color:  #6D6F6F;");
        warehouseButton.setStyle("-fx-background-color:  #6D6F6F;");

        box.getChildren().remove(workPane);
        workPane = FXMLLoader.load(getClass().getResource("/View/ArticlePane.fxml"));
        AnchorPane.setLeftAnchor(workPane, 204.0);
        AnchorPane.setRightAnchor(workPane, 0.0);
        AnchorPane.setBottomAnchor(workPane, 0.0);
        AnchorPane.setTopAnchor(workPane, 0.0);
        box.getChildren().add(workPane);

    }

    @FXML
    public void ordersButtonClicked() throws IOException{

        if(currentOrder != 0)
            OrderModel.deleteOrder(currentOrder);
        OrderModel.clearOrders();

        ordersButton.setStyle("-fx-background-color: lightgray;\n" +
                "-fx-text-fill: black");
        newPurchaseButton.setStyle("-fx-background-color:  #6D6F6F;");
        warehouseButton.setStyle("-fx-background-color:  #6D6F6F;");
        deleteButton.setStyle("-fx-background-color:  #6D6F6F;");
        articlesButton.setStyle("-fx-background-color:  #6D6F6F;");

        box.getChildren().remove(workPane);
        workPane = FXMLLoader.load(getClass().getResource("/View/OrdersWarehouse.fxml"));
        AnchorPane.setLeftAnchor(workPane, 204.0);
        AnchorPane.setRightAnchor(workPane, 0.0);
        AnchorPane.setBottomAnchor(workPane, 0.0);
        AnchorPane.setTopAnchor(workPane, 0.0);
        box.getChildren().add(workPane);

    }

    @FXML
    public void exit() throws IOException, SQLException {
        Stage window = (Stage) box.getScene().getWindow();

        ConnectionModel.connection.close();

        Parent root = FXMLLoader.load(getClass().getResource("/View/LoginScene.fxml"));
        Scene scene = new Scene(root, 300, 250);

        window.setScene(scene);

        window.setMinWidth(300);
        window.setMinHeight(250);
        window.setWidth(300);
        window.setHeight(250);
        window.setMaxWidth(300);
        window.setMaxHeight(250);


        window.setResizable(false);

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        window.setX((primScreenBounds.getWidth() - window.getWidth()) / 2);
        window.setY((primScreenBounds.getHeight() - window.getHeight()) / 2);
    }

    @FXML
    public void deleteButtonClicked() throws IOException{

        if(currentOrder != 0)
            OrderModel.deleteOrder(currentOrder);
        OrderModel.clearOrders();

        currentOrder = OrderModel.createWriteOff();

        deleteButton.setStyle("-fx-background-color: lightgray;\n" +
                "-fx-text-fill: black");
        newPurchaseButton.setStyle("-fx-background-color:  #6D6F6F;");
        ordersButton.setStyle("-fx-background-color:  #6D6F6F;");
        warehouseButton.setStyle("-fx-background-color:  #6D6F6F;");
        articlesButton.setStyle("-fx-background-color:  #6D6F6F;");

        box.getChildren().remove(workPane);
        workPane = FXMLLoader.load(getClass().getResource("/View/GoodsDialog.fxml"));
        AnchorPane.setLeftAnchor(workPane, 204.0);
        AnchorPane.setRightAnchor(workPane, 0.0);
        AnchorPane.setBottomAnchor(workPane, 0.0);
        AnchorPane.setTopAnchor(workPane, 0.0);
        box.getChildren().add(workPane);

    }

    @FXML
    public void newPurchaseButtonClicked() throws IOException{

        if(currentOrder != 0)
            OrderModel.deleteOrder(currentOrder);
        OrderModel.clearOrders();
        newPurchaseButton.setStyle("-fx-background-color: lightgray;\n" +
                "-fx-text-fill: black");
        warehouseButton.setStyle("-fx-background-color:  #6D6F6F;");
        ordersButton.setStyle("-fx-background-color:  #6D6F6F;");
        deleteButton.setStyle("-fx-background-color:  #6D6F6F;");
        articlesButton.setStyle("-fx-background-color:  #6D6F6F;");

        box.getChildren().remove(workPane);
        workPane = FXMLLoader.load(getClass().getResource("/View/ChoseFirmPane.fxml"));
        AnchorPane.setLeftAnchor(workPane, 204.0);
        AnchorPane.setRightAnchor(workPane, 0.0);
        AnchorPane.setBottomAnchor(workPane, 0.0);
        AnchorPane.setTopAnchor(workPane, 0.0);
        box.getChildren().add(workPane);

    }



}
