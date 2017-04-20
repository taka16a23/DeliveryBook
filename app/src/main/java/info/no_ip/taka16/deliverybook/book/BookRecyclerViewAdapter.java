package info.no_ip.taka16.deliverybook.book;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import info.no_ip.taka16.deliverybook.R;
import info.no_ip.taka16.deliverybook.frame.FrameFormActivity;


public class BookRecyclerViewAdapter extends RecyclerView.Adapter<BookRecyclerViewAdapter.ViewHolder> {
    private Book book;

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

    public BookRecyclerViewAdapter(Book book){
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
    }

    public void move(int fromPos, int toPos){
        book.move(fromPos, toPos);
    }
}
