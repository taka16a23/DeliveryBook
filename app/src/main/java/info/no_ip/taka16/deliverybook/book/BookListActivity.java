package info.no_ip.taka16.deliverybook.book;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import info.no_ip.taka16.deliverybook.R;


public class BookListActivity extends AppCompatActivity {

    private ArrayList<String> areaNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        BookRepository bookRepository = new BookRepository(this);
        areaNames = new ArrayList<String>();
        for (Book book : bookRepository.listBook()) {
            if(book != null) {
                areaNames.add(book.getAreaName());
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, areaNames);
        ListView listView = (ListView)findViewById(R.id.listview_books);
        listView.setAdapter(adapter);

        // action for click item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent intent = new Intent(view.getContext(), BookActivity.class);
                intent.putExtra(BookActivity.AREA_NAME_INTENT_KEY, areaNames.get(position));
                startActivity(intent);
            }
        });

        // FAB for add
        FloatingActionButton floatingActionButton = (FloatingActionButton)findViewById(R.id.fab_add_book);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), BookAdditionActivity.class);
                startActivity(intent);
            }
        });

    }
}
