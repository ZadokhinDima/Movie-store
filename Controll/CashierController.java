package Controll;


import Model.ConnectionModel;
import Model.OrderModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import static Controll.Main.role;
import static Controll.Main.currentOrder;
import static java.awt.SystemColor.window;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class CashierController implements Initializable{

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    Label newOrder;
    @FXML
    Label closeOrder;
    @FXML
    Label catalogue;


    @FXML
    BorderPane workPane;

    @FXML
    AnchorPane box;



    @FXML
    public void exit() throws IOException, SQLException{
        Stage window = (Stage) box.getScene().getWindow();

        ConnectionModel.connection.close();

        Parent root = FXMLLoader.load(getClass().getResource("/View/LoginScene.fxml"));
        Scene scene = new Scene(root, 300, 250);


        window.setMinWidth(300);
        window.setMinHeight(250);
        window.setWidth(300);
        window.setHeight(250);
        window.setMaxWidth(300);
        window.setMaxHeight(250);




        window.setScene(scene);



        window.setResizable(false);

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        window.setX((primScreenBounds.getWidth() - window.getWidth()) / 2);
        window.setY((primScreenBounds.getHeight() - window.getHeight()) / 2);
    }


    //Catalogue clicked
    @FXML
    public void catalogueClicked() throws IOException{

        if(currentOrder != 0)
            OrderModel.deleteOrder(currentOrder);

        OrderModel.clearOrders();
        //Changing colors of buttons
        catalogue.setStyle("-fx-background-color: lightgray;\n" +
                "-fx-text-fill: black");

        closeOrder.setStyle("-fx-background-color:  #6D6F6F;");
        newOrder.setStyle("-fx-background-color:  #6D6F6F;");

        //adding the catalogue pane to the window
        box.getChildren().remove(workPane);
        workPane = FXMLLoader.load(getClass().getResource("/View/CataloguePane.fxml"));
        AnchorPane.setLeftAnchor(workPane, 204.0);
        AnchorPane.setRightAnchor(workPane, 0.0);
        AnchorPane.setBottomAnchor(workPane, 0.0);
        AnchorPane.setTopAnchor(workPane, 0.0);
        box.getChildren().add(workPane);

    }

    //New order clicked
    @FXML
    public void newOrderClicked() throws IOException{
        OrderModel.clearOrders();
        //Changing colors of buttons
        newOrder.setStyle("-fx-background-color: lightgray;\n" +
                "-fx-text-fill: black");
        closeOrder.setStyle("-fx-background-color:  #6D6F6F;");
        catalogue.setStyle("-fx-background-color:  #6D6F6F;");


        //going to the choosing of customer
        box.getChildren().remove(workPane);
        workPane = FXMLLoader.load(getClass().getResource("/View/ChoseIndPane.fxml"));
        AnchorPane.setLeftAnchor(workPane, 204.0);
        AnchorPane.setRightAnchor(workPane, 0.0);
        AnchorPane.setBottomAnchor(workPane, 0.0);
        AnchorPane.setTopAnchor(workPane, 0.0);
        box.getChildren().add(workPane);

        if(currentOrder != 0)
            OrderModel.deleteOrder(currentOrder);

    }
    //Close order clicked
    @FXML
    public void closeOrderClicked() throws IOException{

        if(currentOrder != 0)
            OrderModel.deleteOrder(currentOrder);

        OrderModel.clearOrders();
        //Changing colors of buttons
        closeOrder.setStyle("-fx-background-color: lightgray;\n" +
                "-fx-text-fill: black");
        catalogue.setStyle("-fx-background-color:  #6D6F6F;");
        newOrder.setStyle("-fx-background-color:  #6D6F6F;");

        box.getChildren().remove(workPane);
        workPane = FXMLLoader.load(getClass().getResource("/View/OrdersCashier.fxml"));
        AnchorPane.setLeftAnchor(workPane, 204.0);
        AnchorPane.setRightAnchor(workPane, 0.0);
        AnchorPane.setBottomAnchor(workPane, 0.0);
        AnchorPane.setTopAnchor(workPane, 0.0);
        box.getChildren().add(workPane);



    }






}
