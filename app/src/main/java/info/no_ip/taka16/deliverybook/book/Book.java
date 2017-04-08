package info.no_ip.taka16.deliverybook.book;

import android.util.Log;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import info.no_ip.taka16.deliverybook.frame.Frame;


@DatabaseTable(tableName=Book.TABLE_NAME)
public class Book {
    public static final String TABLE_NAME = "BookTable";
    public static final String BOOK_ID = "bookId";
    public static final String AREA_NAME_COLUMN = "areaName";
//    public static final String ROOTS_COLUMN = "roots";

    @DatabaseField(generatedId = true, columnName = BOOK_ID)
    public int id;
    @DatabaseField(unique = true, columnName=AREA_NAME_COLUMN)
    public String areaName;
    @ForeignCollectionField
    public ForeignCollection<Frame> frames;

    public Book(){
    }

    public Book(String areaName){
        this.areaName = areaName;
    }

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
        this.frames.add(frame);
    }

    public void insert(int index, Frame frame){
        this.frames.add(frame);
    }

    public Frame getFrame(int index){
        return new ArrayList<Frame>(this.frames).get(index);
    }

    public void remove(int index){
        this.remove(this.getFrame(index));
    }

    public void remove(Frame frame){
        this.frames.remove(frame);
    }

    public void move(int fromPos, int toPos){
//        ((ArrayList<Frame>)this.frames).add(toPos, ((ArrayList<Frame>)this.frames).remove(fromPos));
    }

    public int size(){
        return this.frames.size();
    }
}
