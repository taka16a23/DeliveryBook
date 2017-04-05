package info.no_ip.taka16.deliverybook.root;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import info.no_ip.taka16.deliverybook.db.DatabaseHelper;


public class RootRepository {

    private static final String LOG_TAG = info.no_ip.taka16.deliverybook.root.Root.class.getSimpleName();
    private static Class daoClass = Root.class;

    private Context context;
    private DatabaseHelper helper;

    public RootRepository(Context context){
        this.context = context;
    }

    private boolean isOpenedDatabase(){
        if(this.helper == null){
            return false;
        }
        return this.helper.isOpen();
    }

    private Dao<Root, Integer> getDao() throws SQLException {
        if(!isOpenedDatabase()){
            this.helper = new DatabaseHelper(context);
        }
        return helper.getDao(Root.class);
    }

    private void closeDatabase(){
        if(isOpenedDatabase()){
            helper.close();
        }
    }

    public void register(Root root){
        try {
            getDao().createOrUpdate(root);
        } catch (SQLException e){
            Log.e(LOG_TAG, "has Exception", e);
        } finally {
            closeDatabase();
        }
    }

    public int unregister(Root root){
        try {
            return getDao().delete(root);
        } catch (SQLException e){
            Log.e(LOG_TAG, "has Exception", e);
        } finally {
            closeDatabase();
        }
        return 0;
    }

    public Root getRoot(int id){
        try {
            return getDao().queryForId(id);
        } catch (SQLException e){
            Log.e(LOG_TAG, "has Exception", e);
        } finally {
            closeDatabase();
        }
        return null;
    }

//    public Root getRoot(String areaName){}

    public boolean hasRoot(int id){
        try {
            return getDao().idExists(id);
        } catch (SQLException e){
            Log.e(LOG_TAG, "has Exception", e);
        } finally {
            closeDatabase();
        }
        return false;

    }

    public boolean hasRoot(Root root){
        return hasRoot(root.getId());
    }

    public List<Root> listRoot(int[] ids){
        // FIXME: occur fail if not exists id.
        List<Root> roots = new ArrayList<Root>();
        try {
            Dao<Root, Integer> dao = getDao();
            for (int id : ids) {
                roots.add(dao.queryForId(id));
            }
            return roots;
        } catch (SQLException e){
            Log.e(LOG_TAG, "has Exception", e);
        } finally {
            closeDatabase();
        }
        return roots;
    }

    public List<Root> findAll(){
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
