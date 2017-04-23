package info.no_ip.taka16.deliverybook.frame;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import info.no_ip.taka16.deliverybook.R;
import info.no_ip.taka16.deliverybook.book.Book;
import info.no_ip.taka16.deliverybook.book.BookRepository;


public class FrameListActivity extends AppCompatActivity {
    public static final String AREA_NAME_INTENT_KEY = "area_name";

    private Book book;
    private String areaName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_list);

        // Relation with Intent
        this.areaName = getIntent().getStringExtra(AREA_NAME_INTENT_KEY);
    }

    @Override
    public void onResume(){
        super.onResume();

        BookRepository bookRepository = new BookRepository(this);
        book = bookRepository.getBook(this.areaName);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // Use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, book);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper itemDecor = new ItemTouchHelper(new ItemTouchHelperCallback(adapter));
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

    public static class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

        // ViewHolder
        public static class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public final Book mBook;

            public TextView textView;
            public ImageView handleView;
            private ColorStateList textColors;

            public ViewHolder(View v, Book book) {
                super(v);
                textView = (TextView)v.findViewById(R.id.item_name);
                handleView = (ImageView)v.findViewById(R.id.item_handle);
                mBook = book;

                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), FrameFormActivity.class);
                        intent.putExtra(FrameFormActivity.AREA_NAME_INTENT_KEY, mBook.getAreaName());
                        intent.putExtra(FrameFormActivity.INTENT_KEY_FRAME_ID, mBook.getFrame(getPosition()).getId());
                        v.getContext().startActivity(intent);
                    }
                });
                textColors = textView.getTextColors();
            }

            public void onItemSelected() {
                itemView.setBackgroundColor(Color.LTGRAY);
                textView.setTextColor(Color.BLACK);
            }

            public void onItemClear() {
                itemView.setBackgroundColor(0);
                textView.setTextColor(textColors);
            }
        }

        /*
           For Adapter
         */
        private Book book;
        private Context context;

        public RecyclerViewAdapter(Context context, Book book){
            this.context = context;
            this.book = book;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_item, parent, false);
            return new ViewHolder(view, this.book);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position){
            holder.textView.setText(this.book.getFrame(position).getName());
        }

        @Override
        public int getItemCount(){
            return book.size();
        }

        public void remove(int position){
            book.remove(position);
            Toast.makeText(this.context, R.string.removed_notifier, Toast.LENGTH_SHORT).show();
        }

        public void move(int fromPos, int toPos){
            book.move(fromPos, toPos);
        }
    }

    /*
       For touch callback
     */
    public static class ItemTouchHelperCallback extends ItemTouchHelper.Callback {
        RecyclerViewAdapter mAdapter;

        public ItemTouchHelperCallback(RecyclerViewAdapter adapter){
            mAdapter = adapter;
        }

        @Override
        public boolean isLongPressDragEnabled() {
            return true;
        }

        @Override
        public boolean isItemViewSwipeEnabled() {
            return true;
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            final int fromPos = viewHolder.getAdapterPosition();
            final int toPos = target.getAdapterPosition();
            mAdapter.move(fromPos, toPos);
            mAdapter.notifyItemMoved(fromPos, toPos);
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            final int fromPos = viewHolder.getAdapterPosition();
            mAdapter.remove(fromPos);
            mAdapter.notifyItemRemoved(fromPos);
        }

        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
            if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                RecyclerViewAdapter.ViewHolder recyclerViewHolder = (RecyclerViewAdapter.ViewHolder) viewHolder;
                recyclerViewHolder.onItemSelected();
            }
            super.onSelectedChanged(viewHolder, actionState);
        }

        @Override
        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);

            if (viewHolder instanceof RecyclerViewAdapter.ViewHolder) {
                // Tell the view holder it's time to restore the idle state
                RecyclerViewAdapter.ViewHolder itemViewHolder = (RecyclerViewAdapter.ViewHolder) viewHolder;
                itemViewHolder.onItemClear();
            }
        }

        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                final int swipeFlags = 0;
                return makeMovementFlags(dragFlags, swipeFlags);
            } else {
                final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                final int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
                return makeMovementFlags(dragFlags, swipeFlags);
            }
        }
    }
}
