package info.no_ip.taka16.deliverybook.root;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.Map;

public class RootRepository {

    private Context context;

    public RootRepository(Context context){
        this.context = context;
    }

    public Root getRoot(String areaName){
        return new Root(context, areaName);
    }

    public ArrayList<String> listAreaNames(){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        ArrayList<String> results = new ArrayList<String>();
        Map<String, ?> allEntries = pref.getAll();
        for(Map.Entry<String, ?> entry : allEntries.entrySet()){
            results.add(entry.getKey());
        }
        return results;
    }
}
