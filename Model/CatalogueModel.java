package Model;


import DBClasses.AvailableItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

public class CatalogueModel {

    public static ObservableList<AvailableItem> getAvailableFilms(String search, String type) {

        String Psearch = OtherModel.processString(search);


        //initializing list
        ObservableList<AvailableItem> items = FXCollections.observableArrayList();

        //creating the SQL query
        String query = "SELECT * FROM av_items WHERE av_items.\"Article\" LIKE concat('%', '" + Psearch + "', '%')  ";


        HashSet<String> filmTypes = new HashSet<>();
        if (type == null || type.equals("Всі")) {

            filmTypes.add("Фільм");
            filmTypes.add("Мультфільм");
            filmTypes.add("Серіал");
            filmTypes.add("Мультсеріал");
            filmTypes.add("ТВ шоу");
            filmTypes.add("Музика");
        }
        else
            filmTypes.add(type);


        try {

            ResultSet rs = ConnectionModel.Function(query);

            //saving the result of the query to the list
            while (rs.next()) {
                if (!(rs.getInt(3) == 0) && (filmTypes.contains(rs.getString(4))))
                    items.add(new AvailableItem(rs.getLong(1), rs.getString(2), rs.getInt(3), 0, rs.getString(4)));
            }

            return items;


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }

    public static ObservableList<AvailableItem> getAvailableItems(String search, String type) {
        //initializing list
        ObservableList<AvailableItem> items = FXCollections.observableArrayList();

        search = OtherModel.processString(search);

        //creating the SQL query
        String query = "SELECT * FROM av_items WHERE av_items.\"Article\" LIKE concat('%', '" + search + "', '%')  ";


        try {
            ResultSet rs = ConnectionModel.Function(query);

            //saving the result of the query to the list
            while (rs.next()) {
                if (!(rs.getInt(3) == 0) && (type.equals(rs.getString(4)) || type.equals("Всі")))
                    items.add(new AvailableItem(rs.getLong(1), rs.getString(2), rs.getInt(3), 0, rs.getString(4)));
            }

            return items;


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }

    public static ObservableList<AvailableItem> getOrderItems(long id_Order) {
        //Creating query
        String query = "SELECT * FROM get_order_items(" + id_Order + ");";

        ObservableList<AvailableItem> orderItems = FXCollections.observableArrayList();
        try {
            //Executing query
            Statement stmt = ConnectionModel.connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            //saving the result of the query to the list
            while (rs.next()) {
                if (!(rs.getInt(4) == 0))
                    orderItems.add(new AvailableItem(rs.getLong(1), rs.getString(2), rs.getInt(4), rs.getFloat(5), rs.getString(3)));
            }
            return orderItems;


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }

    public static ObservableList<AvailableItem> getArticles(String search, String type) {

        search = OtherModel.processString(search);

        //initializing list
        ObservableList<AvailableItem> items = FXCollections.observableArrayList();
        if (type == null)
            type = "Всі";
        //creating the SQL query
        String query = "SELECT  \"Id_Article\" ,\"Article\", \"Type of Article\".\"Type's name\" FROM \"Catalog\", \"Type of Article\" WHERE \n" +
                "\"Catalog\".\"Id_Type of article\" = \"Type of Article\".\"Id_Type of article\" AND \n" +
                "\"Catalog\".\"Article\" LIKE concat('%', '" + search + "', '%');  ";


        try {
            ResultSet rs = ConnectionModel.Function(query);

            //saving the result of the query to the list
            while (rs.next()) {

                if (type.equals(rs.getString(3)) || type.equals("Всі"))
                    items.add(new AvailableItem(rs.getLong(1),rs.getString(2), rs.getString(3)));
            }

            return items;


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }

    public static void createArticle(String article, String type) {

        article = OtherModel.processString(article);
        String Ptype = OtherModel.processString(type);

        ObservableList<String> types = OtherModel.getArticleTypes();
        int typeId = 0;
        if (!types.contains(type)) {
            //language=GenericSQL
            String query = "INSERT INTO \"Type of Article\" VALUES (NULL , '" + Ptype + "')";

            ConnectionModel.Procedure(query);
        }
        String query = "SELECT get_id_of_type('" + Ptype + "');";
        ResultSet rs = ConnectionModel.Function(query);
        try {
            rs.next();
            typeId = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }



        query = "INSERT INTO \"Catalog\" VALUES (NULL, '" + article + "', " + typeId + ");";

        ConnectionModel.Procedure(query);

    }


}
