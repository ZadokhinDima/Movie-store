package Model;

import Controll.CashierController;
import Controll.ErrorWindow;
import Controll.Main;
import javafx.stage.Stage;
import org.postgresql.util.PSQLException;

import java.io.IOException;
import java.sql.*;


public class ConnectionModel {

    public static Connection connection = null;

    public static boolean enterDataBase(String username, String password){
        try {
            //Loading  driver
            Class.forName("org.postgresql.Driver");

            //Creating a connection with data base using inputed data
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/movie_rent", username.toLowerCase(), password);

            return true;

        }
        catch (SQLException e){
            return false;
        }
        catch (ClassNotFoundException e) {
            return false;
        }
        catch (Exception e){
            return false;
        }


    }

    public static String getRole(String username){

        username = OtherModel.processString(username);

        try {
            //Forming the SQL query for getting the role of the user
            String query = "SELECT get_role(\'"+ username.toLowerCase() +"\');";


            //Executing query
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            //returning the result of the query
            if(rs.next()) {
                return rs.getString("get_role");
            }
            else
                //or error String which will be catched in Controller
                throw new SQLException();

        }
        catch (SQLException e) {
            return "Invalid user";
        }


    }

    public static ResultSet Function(String query) {
        try {
            //Executing query
            Statement stmt = ConnectionModel.connection.createStatement();
            return stmt.executeQuery(query);

        }
        catch (SQLException e){
            //Showing the error message to the user
            Main.showError("Помилка", e.getMessage());
            return null;
        }
    }

    public static void Procedure(String query) {

        try {
            //Executing query
            Statement stmt = ConnectionModel.connection.createStatement();
            stmt.executeQuery(query);
        }
        catch (SQLException e){
            //Showing the error message to the user

        }

    }
}
