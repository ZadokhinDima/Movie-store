package Model;


import DBClasses.PricePolicy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OtherModel {

    public static ObservableList<String> getArticleTypes() {
        String query = "SELECT \"Type's name\" FROM \"Type of Article\";";

        ObservableList<String> types = FXCollections.observableArrayList();
        ResultSet rs = ConnectionModel.Function(query);

        try {
            while (rs.next()) {

                types.add(rs.getString(1));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return types;


    }


    public static String processString(String toProcess) {

        return toProcess.replace("'", "''");

    }

    public static PricePolicy getPricePolicy() {


        String query = "SELECT * FROM \"Price policy\" WHERE \"id\" = 1;";

        ResultSet rs = ConnectionModel.Function(query);

        try {
            rs.next();
            return new PricePolicy(rs.getFloat(2), rs.getFloat(3), rs.getFloat(4),
                    rs.getFloat(5), rs.getFloat(6), rs.getFloat(7), rs.getInt(8),
                    rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getBoolean(12));
        } catch (SQLException e) {
            return null;
        }

    }

    public static void setPricePolicy(PricePolicy pricePolicy) {



        String query = "UPDATE \"Price policy\" SET \"baseup\" = " + pricePolicy.getBaseUp() + "," +
                " \"oftenclientoff\" = " + pricePolicy.getOftenClientOff() + ", " +
                " \"usualclientoff\" = " + pricePolicy.getUsualClientOff() + ", " +
                " \"alwaysclientoff\" = " + pricePolicy.getAlwaysClientOff() + ", " +
                " \"seasonoff\" = " + pricePolicy.getSeasonOff() + ", " +
                " \"birthdayoff\" = " + pricePolicy.getBirthDayOff() + ", " +
                " \"oftenclient\" = " + pricePolicy.getOftenClient() + ", " +
                " \"usualclient\" = " + pricePolicy.getUsualClient() + ", " +
                " \"alwaysclient\" = " + pricePolicy.getAlwaysClient() + ", " +
                " \"speed\" = " + pricePolicy.getSpeed() + ", " +
                " \"summing\" = " + pricePolicy.isSumming() +  "  WHERE \"id\" = 1 ;";



        ConnectionModel.Procedure(query);

    }


}
