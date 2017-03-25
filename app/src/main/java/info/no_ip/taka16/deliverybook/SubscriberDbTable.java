package info.no_ip.taka16.deliverybook;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;

import java.util.List;


public class SubscriberDbTable {

    private static final String LOG_TAG = SubscriberDbTable.class.getSimpleName();
    private Context context;


    public SubscriberDbTable(Context context){
        this.context = context;
    }

    public void save(Subscriber subscriber){
        DatabaseHelper helper = new DatabaseHelper(context);
        try {
            Dao<Subscriber, Integer> dao = helper.getDao(Subscriber.class);
            dao.createOrUpdate(subscriber);
        } catch (Exception e){
            Log.e(LOG_TAG, "has Exception", e);
        } finally {
            helper.close();
        }
    }

    public int delete(Subscriber subscriber){
        DatabaseHelper helper = new DatabaseHelper(context);
        try {
            Dao<Subscriber, Integer> dao = helper.getDao(Subscriber.class);
            return dao.delete(subscriber);
        } catch (Exception e){
            Log.e(LOG_TAG, "has Exception", e);
        } finally {
            helper.close();
        }
        return 0;
    }

    public List<Subscriber> findAll(){
        DatabaseHelper helper = new DatabaseHelper(context);
        try{
            Dao<Subscriber, Integer> dao = helper.getDao(Subscriber.class);
            return dao.queryForAll();
        } catch (Exception e){
            Log.e(LOG_TAG, "has Exception", e);
            return null;
        } finally {
            helper.close();
        }
    }

    public int deleteAll(){
        DatabaseHelper helper = new DatabaseHelper(context);
        try {
            Dao<Subscriber, Integer> dao = helper.getDao(Subscriber.class);
            return dao.delete(findAll());
        } catch (Exception e){
            Log.e(LOG_TAG, "has Exception", e);
        } finally {
            helper.close();
        }
        return 0;
    }

}
