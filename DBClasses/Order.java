package DBClasses;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Order {

    private int id;
    private String name;
    private String state;
    private Timestamp time;
    private String timestamp;
    private String typeOfOrder;

    public Order(int id, String name, String state, Timestamp timestamp, String typeOfOrder) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.time = timestamp;
        this.timestamp =  new SimpleDateFormat("yyyy-MM-dd   HH:mm:ss").format(timestamp);
        this.typeOfOrder = typeOfOrder;
    }


    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTypeOfOrder() {
        return typeOfOrder;
    }

    public void setTypeOfOrder(String typeOfOrder) {
        this.typeOfOrder = typeOfOrder;
    }
}
