package info.no_ip.taka16.deliverybook.book;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;

import info.no_ip.taka16.deliverybook.R;
import info.no_ip.taka16.deliverybook.delivering.DeliveringActivity;
import info.no_ip.taka16.deliverybook.frame.FrameFormActivity;
import info.no_ip.taka16.deliverybook.frame.FrameListActivity;

public class BookActivity extends Activity {
    public static final String AREA_NAME_INTENT_KEY = "AREA_NAME_INTENT_KEY";

    private String areaName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        Intent intent = getIntent();
        this.areaName = intent.getStringExtra(BookActivity.AREA_NAME_INTENT_KEY);
        BookRepository bookRepository = new BookRepository(this);
        Book book = bookRepository.getBook(areaName);
        // Set AreaName
        TextView areaNameView = (TextView) findViewById(R.id.book_area_name);
        areaNameView.setText(book.getAreaName());
        // Set Detail
        TextView asa = new TextView(this);
        String asaString = "朝日:" + String.valueOf(book.size());
        asa.setText(asaString);
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.book_detail);
        linearLayout.addView(asa);

        // list frame button
        Button listFrameButton = (Button)findViewById(R.id.book_list_frame_button);
        listFrameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FrameListActivity.class);
                intent.putExtra(FrameListActivity.AREA_NAME_INTENT_KEY, areaName);
                startActivity(intent);
            }
        });

        // add frame button
        Button addFrameButton = (Button)findViewById(R.id.book_add_frame);
        addFrameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FrameFormActivity.class);
                intent.putExtra(FrameFormActivity.AREA_NAME_INTENT_KEY, areaName);
                startActivity(intent);
            }
        });

        // start delivery button
        Button startDeliveryButton = (Button)findViewById(R.id.book_start_delivery_button);
        startDeliveryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DeliveringActivity.class);
                intent.putExtra(DeliveringActivity.AREA_NAME_INTENT_KEY, areaName);
                startActivity(intent);
            }
        });

    }
}
