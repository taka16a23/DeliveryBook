package info.no_ip.taka16.deliverybook.subscribers;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/*
   Entity
 */
@DatabaseTable(tableName = "Subscriber")
public class Subscriber {
    public static final String ORDER_NUMBER = "order_number";

    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField
    private String name;
    @DatabaseField
    private String displayName;
    @DatabaseField
    private String address;
    @DatabaseField
    private String displayAddress;
    @DatabaseField
    private Date created;
    @DatabaseField
    private Date lastModified;

    public Subscriber(){

    }

    public Subscriber(String name, String displayName, String address, String displayAddress){
        // TODO: null check
        this.name = name;
        this.displayName = displayName;
        this.address = address;
        this.displayAddress = displayAddress;
        this.created = new Date(System.currentTimeMillis());
    }

    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null){
            throw new NullPointerException("Name not acceptable null.");
        }
        this.name = name;
    }

    public String getDisplayName() {
        if (displayName == null || "".equals(displayName)){
            return name;
        }
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayAddress() {
        return address;
    }

    public Date getCreated(){
        return created;
    }

    public void setLastModified(Date date){
        this.lastModified = date;
    }

    public Date getLastModified(){
        return lastModified;
    }

}
