package info.no_ip.taka16.deliverybook.delivering;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import info.no_ip.taka16.deliverybook.R;
import info.no_ip.taka16.deliverybook.frame.Frame;


public class DeliveringPageFragment extends Fragment implements View.OnClickListener {
    /**
     * The argument key for the page number this fragment represents.
     */
    public static final String ARG_CURRENT_PAGE = "currentPosition";
    public static final String ARG_MAX_PAGE = "maxPosition";
    public static final String ARG_NAME = "name";
    public static final String ARG_ADDRESS = "address";

    private TextView addressView;
    private Position mPosition;
    /**
     * Factory method for this fragment class. Constructs a new fragment for the given page number.
     */
    public static DeliveringPageFragment create(Frame frame, Position position) {
        DeliveringPageFragment fragment = new DeliveringPageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_CURRENT_PAGE, position.getCurrentPosition());
        args.putInt(ARG_MAX_PAGE, position.getCurrentPosition());
        args.putString(ARG_NAME, frame.getDisplayName());
        args.putString(ARG_ADDRESS, frame.getDisplayAddress());

        fragment.setArguments(args);
        fragment.setPosition(position);
        return fragment;
    }

    public DeliveringPageFragment() {

    }

    public void setPosition(Position position){
        this.mPosition = position;
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
        ((TextView) rootView.findViewById(R.id.delivering_position)).setText(mPosition.toString());
        ((TextView) rootView.findViewById(R.id.display_name)).setText(getArguments().getString("name"));
        // Set address to view
        ((TextView) rootView.findViewById(R.id.display_address)).setText(getArguments().getString("address"));
        addressView = (TextView)rootView.findViewById(R.id.display_address);
        addressView.setOnClickListener(this);
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

    /**
     * Returns the page number represented by this fragment object.
     */
    public int getPageNumber() {
        return getArguments().getInt(ARG_CURRENT_PAGE);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() != R.id.display_address){
            return;
        }
        // TODO: show direction from here
        // TODO: other method to embed map
        String addressText = addressView.getText().toString();
        Uri gmmIntentUri = Uri.parse("http://maps.google.co.jp/maps?q=" + addressText);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        startActivity(mapIntent);
    }
}
