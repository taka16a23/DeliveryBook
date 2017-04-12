package info.no_ip.taka16.deliverybook.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import info.no_ip.taka16.deliverybook.frame.Frame;
import info.no_ip.taka16.deliverybook.book.Book;


public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    //	private static final String DATABASE_NAME = Environment.getExternalStorageDirectory() + "/ormExample.db";
    private static final String DATABASE_NAME = "DeliveryBook.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Frame.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Failed create database.", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i2) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
