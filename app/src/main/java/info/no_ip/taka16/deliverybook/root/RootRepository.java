package info.no_ip.taka16.deliverybook.root;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class RootRepository {

    private Context context;

    public RootRepository(Context context){
        this.context = context;
    }

    public Root getRoot(String areaName){

        SharedPreferences sharedPreferences = context.getSharedPreferences(areaName, Context.MODE_PRIVATE);
        Map<String, ?> allEntries = sharedPreferences.getAll();
        Set<String> set = allEntries.keySet();
        for (String s : set) {
            Log.d("debug", s);
        }
        Log.d("debug", "hello");
        return new Root(context, areaName);
    }

    public ArrayList<String> listAreaNames(){
        ArrayList<String> results = new ArrayList<String>();
        SharedPreferences sharedPreferences = this.context.getSharedPreferences("xxx", Context.MODE_PRIVATE);
        Map<String, ?> allEntries = sharedPreferences.getAll();
        Set<String> set = allEntries.keySet();
        for (String s : set) {
            Log.d("debug", s);
        }
        for(Map.Entry<String, ?> entry : allEntries.entrySet()){
            results.add(entry.getKey());
        }
        Log.d("debug", String.valueOf(results.size()));
        return results;
    }
}
