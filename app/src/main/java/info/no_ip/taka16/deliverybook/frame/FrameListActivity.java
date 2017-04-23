package info.no_ip.taka16.deliverybook.frame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;

import info.no_ip.taka16.deliverybook.R;
import info.no_ip.taka16.deliverybook.book.Book;
import info.no_ip.taka16.deliverybook.book.BookRepository;
import info.no_ip.taka16.deliverybook.simple_recyclerview.SimpleItemTouchHelperCallback;
import info.no_ip.taka16.deliverybook.book.BookRecyclerViewAdapter;


public class FrameListActivity extends AppCompatActivity {
    public static final String AREA_NAME_INTENT_KEY = "area_name";

    private Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_list);

        // Make book from Intent's AreaName
        BookRepository bookRepository = new BookRepository(this);
        book = bookRepository.getBook(getIntent().getStringExtra(AREA_NAME_INTENT_KEY));
    }

    @Override
    public void onResume(){
        super.onResume();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // Use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        BookRecyclerViewAdapter adapter = new BookRecyclerViewAdapter(book);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper itemDecor = new ItemTouchHelper(new SimpleItemTouchHelperCallback(adapter));
        itemDecor.attachToRecyclerView(recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Set title for action bar
        setTitle(book.getAreaName() + " " + getTitle());
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_frame_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()){
            case R.id.action_add_frame:
                Intent intent = new Intent(this, FrameFormActivity.class);
                intent.putExtra(FrameFormActivity.AREA_NAME_INTENT_KEY, this.book.getAreaName());
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
