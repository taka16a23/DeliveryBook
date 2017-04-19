package info.no_ip.taka16.deliverybook.book;


import android.app.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import java.util.List;

import info.no_ip.taka16.deliverybook.R;

public class BookActivity extends Activity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        BookRepository bookRepository = new BookRepository(this);
        List<Book> books = bookRepository.listBook();
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager(), books);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        Intent intent = getIntent();
        String areaName = intent.getStringExtra(AREA_NAME_INTENT_KEY);
        if (areaName != null){
            int position = mSectionsPagerAdapter.getPosition(areaName);
            if (position != -1){
                mViewPager.setCurrentItem(position);
            }
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
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

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_book, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.book_areaname);
            textView.setText(this.book.getAreaName());
//            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
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
            // Show 3 total pages.
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
