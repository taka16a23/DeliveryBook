package info.no_ip.taka16.deliverybook.book;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.Collection;

import info.no_ip.taka16.deliverybook.frame.Frame;


@DatabaseTable(tableName="BookTable")
public class Book {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(unique = true)
    private String name;
    @ForeignCollectionField
    private Collection<Frame> roots = new ArrayList<Frame>();

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public void rename(String name){
        this.name = name;
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
