package info.no_ip.taka16.deliverybook;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import info.no_ip.taka16.deliverybook.book.BookListActivity;
import info.no_ip.taka16.deliverybook.book.BookActivity;
import info.no_ip.taka16.deliverybook.deliverable.DeliverableListActivity;


public class MainActivity extends ListActivity {
    /**
     * This class describes an individual sample (the sample title, and the activity class that
     * demonstrates this sample).
     */
    private class Sample {
        private CharSequence title;
        private Class activityOrFragment;
        // TODO: temporary implemented

        public Sample(int titleResId, Class activityClass) {
            this(getResources().getString(titleResId), activityClass);
        }

        public Sample(String title, Class activityClass){
            this.activityOrFragment = activityClass;
            this.title = title;
        }

        @Override
        public String toString() {
            return title.toString();
        }

        public void startActivity(Activity activity) {
            activity.startActivity(new Intent(activity, activityOrFragment));
        }
    }
    /**
     * The collection of all samples in the app. This gets instantiated in {@link
     * #onCreate(Bundle)} because the {@link Sample} constructor needs access to {@link
     * android.content.res.Resources}.
     */
    private static Sample[] mSamples;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate the list of samples.
        mSamples = new Sample[]{
                new Sample("BookList", BookListActivity.class),
                new Sample("Deliverable", DeliverableListActivity.class),
                new Sample("book activity", BookActivity.class),
        };

        setListAdapter(new ArrayAdapter<Sample>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                mSamples));
    }

    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id) {
        // Launch the sample associated with this list position.
        mSamples[position].startActivity(this);
    }
}
