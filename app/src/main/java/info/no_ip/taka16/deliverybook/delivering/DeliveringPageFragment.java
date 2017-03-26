package info.no_ip.taka16.deliverybook.delivering;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import info.no_ip.taka16.deliverybook.R;
import info.no_ip.taka16.deliverybook.subscribers.Subscriber;


public class DeliveringPageFragment extends Fragment {
    /**
     * The argument key for the page number this fragment represents.
     */
    public static final String ARG_PAGE = "page";

    /**
     * The fragment's page number, which is set to the argument value for {@link #ARG_PAGE}.
     */
    private int mPageNumber;

    private Subscriber subscriber;
    /**
     * Factory method for this fragment class. Constructs a new fragment for the given page number.
     */
    public static DeliveringPageFragment create(Subscriber subscriber, int pageNumber) {
        DeliveringPageFragment fragment = new DeliveringPageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        args.putString("name", subscriber.getDisplayName());
        args.putString("address", subscriber.getDisplayAddress());
        fragment.setArguments(args);
        return fragment;
    }

    public DeliveringPageFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout containing a title and body text.
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_delivering_page, container, false);

        // Set the title view to show the page number.
        // Subscriber subscriber = SubScriberFactory.getSubscriber(mPageNumber);
        ((TextView) rootView.findViewById(R.id.display_name)).setText(getArguments().getString("name"));
        // Set address
        ((TextView) rootView.findViewById(R.id.display_address)).setText(getArguments().getString("address"));
        return rootView;
    }

    /**
     * Returns the page number represented by this fragment object.
     */
    public int getPageNumber() {
        return mPageNumber;
    }
}
