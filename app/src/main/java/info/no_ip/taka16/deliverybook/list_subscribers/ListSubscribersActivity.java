package info.no_ip.taka16.deliverybook.list_subscribers;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import info.no_ip.taka16.deliverybook.R;


public class ListSubscribersActivity extends Activity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    // ArrayListで定義
    private List<Integer> itemImages;
    private List<String> itemNames;
    private List<String> itemEmails;

    private static final String[] names = {
            "Yuka",
            "Kurumi",
            "Mai",
            "Miki",
            "Saya",
            "Toko",
            "Nagi",
            "Yuyu",
            "Yumiko",
            "Katakuriko"
    };

    // それぞれの画像ファイルをdarawableに入れます
    // ArrayListのInteger型
    private static final Integer[] photos = {
            R.drawable.sample,
            R.drawable.sample,
            R.drawable.sample,
            R.drawable.sample,
            R.drawable.sample,
            R.drawable.sample,
            R.drawable.sample,
            R.drawable.sample,
            R.drawable.sample,
            R.drawable.sample
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_subscribers);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        // 配列をArrayListにコピー
        itemNames = new ArrayList<String>(Arrays.asList(names));
        itemImages = new ArrayList<Integer>(Arrays.asList(photos));

        itemEmails = new ArrayList<String>();
        for(int i=0; i<itemNames.size() ;i++ ){
            String str = itemNames.get(i)+"@gmail.com";
            itemEmails.add(str);
        }

        // specify an adapter (see also next example)
        adapter = new MyAdapter(itemImages, itemNames, itemEmails);
        recyclerView.setAdapter(adapter);

        // ItemTouchHelper
        ItemTouchHelper itemDecor = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                        final int fromPos = viewHolder.getAdapterPosition();
                        final int toPos = target.getAdapterPosition();
                        adapter.notifyItemMoved(fromPos, toPos);
                        return true;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                        final int fromPos = viewHolder.getAdapterPosition();
                        itemImages.remove(fromPos);
                        itemNames.remove(fromPos);
                        itemEmails.remove(fromPos);

                        adapter.notifyItemRemoved(fromPos);
                    }
                });
        itemDecor.attachToRecyclerView(recyclerView);
    }
}
