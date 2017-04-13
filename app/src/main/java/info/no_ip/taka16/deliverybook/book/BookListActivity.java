package info.no_ip.taka16.deliverybook.book;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import info.no_ip.taka16.deliverybook.R;

import static android.R.id.list;

public class BookListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        BookRepository bookRepository = new BookRepository(this);
        ArrayList<String> areaNames = new ArrayList<String>();
        for (Book book : bookRepository.listBook()) {
            areaNames.add(book.getAreaName());
            Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
            Log.d("debug", book.getAreaName());
        }
        File prefsdir = new File(getApplicationInfo().dataDir,"shared_prefs");

        if(prefsdir.exists() && prefsdir.isDirectory()){
            String[] slist = prefsdir.list();
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, android.R.id.text1,list);
            for (String string : slist) {
                Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, slist);
            setListAdapter(adapter);

        }

    }

    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id){
        Toast.makeText(getBaseContext(), "Click", Toast.LENGTH_SHORT).show();
    }
}
