package View;


import Controll.Main;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PricePicker {

    private static double price = -1;

    public static double pickPrice(){

        price = -1;

        Stage window = new Stage();

        window.setTitle("Вкажіть ціну.");

        AnchorPane root = new AnchorPane();

        TextField priceField = new TextField();

        Label label = new Label("Ціна продукції:");
        Label grn = new Label("грн");

        Button ok = new Button("Готово");
        Button cancel = new Button("Скасувати");

        AnchorPane.setTopAnchor(label, 19.0);
        AnchorPane.setLeftAnchor(label, 30.0);
        AnchorPane.setBottomAnchor(cancel, 8.0);
        AnchorPane.setRightAnchor(label, 160.0);
        AnchorPane.setTopAnchor(priceField, 16.0);
        AnchorPane.setLeftAnchor(priceField, 150.0);
        AnchorPane.setTopAnchor(grn, 19.0);
        AnchorPane.setRightAnchor(grn, 10.0);
        AnchorPane.setRightAnchor(priceField, 40.0);
        AnchorPane.setBottomAnchor(ok, 8.0);
        AnchorPane.setRightAnchor(ok, 30.0);
        AnchorPane.setLeftAnchor(cancel, 30.0);



        root.getChildren().addAll(priceField, label, ok, cancel, grn);

        ok.setOnAction(e -> {
            try {
                if(priceField.getText().length() != 0){
                    price = Double.parseDouble(priceField.getText());
                    window.close();
                }
                else {
                    Main.showError("Помилка", "Введіть ціну.");

                }
            }
            catch (NumberFormatException ex){
                Main.showError("Помилка", "Неправильно введена ціна.");
            }


        });

        cancel.setOnAction(e -> window.close());


        Scene scene = new Scene(root, 300, 80);

        window.setScene(scene);
        window.setResizable(false);

        window.initModality(Modality.APPLICATION_MODAL);
        window.showAndWait();


        return price;



    }


}
