package info.no_ip.taka16.deliverybook.deliverable;


import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import info.no_ip.taka16.deliverybook.db.DatabaseHelper;


public class DeliverableRepository {
    private static final String LOG_TAG = DeliverableRepository.class.getSimpleName();
    private static final Class daoClass = Deliverable.class;

    private Context context;
    private DatabaseHelper helper;

    public DeliverableRepository(Context context){
        this.context = context;
    }

    private boolean isOpenedDatabase(){
        if(this.helper == null){
            return false;
        }
        return this.helper.isOpen();
    }

    private Dao<Deliverable, Integer> getDao() throws SQLException {
        if(!isOpenedDatabase()){
            this.helper = new DatabaseHelper(context);
        }
        return helper.getDao(Deliverable.class);
    }

    private void closeDatabase(){
        if(isOpenedDatabase()){
            helper.close();
        }
    }

    public void save(Deliverable deliverable){
        try {
            getDao().createOrUpdate(deliverable);
        } catch (SQLException e){
            Log.e(LOG_TAG, "has exception", e);
        } finally {
            closeDatabase();
        }
    }

    public int remove(Deliverable deliverable){
        try {
            return getDao().delete(deliverable);
        } catch (SQLException e){
            Log.e(LOG_TAG, "has exception", e);
        } finally {
            closeDatabase();
        }
        return 0;
    }

    public Deliverable getDeliverable(String name){
        try{
            return getDao().queryBuilder().where().eq("name", name).queryForFirst();
        } catch (SQLException e){
            Log.e(LOG_TAG, "has exception", e);
        } finally {
            closeDatabase();
        }
        return null;
    }

    public Deliverable getDeliverable(int id){
        try {
            return getDao().queryForId(id);
        } catch (SQLException e){
            closeDatabase();
        }
        return null;
    }

    public boolean hasDeliverable(int id){
        try {
            return getDao().idExists(id);
        } catch (SQLException e){
            Log.e(LOG_TAG, "has exception", e);
        } finally {
            closeDatabase();
        }
        return false;
    }

    public boolean hasDeliverable(String name){
        try{
            return 1 <= getDao().queryBuilder().where().eq("name", name).countOf();
        } catch (SQLException e){
            Log.e(LOG_TAG, "has exception", e);
        } finally {
            closeDatabase();
        }
        return false;
    }

    public List<Deliverable> listDeliverable(int[] ids){
        List<Deliverable> frames = new ArrayList<Deliverable>();
        try {
            Dao<Deliverable, Integer> dao = getDao();
            for (int id : ids) {
                frames.add(dao.queryForId(id));
            }
            return frames;
        } catch (SQLException e){
            Log.e(LOG_TAG, "has Exception", e);
        } finally {
            closeDatabase();
        }
        return frames;
    }

    public List<Deliverable> findAll(){
        try{
            return getDao().queryForAll();
        } catch (SQLException e){
            Log.e(LOG_TAG, "has Exception", e);
            return null;
        } finally {
            closeDatabase();
        }
    }

    public int deleteAll(){
        try {
            return getDao().delete(findAll());
        } catch (SQLException e){
            Log.e(LOG_TAG, "has Exception", e);
        } finally {
            closeDatabase();
        }
        return 0;
    }

}
