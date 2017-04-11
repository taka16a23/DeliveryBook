package info.no_ip.taka16.deliverybook.frame;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import info.no_ip.taka16.deliverybook.db.DatabaseHelper;


public class FrameRepository {

    private static final String LOG_TAG = info.no_ip.taka16.deliverybook.frame.Frame.class.getSimpleName();
    private static Class daoClass = Frame.class;

    private Context context;
    private DatabaseHelper helper;

    public FrameRepository(Context context){
        this.context = context;
    }

    private boolean isOpenedDatabase(){
        if(this.helper == null){
            return false;
        }
        return this.helper.isOpen();
    }

    private Dao<Frame, Integer> getDao() throws SQLException {
        if(!isOpenedDatabase()){
            this.helper = new DatabaseHelper(context);
        }
        return helper.getDao(Frame.class);
    }

    private void closeDatabase(){
        if(isOpenedDatabase()){
            helper.close();
        }
    }

    public void register(Frame frame){
        frame.setLastModified(new Date(System.currentTimeMillis()));
        try {
            getDao().createOrUpdate(frame);
        } catch (SQLException e){
            Log.e(LOG_TAG, "has Exception", e);
        } finally {
            closeDatabase();
        }
    }

    public int unregister(Frame frame){
        try {
            return getDao().delete(frame);
        } catch (SQLException e){
            Log.e(LOG_TAG, "has Exception", e);
        } finally {
            closeDatabase();
        }
        return 0;
    }

    public Frame getFrame(int id){
        try {
            return getDao().queryForId(id);
        } catch (SQLException e){
            Log.e(LOG_TAG, "has Exception", e);
        } finally {
            closeDatabase();
        }
        return null;
    }

    public boolean hasFrame(int id){
        try {
            return getDao().idExists(id);
        } catch (SQLException e){
            Log.e(LOG_TAG, "has Exception", e);
        } finally {
            closeDatabase();
        }
        return false;

    }

    public List<Frame> listFrame(int[] ids){
        // FIXME: occur fail if not exists id.
        List<Frame> frames = new ArrayList<Frame>();
        try {
            Dao<Frame, Integer> dao = getDao();
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

    public List<Frame> findAll(){
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
