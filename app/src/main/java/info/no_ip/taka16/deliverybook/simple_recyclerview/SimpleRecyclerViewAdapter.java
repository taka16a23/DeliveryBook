package info.no_ip.taka16.deliverybook.simple_recyclerview;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import RecyclerView.Adaper;
import info.no_ip.taka16.deliverybook.R;
import info.no_ip.taka16.deliverybook.list_subscribers.ListSubscribersAdapter;


public class SimpleRecyclerViewAdapter extends Adaper<SimpleRecyclerViewAdapter.ViewHolder> {
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
}
