package info.no_ip.taka16.deliverybook.book;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import info.no_ip.taka16.deliverybook.R;
import info.no_ip.taka16.deliverybook.delivering.Position;


public class BookPagerFragment extends Fragment {
    /**
     * The argument key for the page number this fragment represents.
     */
    public static final String ARG_CURRENT_PAGE = "currentPosition";
    public static final String ARG_MAX_PAGE = "maxPosition";
    public static final String ARG_AREA_NAME = "area_name";

    private TextView addressView;
    private Position mPosition;
    private Book book;
    /**
     * Factory method for this fragment class. Constructs a new fragment for the given page number.
     */
    public static info.no_ip.taka16.deliverybook.book.BookPagerFragment create(Book book, Position position) {
        info.no_ip.taka16.deliverybook.book.BookPagerFragment fragment = new info.no_ip.taka16.deliverybook.book.BookPagerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_CURRENT_PAGE, position.getCurrentPosition());
        args.putInt(ARG_MAX_PAGE, position.getCurrentPosition());
        args.putString(ARG_AREA_NAME, book.getAreaName());
        fragment.setBook(book);
        fragment.setArguments(args);
        fragment.setPosition(position);
        return fragment;
    }

    public BookPagerFragment() {
    }

    public void setPosition(Position position){
        this.mPosition = position;
    }

    public void setBook(Book book){
        this.book = book;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout containing a title and body text.
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_book, container, false);
        ((TextView) rootView.findViewById(R.id.book_area_name)).setText(book.getAreaName());
        return rootView;
    }
}

