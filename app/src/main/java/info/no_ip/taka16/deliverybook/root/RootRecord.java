package info.no_ip.taka16.deliverybook.root;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by root on 17/04/04.
 */

public class RootRecord {
    private static String BASE_KEY = "info.no_ip.taka16.deliverybook.root";
    private String keyString;
    private Context mContext;

    public void saveRootId(List<Integer> array){
        String s = "";
        for (Integer i : array){
            s += i + ",";
        }
        SharedPreferences.Editor edit = mContext.getSharedPreferences(
                keyString, mContext.MODE_PRIVATE).edit();
        edit.putString(keyString, s);
        edit.commit();
    }

    public ArrayList<Integer> loadRoot(){

        SharedPreferences prefs = mContext.getSharedPreferences(keyString, Context.MODE_PRIVATE);
        String s = prefs.getString(keyString, "");
        StringTokenizer st = new StringTokenizer(s, ",");
        ArrayList<Integer> result = new ArrayList<Integer>();
        while (st.hasMoreTokens()) {
            result.add(Integer.parseInt(st.nextToken()));
        }
        return result;
    }
}
