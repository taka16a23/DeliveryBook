package info.no_ip.taka16.deliverybook.book;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import info.no_ip.taka16.deliverybook.db.DatabaseHelper;


public class BookRepository {

    private static final String LOG_TAG = info.no_ip.taka16.deliverybook.book.Book.class.getSimpleName();
    private static Class daoClass = Book.class;

    private Context context;
    private DatabaseHelper helper;

    public BookRepository(Context context){
        this.context = context;
    }

    private boolean isOpenedDatabase(){
        if(this.helper == null){
            return false;
        }
        return this.helper.isOpen();
    }

    private Dao<Book, Integer> getDao() throws SQLException {
        if(!isOpenedDatabase()){
            this.helper = new DatabaseHelper(context);
        }
        return helper.getDao(Book.class);
    }

    private void closeDatabase(){
//        if(isOpenedDatabase()){
//            helper.close();
//        }
    }

    public void register(Book book){
        try {
            getDao().createOrUpdate(book);
        } catch (SQLException e){
            Log.e(LOG_TAG, "has Exception", e);
        } finally {
            closeDatabase();
        }
    }

    public int unregister(Book book){
        try {
            return getDao().delete(book);
        } catch (SQLException e){
            Log.e(LOG_TAG, "has Exception", e);
        } finally {
            closeDatabase();
        }
        return 0;
    }

    public Book getBook(int id){
        try {
            return getDao().queryForId(id);
        } catch (SQLException e){
            Log.e(LOG_TAG, "has Exception", e);
        } finally {
            closeDatabase();
        }
        return null;
    }

    public Book getBook(String areaName){
        try {
            return getDao().queryBuilder().where().eq(Book.AREA_NAME_COLUMN, areaName).queryForFirst();
        } catch (SQLException e){
            Log.e(LOG_TAG, "has Exception", e);
        } finally {
            closeDatabase();
        }
        return null;
    }

    public boolean hasBook(int id){
        try {
            return getDao().idExists(id);
        } catch (SQLException e){
            Log.e(LOG_TAG, "has Exception", e);
        } finally {
            closeDatabase();
        }
        return false;

    }

    public boolean hasBook(Book book){
        return hasBook(book.getId());
    }

    public boolean hasBook(String areaName){
        try {
            return getDao().queryBuilder().where().eq(Book.AREA_NAME_COLUMN, areaName).countOf() != 0;
        } catch (SQLException e) {
            Log.e(LOG_TAG, "has Exception", e);
        }
        return false;
    }

    public List<Book> listBook(int[] ids){
        // FIXME: occur fail if not exists id.
        List<Book> books = new ArrayList<Book>();
        try {
            Dao<Book, Integer> dao = getDao();
            for (int id : ids) {
                books.add(dao.queryForId(id));
            }
            return books;
        } catch (SQLException e){
            Log.e(LOG_TAG, "has Exception", e);
        } finally {
            closeDatabase();
        }
        return books;
    }

    public List<Book> findAll(){
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
