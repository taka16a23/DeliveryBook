package info.no_ip.taka16.deliverybook.book;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import info.no_ip.taka16.deliverybook.R;


public class BookRecyclerViewAdapter extends RecyclerView.Adapter<BookRecyclerViewAdapter.ViewHolder> {
    private Book book;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public ImageView handleView;

        public ViewHolder(View v) {
            super(v);
            textView = (TextView)v.findViewById(R.id.line_subscriber_name);
            handleView = (ImageView)v.findViewById(R.id.line_handle);
        }

        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }

    public BookRecyclerViewAdapter(Book book){
        this.book = book;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_item, parent, false);
        return new ViewHolder(view);
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
