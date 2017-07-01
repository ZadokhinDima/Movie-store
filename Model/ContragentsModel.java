package Model;

import Controll.Main;
import DBClasses.Firm;
import DBClasses.Individual;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ContragentsModel {

    public static Individual currentIndividual;


    public static ObservableList<String> getIndividuals(String search) {
        search = OtherModel.processString(search);
        //initializing list
        ObservableList<String> items = FXCollections.observableArrayList();

        //creating the SQL query
        String query = "SELECT get_individual_names(\'" + search + "\');";


        try {
            //Executing query
            Statement stmt = ConnectionModel.connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            //saving the result of the query to the list
            while (rs.next()) {

                items.add(rs.getString(1));

            }
            return items;


        } catch (SQLException e) {
            return items;
        }


    }

    //Checking if there is such individual in the database.
    public static boolean isHere(String searchString) {

        searchString = OtherModel.processString(searchString);
        String query = "SELECT get_individual_names(\'" + searchString + "\');";


        try {
            //Executing query
            Statement stmt = ConnectionModel.connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            return rs.next();
        } catch (SQLException e) {
            Main.showError("Помилка", "Помилка на сервері.");
            return false;
        }

    }


    public static int get_ind_id(String search) {

        search = OtherModel.processString(search);

        //Forming query
        String query = "SELECT \"Id\" FROM \"Individual\" WHERE " +
                "concat(\"Last name\", \' \', \"First name\", \' \', \"Middle name\") = \'" + search + "\';";
        try {
            //Executing query
            Statement stmt = ConnectionModel.connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            //getting result
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            //Showing the error message to the user
            Main.showError("Помилка", e.getMessage());
            return 0;
        }

    }

    public static void insertIndividual(Individual toIncert) {
        //Forming query
        String query = "SELECT insert_person(\'" + OtherModel.processString(toIncert.getFirstname()) + "\', \'" + OtherModel.processString(toIncert.getMiddlename()) +
                "\', \'" + OtherModel.processString(toIncert.getLastname()) + "\', " + toIncert.getPhone() + " , \'" + OtherModel.processString(toIncert.getAdress()) + "\', \'" + toIncert.getBirthDate() + "\');";
        try {
            //Executing query
            Statement stmt = ConnectionModel.connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            //Showing the error message to the user
            Main.showError("Помилка", e.getMessage());
        }


    }

    public static void updateIndividual(Individual toUpdate, long id) {
        //Forming query
        String query = "SELECT update_person(\'" + OtherModel.processString(toUpdate.getFirstname()) + "\', \'" +
                OtherModel.processString(toUpdate.getMiddlename()) +
                "\', \'" + OtherModel.processString(toUpdate.getLastname()) + "\', "
                + toUpdate.getPhone() + " , \'" + OtherModel.processString(toUpdate.getAdress()) +
                "\', \'" + toUpdate.getBirthDate() + "\', "+id+");";

        System.out.println(query);
        try {
            //Executing query
            Statement stmt = ConnectionModel.connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            //Showing the error message to the user
            Main.showError("Помилка", e.getMessage());
        }


    }


    public static void insertFirm(Firm toIncert) {
        //Forming query
        String query = "INSERT INTO \"Entity\" VALUES (NULL , '" + OtherModel.processString(toIncert.getName()) + "', " + toIncert.getUsreou() + ")";

        ConnectionModel.Procedure(query);

    }

    public static ObservableList<Firm> getFirms(String search) {

        search = OtherModel.processString(search);

        ObservableList<Firm> firms = FXCollections.observableArrayList();
        String query = "SELECT * FROM get_firms('" + search + "');";
        ResultSet rs = ConnectionModel.Function(query);
        try {
            while (rs.next()) {
                if (rs.getLong(1) != 198)
                    firms.add(new Firm(rs.getLong(1), rs.getLong(3), rs.getString(2)));


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return firms;


    }

    public static ObservableList<Individual> getAllIndividuals(String search) {
        ObservableList<Individual> individuals = FXCollections.observableArrayList();

        search = OtherModel.processString(search);

        String query = "SELECT * FROM get_individuals('" + search + "')";

        ResultSet resultSet = ConnectionModel.Function(query);

        try {
            while (resultSet.next()){

                individuals.add(new Individual(resultSet.getInt(1), resultSet.getString(3),
                        resultSet.getString(2), resultSet.getString(4), resultSet.getString(5),
                        resultSet.getLong(6), resultSet.getDate(7).toLocalDate()));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return individuals;
    }

}
