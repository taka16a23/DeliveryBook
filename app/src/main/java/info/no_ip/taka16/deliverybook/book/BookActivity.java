package info.no_ip.taka16.deliverybook.book;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import info.no_ip.taka16.deliverybook.R;

public class BookActivity extends Activity {
    public static final String AREA_NAME_INTENT_KEY = "AREA_NAME_INTENT_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        Intent intent = getIntent();
        String areaName = intent.getStringExtra(BookActivity.AREA_NAME_INTENT_KEY);
        if(areaName != null) {
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
        }
    }
}
