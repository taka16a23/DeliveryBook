package info.no_ip.taka16.deliverybook.simple_recyclerview;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import info.no_ip.taka16.deliverybook.R;
import info.no_ip.taka16.deliverybook.book.Book;


public class SimpleRecyclerViewAdapter extends RecyclerView.Adapter<SimpleRecyclerViewAdapter.ViewHolder> {
    private Book book;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public ImageView handleView;

        public ViewHolder(View v) {
            super(v);
            textView = (TextView)v.findViewById(R.id.line_subscriber_name2);
            handleView = (ImageView)v.findViewById(R.id.line_handle2);
        }

        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }

    public SimpleRecyclerViewAdapter(Book book){
        this.book = book;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_frames, parent, false);
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
}
