package info.no_ip.taka16.deliverybook.list_subscribers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import info.no_ip.taka16.deliverybook.R;
import info.no_ip.taka16.deliverybook.subscribers.Subscriber;


public class ListSubscribersAdapter extends RecyclerView.Adapter<ListSubscribersAdapter.ViewHolder> {

    private List<Subscriber> subscribers;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;

        public ViewHolder(View v) {
            super(v);
            textView = (TextView)v.findViewById(R.id.line_subscriber_name);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ListSubscribersAdapter(List<Subscriber> subscribers) {
        this.subscribers = subscribers;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.line_subscribers, parent, false);

        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.setText(subscribers.get(position).getName());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return subscribers.size();
    }
}
