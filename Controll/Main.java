package Controll;

import DBClasses.Order;
import Model.OrderModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Main extends Application {

    public static  String role;
    public static int currentOrder = 0;

    Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setOnCloseRequest(e ->  {
            if(currentOrder != 0)
                OrderModel.deleteOrder(currentOrder);

        } );
        Parent root = FXMLLoader.load(getClass().getResource("/View/LoginScene.fxml"));
        Scene scene = new Scene(root, 300, 250);
        window.setScene(scene);
        window.setResizable(false);
        window.show();
    }

    public static void showError(String title, String err){

        new Main().innerError(title, err);



    }

    public void innerError(String title, String err){
        Stage win = new Stage();


        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/View/ErrorWindow.fxml"));


            Label message = new Label(err);
            message.setAlignment(Pos.CENTER);
            root.getChildren().add(message);
            AnchorPane.setLeftAnchor(message, 89.0);
            AnchorPane.setRightAnchor(message, 0.0);
            AnchorPane.setTopAnchor(message, 0.0);
            AnchorPane.setBottomAnchor(message, 40.0);

            Scene scene = new Scene(root, 129 + err.length() * 7, 129);
            scene.getStylesheets().add("/View/AllStyles.css");


            win.setScene(scene);


        } catch (IOException e) {
            e.printStackTrace();
        }


        win.initModality(Modality.APPLICATION_MODAL);
        win.setTitle(title);
        win.showAndWait();

    }







}
