package Controll;

import Model.ConnectionModel;
import Model.OrderModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static Controll.Main.currentOrder;

/**
 * Created by dimaz on 14.05.2017.
 */
public class AdminController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    Label clientsBase;
    @FXML
    Label firmBase;
    @FXML
    Label pricePolicy;


    @FXML
    BorderPane workPane;

    @FXML
    AnchorPane box;

    @FXML
    Label documents;



    //Catalogue clicked
    @FXML
    public void clientsBaseClicked() throws IOException {

        if(currentOrder != 0)
            OrderModel.deleteOrder(currentOrder);

        OrderModel.clearOrders();
        //Changing colors of buttons
        clientsBase.setStyle("-fx-background-color: lightgray;\n" +
                "-fx-text-fill: black");

        firmBase.setStyle("-fx-background-color:  #6D6F6F;");
        pricePolicy.setStyle("-fx-background-color:  #6D6F6F;");
        documents.setStyle("-fx-background-color:  #6D6F6F;");

        //adding the catalogue pane to the window
        box.getChildren().remove(workPane);
        workPane = FXMLLoader.load(getClass().getResource("/View/Customers.fxml"));
        AnchorPane.setLeftAnchor(workPane, 204.0);
        AnchorPane.setRightAnchor(workPane, 0.0);
        AnchorPane.setBottomAnchor(workPane, 0.0);
        AnchorPane.setTopAnchor(workPane, 0.0);
        box.getChildren().add(workPane);

    }

    //New order clicked
    @FXML
    public void firmBaseClicked() throws IOException{
        OrderModel.clearOrders();
        //Changing colors of buttons
        firmBase.setStyle("-fx-background-color: lightgray;\n" +
                "-fx-text-fill: black");
        clientsBase.setStyle("-fx-background-color:  #6D6F6F;");
        pricePolicy.setStyle("-fx-background-color:  #6D6F6F;");
        documents.setStyle("-fx-background-color:  #6D6F6F;");


        //going to the choosing of customer
        box.getChildren().remove(workPane);
        workPane = FXMLLoader.load(getClass().getResource("/View/FirmBase.fxml"));
        AnchorPane.setLeftAnchor(workPane, 204.0);
        AnchorPane.setRightAnchor(workPane, 0.0);
        AnchorPane.setBottomAnchor(workPane, 0.0);
        AnchorPane.setTopAnchor(workPane, 0.0);
        box.getChildren().add(workPane);

        if(currentOrder != 0)
            OrderModel.deleteOrder(currentOrder);

    }


    @FXML
    public void exit() throws IOException, SQLException {
        Stage window = (Stage) box.getScene().getWindow();

        ConnectionModel.connection.close();

        Parent root = FXMLLoader.load(getClass().getResource("/View/LoginScene.fxml"));
        Scene scene = new Scene(root, 300, 250);


        window.setScene(scene);

        window.setResizable(false);

        window.setMinWidth(300);
        window.setMinHeight(250);
        window.setWidth(300);
        window.setHeight(250);
        window.setMaxWidth(300);
        window.setMaxHeight(250);

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        window.setX((primScreenBounds.getWidth() - window.getWidth()) / 2);
        window.setY((primScreenBounds.getHeight() - window.getHeight()) / 2);
    }


    //Close order clicked
    @FXML
    public void pricePolicyClicked() throws IOException{

        if(currentOrder != 0)
            OrderModel.deleteOrder(currentOrder);

        OrderModel.clearOrders();
        //Changing colors of buttons
        pricePolicy.setStyle("-fx-background-color: lightgray;\n" +
                "-fx-text-fill: black");
        clientsBase.setStyle("-fx-background-color:  #6D6F6F;");
        firmBase.setStyle("-fx-background-color:  #6D6F6F;");
        documents.setStyle("-fx-background-color:  #6D6F6F;");


        box.getChildren().remove(workPane);
        workPane = FXMLLoader.load(getClass().getResource("/View/PricePane.fxml"));
        AnchorPane.setLeftAnchor(workPane, 204.0);
        AnchorPane.setRightAnchor(workPane, 0.0);
        AnchorPane.setBottomAnchor(workPane, 0.0);
        AnchorPane.setTopAnchor(workPane, 0.0);
        box.getChildren().add(workPane);

    }

    @FXML
    public void documentsClicked() throws IOException{

        //Changing colors of buttons
        pricePolicy.setStyle("-fx-background-color:  #6D6F6F;");
        clientsBase.setStyle("-fx-background-color:  #6D6F6F;");
        firmBase.setStyle("-fx-background-color:  #6D6F6F;");
        documents.setStyle("-fx-background-color: lightgray;\n" +
                "-fx-text-fill: black");


        box.getChildren().remove(workPane);
        workPane = FXMLLoader.load(getClass().getResource("/View/OrdersWarehouse.fxml"));
        AnchorPane.setLeftAnchor(workPane, 204.0);
        AnchorPane.setRightAnchor(workPane, 0.0);
        AnchorPane.setBottomAnchor(workPane, 0.0);
        AnchorPane.setTopAnchor(workPane, 0.0);
        box.getChildren().add(workPane);


    }






}