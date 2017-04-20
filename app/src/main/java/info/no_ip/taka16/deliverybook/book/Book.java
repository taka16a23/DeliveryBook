package info.no_ip.taka16.deliverybook.book;

import android.content.Context;

import info.no_ip.taka16.deliverybook.frame.Frame;
import info.no_ip.taka16.deliverybook.frame.FrameRepository;
import info.no_ip.taka16.deliverybook.book.root.Root;


public class Book {

    private Root root;
    private FrameRepository frameRepository;

    public Book(Context context, Root root){
        this.root = root;
        this.frameRepository = new FrameRepository(context);
    }

    public String getAreaName(){
        return this.root.getAreaName();
    }

    public void rename(String name){
        this.root.setAreaName(name);
    }

    public void insert(Frame frame) {
        this.root.add(frame.getId());
    }

    public void insert(int index, Frame frame){
        this.root.add(index, frame.getId());
    }

    public Frame getFrame(int index){
        int id = this.root.get(index);
        return this.frameRepository.getFrame(id);
    }

    public void remove(int index){
        this.root.remove(index);
    }

    public void remove(Frame frame){
        // TODO:
        int id = frame.getId();

    }

    public void move(int fromPos, int toPos){
        this.root.move(fromPos, toPos);
    }

    public int size(){
        return this.root.size();
    }
}
