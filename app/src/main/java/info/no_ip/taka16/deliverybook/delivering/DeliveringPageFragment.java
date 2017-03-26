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
    public static final String ARG_CURRENT_PAGE = "currentPosition";
    public static final String ARG_MAX_PAGE = "maxPosition";
    public static final String ARG_NAME = "name";
    public static final String ARG_ADDRESS = "address";

    /**
     * The fragment's page number, which is set to the argument value for {@link #ARG_PAGE}.
     */

    /**
     * Factory method for this fragment class. Constructs a new fragment for the given page number.
     */
    public static DeliveringPageFragment create(Subscriber subscriber, Position position) {
        DeliveringPageFragment fragment = new DeliveringPageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_CURRENT_PAGE, position.getCurrentPosition());
        args.putInt(ARG_MAX_PAGE, position.getCurrentPosition());
        args.putString(ARG_NAME, subscriber.getDisplayName());
        args.putString(ARG_ADDRESS, subscriber.getDisplayAddress());

        fragment.setArguments(args);
        return fragment;
    }

    public DeliveringPageFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout containing a title and body text.
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_delivering_page, container, false);

        // Set the title view to show the page number.
        ((TextView) rootView.findViewById(R.id.display_name)).setText(getArguments().getString("name"));
        // Set address
        ((TextView) rootView.findViewById(R.id.display_address)).setText(getArguments().getString("address"));
        return rootView;
    }

    /**
     * Returns the page number represented by this fragment object.
     */
    public int getPageNumber() {
        return getArguments().getInt(ARG_CURRENT_PAGE);
    }
}
