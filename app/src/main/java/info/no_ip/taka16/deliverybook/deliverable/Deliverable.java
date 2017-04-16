package info.no_ip.taka16.deliverybook.deliverable;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName="DeliverableTable")
public class Deliverable {
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DISPLAY_NAME = "display_name";

    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField(columnName = Deliverable.COLUMN_NAME)
    private String name;
    @DatabaseField(columnName = Deliverable.COLUMN_DISPLAY_NAME)
    private String displayName;

    public Deliverable(){

    }

    public int getId(){
        return this.id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setDisplayName(String name){
        this.displayName = name;
    }

    public String getDisplayName(){
        return this.displayName;
    }

}
