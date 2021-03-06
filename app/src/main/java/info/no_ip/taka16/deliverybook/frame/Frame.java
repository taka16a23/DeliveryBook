package info.no_ip.taka16.deliverybook.frame;

import android.support.annotation.NonNull;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


import java.util.Date;


@DatabaseTable(tableName="FrameTable")
public class Frame implements Comparable<Frame> {

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
    @DatabaseField
    private String description;

    // tmp
    @DatabaseField
    private String deliverable;

    public Frame(){
        this.created = new Date(System.currentTimeMillis());
    }

    public int getId(){
        return id;
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

    public String getDeliverable(){
        if(this.deliverable == null){
            return "";
        }
        return this.deliverable;
    }

    public void setDeliverable(String deliverable){
        this.deliverable = deliverable;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Frame frame = (Frame) o;

        return id.equals(frame.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Frame{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", displayName='" + displayName + '\'' +
                ", address='" + address + '\'' +
                ", displayAddress='" + displayAddress + '\'' +
                ", created=" + created +
                ", lastModified=" + lastModified +
                ", description='" + description + '\'' +
                ", deliverable='" + deliverable + '\'' +
                '}';
    }

    @Override
    public int compareTo(@NonNull Frame o) {
        if(this == o) return 0;
        return this.id.compareTo(o.getId());
    }
}
