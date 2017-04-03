package info.no_ip.taka16.deliverybook.list_subscribers;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.List;

import info.no_ip.taka16.deliverybook.R;
import info.no_ip.taka16.deliverybook.subscribers.Subscriber;
import info.no_ip.taka16.deliverybook.subscribers.SubscribersBook;


public class ListSubscribersActivity extends Activity {
    private RecyclerView recyclerView;
    private ListSubscribersAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Subscriber> subscribers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribers_list);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        SubscribersBook book = new SubscribersBook(this);
        subscribers = book.findAll();

        adapter = new ListSubscribersAdapter(subscribers);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper itemDecor = new ItemTouchHelper(new SimpleItemTouchHelperCallback(adapter));
        itemDecor.attachToRecyclerView(recyclerView);
    }
}
