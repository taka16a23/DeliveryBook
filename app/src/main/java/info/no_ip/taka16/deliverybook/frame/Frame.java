package info.no_ip.taka16.deliverybook.frame;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

import info.no_ip.taka16.deliverybook.book.Book;


@DatabaseTable(tableName="FrameTable")
public class Frame implements Comparable<Frame>{

    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField(defaultValue="-1")
    private Integer rootNumber;
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
    @DatabaseField(foreign=true, foreignAutoRefresh = true)
    protected Book book;

    public Frame(){
        this.created = new Date(System.currentTimeMillis());
    }

    public void setBook(Book book){
        this.book = book;
    }

    public int getId(){
        return id;
    }

    public int getRootNumber(){
        return this.rootNumber;
    }

    public void setRootNumber(int rootNumber){
        this.rootNumber = rootNumber;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
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

    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return this.address;
    }

    public void setDisplayAddress(String displayAddress){
        this.displayAddress = displayAddress;
    }

    public String getDisplayAddress(){
        if(displayAddress == null || "".equals(displayAddress)){
            return address;
        }
        return this.displayAddress;
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

    public int compareTo(Frame frame){
        if (frame == null) {
            throw new NullPointerException();
        }
        int result = this.getRootNumber() - frame.getRootNumber();
        if (result > 0){
            return 1;
        } else if(result < 0){
            return -1;
        } else {
            return 0;
        }
    }
}
