package Controll;


import Model.ConnectionModel;
import Model.OrderModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static Controll.Main.role;
import static Controll.Main.currentOrder;


public class LoginControl implements Initializable {



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private TextField name;
    @FXML
    private PasswordField pass;

    @FXML
    public void loginClicked() {

        //trying to enter the database
        if(!ConnectionModel.enterDataBase(name.getText(), pass.getText())) {
            //if not entered
            Main.showError("Помилка", "Невірні дані для входу");
            name.clear();
            pass.clear();
        }
        else{
            //if connected
            //getting the role
            role = ConnectionModel.getRole(name.getText());

            //opening the appropriate window
            try {



                if (role.equals("cashier")) {

                    Stage window = (Stage)name.getScene().getWindow();



                    window.setX(50);



                    window.setResizable(true);
                    AnchorPane root = FXMLLoader.load(getClass().getResource("/View/CashierScene2.fxml"));
                    ((Stage)name.getScene().getWindow()).setScene(new Scene(root, 1110
                            ,600));


                    window.setMinHeight(600);
                    window.setMinWidth(1110);
                    window.setMaxWidth(Double.MAX_VALUE);
                    window.setMaxHeight(Double.MAX_VALUE);



                }
                if(role.equals("warehouse")){



                    Stage window = (Stage)name.getScene().getWindow();

                    window.setX(50);



                    window.setResizable(true);
                    AnchorPane root = FXMLLoader.load(getClass().getResource("/View/WarehouseScene.fxml"));
                    ((Stage)name.getScene().getWindow()).setScene(new Scene(root,1110
                            ,600));


                    window.setMinHeight(600);
                    window.setMinWidth(1110);
                    window.setMaxWidth(Double.MAX_VALUE);
                    window.setMaxHeight(Double.MAX_VALUE);


                }
                if(role.equals("administrator")){

                    Stage window = (Stage)name.getScene().getWindow();
                    window.setResizable(true);

                    window.setX(50);



                    AnchorPane root = FXMLLoader.load(getClass().getResource("/View/AdminScene.fxml"));
                    ((Stage)name.getScene().getWindow()).setScene(new Scene(root, 1110
                            ,600));



                    window.setMaxWidth(Double.MAX_VALUE);
                    window.setMaxHeight(Double.MAX_VALUE);
                    window.setMinHeight(600);
                    window.setMinWidth(1110);



                }
            }
            catch (IOException e){
                Main.showError("Помилка", "Не вдалось завантажити файли ресурсів.");
            }
        }





    }
}

