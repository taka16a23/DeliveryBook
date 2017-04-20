package info.no_ip.taka16.deliverybook.book;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
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

        // action for click item
        ListView listView = (ListView)findViewById(R.id.listview_books);
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
                Intent intent = new Intent(view.getContext(), BookFormActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume(){
        super.onResume();

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_book_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()){
            case R.id.list_book_add:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
