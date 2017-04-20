package info.no_ip.taka16.deliverybook.root;


import android.content.Context;

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
        for (String filename : listAreaNamePrefs()) {
            if(areaName.equals(FilenameUtils.removeExtension(filename))){
                File file = new File(context.getApplicationInfo().dataDir + "/" + "shared_prefs/" + filename);
                if(file.exists()){
                    file.delete();
                }
            }
        }
    }

    private ArrayList<String> listAreaNamePrefs(){
        ArrayList<String> results = new ArrayList<String>();
        File prefsdir = new File(context.getApplicationInfo().dataDir,"shared_prefs");
        if(prefsdir.exists() && prefsdir.isDirectory()){
            for (String prefFileName : prefsdir.list()) {
                results.add(prefFileName);
            }
        }
        return results;
    }

    public ArrayList<String> listAreaNames(){
        ArrayList<String> results = new ArrayList<String>();
        for (String filename : listAreaNamePrefs()) {
            results.add(FilenameUtils.removeExtension(filename));
        }
        return results;
    }
}
