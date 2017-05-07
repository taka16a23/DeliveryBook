package info.no_ip.taka16.deliverybook.delivery;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import info.no_ip.taka16.deliverybook.R;
import info.no_ip.taka16.deliverybook.book.Book;
import info.no_ip.taka16.deliverybook.book.BookRepository;
import info.no_ip.taka16.deliverybook.frame.Frame;


public class DeliveryActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private String areaName;

    // for Intent
    public static final String AREA_NAME_INTENT_KEY = "area_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);


        Intent intent = getIntent();
        this.areaName = intent.getStringExtra(AREA_NAME_INTENT_KEY);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        Book book = (new BookRepository(this)).getBook(this.areaName);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager(), book);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // set title for menu bar
        setTitle(areaName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_delivery, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        public static final String ARG_CURRENT_PAGE = "currentPosition";
        public static final String ARG_MAX_PAGE = "maxPosition";

        private TextView addressView;
        private Position mPosition;
        private Frame frame;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(Frame frame, Position position) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_CURRENT_PAGE, position.getCurrentPosition());
            args.putInt(ARG_MAX_PAGE, position.getCurrentPosition());
            fragment.setArguments(args);
            fragment.setFrame(frame);
            fragment.setPosition(position);
            return fragment;
        }

        public void setPosition(Position position){
            this.mPosition = position;
        }

        public void setFrame(Frame frame){
            this.frame = frame;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout containing a title and body text.
            View rootView = inflater.inflate(R.layout.fragment_delivery, container, false);

            // set deliverable
            TextView deliverableView = (TextView) rootView.findViewById(R.id.delivery_deliverable);
            deliverableView.setText(frame.getDeliverable());

            // Set the title view to show the page number.
            ((TextView) rootView.findViewById(R.id.delivery_position)).setText(mPosition.toString());
            TextView displayName = (TextView)rootView.findViewById(R.id.display_name);
            displayName.setText(frame.getDisplayName());
            if(frame.getDeliverable().contains("毎日")){
                displayName.setBackgroundColor(getResources().getColor(R.color.mai_name));
                deliverableView.setBackgroundColor(getResources().getColor(R.color.mai_deliverable));
            } else if(frame.getDeliverable().contains("朝日")){
                displayName.setBackgroundColor(getResources().getColor(R.color.asa_name));
                deliverableView.setBackgroundColor(getResources().getColor(R.color.asa_deliverable));
            } else if(frame.getDeliverable().contains("産経")){
                displayName.setBackgroundColor(getResources().getColor(R.color.sankei_name));
                deliverableView.setBackgroundColor(getResources().getColor(R.color.sankei_deliverable));
            } else if(frame.getDeliverable().contains("読売")){
                displayName.setBackgroundColor(getResources().getColor(R.color.yomi_name));
                deliverableView.setBackgroundColor(getResources().getColor(R.color.yomi_deliverable));
            }

            // Set address to view
            ((TextView) rootView.findViewById(R.id.display_address)).setText(frame.getDisplayAddress());
            addressView = (TextView)rootView.findViewById(R.id.display_address);
            addressView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String addressText = addressView.getText().toString();
                    Uri gmmIntentUri = Uri.parse("http://maps.google.co.jp/maps?q=" + addressText);
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    startActivity(mapIntent);
                }
            });

            // description
            ((TextView) rootView.findViewById(R.id.delivery_description)).setText(frame.getDescription());

            // implement onClick to address image view
            ImageView addressIconView = (ImageView)rootView.findViewById(R.id.address_icon);
            addressIconView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String addressText = addressView.getText().toString();
                    Uri gmmIntentUri = Uri.parse("http://maps.google.co.jp/maps?q=" + addressText);
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    startActivity(mapIntent);
                }
            });
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private Book book;

        public SectionsPagerAdapter(FragmentManager fm, Book book) {
            super(fm);
            this.book = book;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(book.getFrame(position), new Position(getCount(), position));
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return book.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }

    public class Position {
        private int maxPosition;
        private int currentPosition = 0;

        public Position(int maxPosition){
            this.maxPosition = maxPosition;
        }

        public Position(int maxPosition, int currentPosition){
            this(maxPosition);
            this.currentPosition = currentPosition;
        }

        public int getMaxPosition(){
            return maxPosition;
        }

        public int getCurrentPosition(){
            return currentPosition;
        }

        public void setCurrentPosition(int currentPosition){
            this.currentPosition = currentPosition;
        }

        public String toString(){
            return (currentPosition + 1) + " / " + maxPosition;
        }
    }

}
