package info.no_ip.taka16.deliverybook;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "Subscriber")
public class Subscriber {

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

    public Subscriber(){

    }

    public Subscriber(String name, String displayName, String address, String displayAddress){
        // TODO: null check
        this.name = name;
        this.displayName = displayName;
        this.address = address;
        this.displayAddress = displayAddress;
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

    public String getDisplayName(){
        if (displayName == null || "".equals(displayName)){
            return name;
        }
        return displayName;
    }

    public void setDisplayName(String displayName){
        this.displayName = displayName;
    }

    public String getDisplayAddress(){
        return address;
    }
}
