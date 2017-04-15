package info.no_ip.taka16.deliverybook.book;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import info.no_ip.taka16.deliverybook.R;


public class BookListActivity extends ListActivity {

    private ArrayList<String> areaNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        BookRepository bookRepository = new BookRepository(this);
        areaNames = new ArrayList<String>();
        for (Book book : bookRepository.listBook()) {
            areaNames.add(book.getAreaName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, areaNames);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id){
        Log.d("debug", this.areaNames.get(position));
        Intent intent = new Intent(this, BookActivity.class);
        intent.putExtra(BookActivity.AREA_NAME_INTENT_KEY, this.areaNames.get(position));
        startActivity(intent);
    }
}
