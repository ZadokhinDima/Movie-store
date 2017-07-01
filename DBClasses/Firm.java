package DBClasses;

/**
 * Created by dimaz on 12.05.2017.
 */
public class Firm {
    private long id, usreou;
    private String name;

    public Firm(long id, long usreou, String name) {
        this.id = id;
        this.usreou = usreou;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUsreou() {
        return usreou;
    }

    public void setUsreou(long usreou) {
        this.usreou = usreou;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
