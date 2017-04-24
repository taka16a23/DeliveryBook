package info.no_ip.taka16.deliverybook.book;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import info.no_ip.taka16.deliverybook.R;
import info.no_ip.taka16.deliverybook.delivery.DeliveryActivity;
import info.no_ip.taka16.deliverybook.frame.FrameFormActivity;
import info.no_ip.taka16.deliverybook.frame.FrameListActivity;

public class BookActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    public static final String AREA_NAME_INTENT_KEY = "area_name";

    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private BookRepository bookRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        bookRepository = new BookRepository(this);
        setContentView(R.layout.activity_book);
            List<Book> books = bookRepository.listBook();
            mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager(), books);

            // Set up the ViewPager with the sections adapter.
            mViewPager = (ViewPager) findViewById(R.id.container);
            mViewPager.setAdapter(mSectionsPagerAdapter);

            Intent intent = getIntent();
            String areaName = intent.getStringExtra(AREA_NAME_INTENT_KEY);
            if (areaName != null) {
                int position = mSectionsPagerAdapter.getPosition(areaName);
                if (position != -1) {
                    mViewPager.setCurrentItem(position);
                }
            }
    }

    @Override
    public void onResume(){
        super.onResume();
        bookRepository = new BookRepository(this);

        if(bookRepository.isEmpty()){
            Intent intent = new Intent(this, BookFormActivity.class);
            startActivity(intent);
        } else {
            mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager(), bookRepository.listBook());
            mViewPager.setAdapter(mSectionsPagerAdapter);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_book, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()){
            case R.id.action_add_book:
                Intent intent = new Intent(this, BookFormActivity.class);
                startActivity(intent);
                break;
            case R.id.action_remove_book:
                BookRepository bookRepository = new BookRepository(this);
                int position = mViewPager.getCurrentItem();
                PlaceholderFragment fragment = (PlaceholderFragment)mSectionsPagerAdapter.getItem(position);
                bookRepository.removeBook(fragment.getBook());
                Intent remove_intent = new Intent(this, BookActivity.class);
                startActivity(remove_intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        public static final String ARG_CURRENT_PAGE = "currentPosition";
        public static final String ARG_MAX_PAGE = "maxPosition";

        private Book book;
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber, Book book) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_CURRENT_PAGE, sectionNumber);
            args.putInt(ARG_MAX_PAGE, sectionNumber);
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setBook(book);
            fragment.setArguments(args);
            return fragment;
        }

        public void setBook(Book book){
            this.book = book;
        }

        public Book getBook(){
            return this.book;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_book, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.book_areaname);
            textView.setText(this.book.getAreaName());
            // list frame button
            Button listFrameButton = (Button)rootView.findViewById(R.id.book_list_frame_button);
            listFrameButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), FrameListActivity.class);
                    intent.putExtra(FrameListActivity.AREA_NAME_INTENT_KEY, book.getAreaName());
                    startActivity(intent);
                }
            });

            // add frame button
            Button addFrameButton = (Button)rootView.findViewById(R.id.book_add_frame);
            addFrameButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), FrameFormActivity.class);
                    intent.putExtra(FrameFormActivity.AREA_NAME_INTENT_KEY, book.getAreaName());
                    startActivity(intent);
                }
            });

            // start delivery button
            Button startDeliveryButton = (Button)rootView.findViewById(R.id.book_start_delivery_button);
            startDeliveryButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DeliveryActivity.class);
                    intent.putExtra(DeliveryActivity.AREA_NAME_INTENT_KEY, book.getAreaName());
                    startActivity(intent);
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
        private List<Book> books;

        public SectionsPagerAdapter(FragmentManager fm, List<Book> books) {
            super(fm);
            this.books = books;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position, books.get(position));
        }

        @Override
        public int getCount() {
            return books.size();
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

        public int getPosition(String areaName){
            for (int i = 0; i < this.books.size(); i++){
                if (areaName.equals(this.books.get(i).getAreaName())){
                    return i;
                }
            }
            return -1;
        }
    }
}
