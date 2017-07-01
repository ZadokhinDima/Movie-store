package Model;

import Controll.Main;
import DBClasses.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;


public class OrderModel {

    public static int createRent(int id) {
        String query = "SELECT create_rent(" + id + ")";
        try {
            //Executing query
            Statement stmt = ConnectionModel.connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            //Showing the error message to the user
            Main.showError("Помилка", e.getMessage());
            return 0;
        }

    }

    public static int createWriteOff() {
        String query = "SELECT createWriteOff()";
        ResultSet rs = ConnectionModel.Function(query);
        try {
            if (rs.next())
                return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int createPurchase(long id) {
        String query = "SELECT create_purchase(" + id + ")";
        ResultSet rs = ConnectionModel.Function(query);
        try {
            if (rs.next())
                return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public static void insertItemToOrder(int idOrder, int idItem, int quantity, double price) {
        String query = "INSERT INTO  \"Article in order\" VALUES (NULL, " + quantity + ", " + price + ", " + idItem + ", " + idOrder + ");";

        ConnectionModel.Procedure(query);

    }

    public static void addItemToOrder(int idOrder, int idItem, int quantity) {


        String query = "SELECT add_item(" + idOrder + ", " + idItem + ", " + quantity + ");";

        try {
            //Executing query
            Statement stmt = ConnectionModel.connection.createStatement();
            stmt.executeQuery(query);

        } catch (SQLException e) {
            //Showing the error message to the user
            Main.showError("Помилка", e.getMessage());
        }

    }


    public static void deleteItemFromOrder(int idOrder, int idItem, int quantity) {

        String query = "SELECT delete_from_order(" + idOrder + ", " + idItem + ", " + quantity + ");";

        try {
            //Executing query
            Statement stmt = ConnectionModel.connection.createStatement();
            stmt.executeQuery(query);

        } catch (SQLException e) {
            //Showing the error message to the user
            Main.showError("Помилка", e.getMessage());
        }
    }

    public static void clearOrders() {

        String query = "SELECT clear_orders()";

        try {
            //Executing query
            Statement stmt = ConnectionModel.connection.createStatement();
            stmt.executeQuery(query);

        } catch (SQLException e) {
            //Showing the error message to the user
            Main.showError("Помилка", e.getMessage());
        }
    }

    public static void deleteOrder(int idOrder) {

        String query = "SELECT delete_order(" + idOrder + ");";

        ConnectionModel.Function(query);
    }

    public static ObservableList<Order> getOrders(String toSearch, String mode, LocalDate from, LocalDate to) {
        String query = "";
        if (from == null)
            from = LocalDate.of(2000, 1, 1);
        if (to == null)
            to = LocalDate.now();

        to = to.plusDays(1);

        toSearch = OtherModel.processString(toSearch);

        //result list
        ObservableList<Order> orders = FXCollections.observableArrayList();
        query = "SELECT * FROM get_orders('" + toSearch + "','" + from + "','" + to + "')";


        try {
            //Executing query
            ResultSet rs = ConnectionModel.Function(query);


            while (rs.next()) {
                if (mode.equals("Всі") || rs.getString(5).equals(mode)) {

                    orders.add(new Order(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4), rs.getString(5)));
                }
            }

        } catch (SQLException e) {
            //Showing the error message to the user
            Main.showError("Помилка", e.getMessage());

        }


        return orders;
    }

    public static Order getOrder(int id) {

        String query = "SELECT * FROM get_orders('', NULL ,NULL)" +
                "WHERE \"id\" = " + id + ";";

        try {
            //Executing query
            ResultSet rs = ConnectionModel.Function(query);


            rs.next();

            return new Order(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4), rs.getString(5));



        } catch (SQLException e) {
            //Showing the error message to the user
            Main.showError("Помилка", e.getMessage());
            return null;
        }


    }

    public static void closeOrder(int id) {

        String query = "SELECT close_rent(" + id + ")";
        ConnectionModel.Function(query);

    }


}


