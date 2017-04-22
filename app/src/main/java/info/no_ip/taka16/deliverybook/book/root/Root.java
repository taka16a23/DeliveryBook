package info.no_ip.taka16.deliverybook.book.root;

import android.content.Context;
import android.content.SharedPreferences;


import java.util.ArrayList;


public class Root {

    private Context context;
    private String areaName;
    private ArrayList<Integer> root;

    public Root(Context context, String areaName, ArrayList<Integer> root){
        this.context = context;
        this.areaName = areaName;
        this.root = root;
        reStoreRoot();
        storeRoot();
    }

    public Root(Context context, String areaName){
        this(context, areaName, new ArrayList<Integer>());
    }

    public String getAreaName(){
        return this.areaName;
    }

    public void setAreaName(String name){
        this.areaName = name;
    }

    private void storeRoot(){
        SharedPreferences.Editor edit = context.getSharedPreferences(areaName, Context.MODE_PRIVATE).edit();
        edit.clear();
        edit.putInt("Count", this.root.size());
        int count = 0;
        for (int i: this.root){
            edit.putInt("IntValue_" + count++, i);
        }
        edit.apply();
    }

    private void reStoreRoot(){
        this.root.clear();
        SharedPreferences prefs = context.getSharedPreferences(areaName, Context.MODE_PRIVATE);
        int count = prefs.getInt("Count", 0);
        for (int i = 0; i < count; i++){
            this.root.add(prefs.getInt("IntValue_"+ i, i));
        }
    }

    public void add(int id){
        this.root.add(id);
        storeRoot();
    }

    public void add(int index, int id){
        this.root.add(index, id);
        storeRoot();
    }

    public void remove(int id){
        this.root.remove(id);
        storeRoot();
    }

    public int size(){
        return this.root.size();
    }

    public int get(int position){
        return this.root.get(position);
    }

    public void move(int fromPos, int toPos){
        if (fromPos == toPos){
            return;
        }
        this.add(toPos, this.root.remove(fromPos));
    };
}
