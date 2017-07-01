package Model;


import DBClasses.AvailableItem;
import DBClasses.Order;
import javafx.collections.ObservableList;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Report {


    public static void showReport(Order order){

        try {
            JasperReport jasperReport = JasperCompileManager.compileReport("C:\\Users\\dimaz\\IdeaProjects\\kursova_bd\\src\\View\\Sample.jrxml");

            HashMap <String, Object> parameters = new HashMap<>();

            ObservableList<AvailableItem> items = CatalogueModel.getOrderItems(order.getId());

            float summ = 0;

            for(AvailableItem item : items){

                summ += item.getPrice() * item.getQuantity();

            }

            JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(items);



            parameters.put("Id", order.getId());
            parameters.put("Type", order.getTypeOfOrder());
            parameters.put("Name", order.getName());
            parameters.put("summ", summ);
            parameters.put("Time", order.getTimestamp());


            parameters.put("ItemDataSource", itemsJRBean);

            ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
            list.add(parameters);

            JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);
            JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);

            File pdf = new File("C:\\Users\\dimaz\\Desktop\\output.pdf");

            JasperExportManager.exportReportToPdfStream(print, new FileOutputStream(pdf));

            Desktop desktop = Desktop.getDesktop();
            desktop.open(pdf);


        } catch (JRException e) {

        }
        catch (IOException e){
            e.printStackTrace();
        }



    }


}
