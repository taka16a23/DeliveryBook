package info.no_ip.taka16.deliverybook.simple_recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import info.no_ip.taka16.deliverybook.R;
import info.no_ip.taka16.deliverybook.book.Book;
import info.no_ip.taka16.deliverybook.book.BookRepository;


public class FrameListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_list);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        BookRepository bookRepository = new BookRepository(this);
        Book book = bookRepository.getBook("11area");

        SimpleRecyclerViewAdapter adapter = new SimpleRecyclerViewAdapter(book);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper itemDecor = new ItemTouchHelper(new SimpleItemTouchHelperCallback(adapter));
        itemDecor.attachToRecyclerView(recyclerView);
    }
}

