package info.no_ip.taka16.deliverybook.book;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.Collection;

import info.no_ip.taka16.deliverybook.frame.Frame;


@DatabaseTable(tableName=Book.TABLE_NAME)
public class Book {
    public static final String TABLE_NAME = "BookTable";
    public static final String BOOK_ID = "bookId";
    public static final String AREA_NAME_COLUMN = "areaName";
    public static final String ROOTS_COLUMN = "roots";

    @DatabaseField(generatedId = true, columnName = BOOK_ID)
    public int id;
    @DatabaseField(unique = true, columnName=AREA_NAME_COLUMN)
    public String areaName;
    @ForeignCollectionField(columnName=ROOTS_COLUMN)
    public Collection<Frame> roots = new ArrayList<Frame>();

    public int getId(){
        return this.id;
    }

    public String getAreaName(){
        return this.areaName;
    }

    public void rename(String name){
        this.areaName = name;
    }

    public void insert(Frame frame){
        this.roots.add(frame);
    }

    public void insert(int index, Frame frame){
        ((ArrayList<Frame>)this.roots).add(index, frame);
    }

    public Frame getFrame(int index){
        return ((ArrayList<Frame>)this.roots).get(index);
    }

    public void remove(int index){
        ((ArrayList<Frame>)this.roots).remove(index);
    }

    public void remove(Frame frame){
        ((ArrayList<Frame>)this.roots).remove(frame);
    }

    public void move(int fromPos, int toPos){
        ((ArrayList<Frame>)this.roots).add(toPos, ((ArrayList<Frame>)this.roots).remove(fromPos));
    }

    public int size(){
        return this.roots.size();
    }
}
