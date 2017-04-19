package info.no_ip.taka16.deliverybook.root;


import android.content.Context;
import android.content.SharedPreferences;

import java.io.File;
import java.util.ArrayList;
import org.apache.commons.io.FilenameUtils;


public class RootRepository {

    private Context context;

    public RootRepository(Context context){
        this.context = context;
    }

    public Root getRoot(String areaName){
        if(areaName == null){
            throw new NullPointerException();
        }
        if(areaName.equals("")){
            throw new IllegalArgumentException();
        }
        return new Root(context, areaName);
    }

    public void removeRoot(String areaName){
        SharedPreferences prefs = context.getSharedPreferences(areaName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
    }

    public ArrayList<String> listAreaNames(){
        ArrayList<String> results = new ArrayList<String>();
        File prefsdir = new File(context.getApplicationInfo().dataDir,"shared_prefs");
        if(prefsdir.exists() && prefsdir.isDirectory()){
            for (String prefFileName : prefsdir.list()) {
                results.add(FilenameUtils.removeExtension(prefFileName));
            }
        }
        return results;
    }
}
