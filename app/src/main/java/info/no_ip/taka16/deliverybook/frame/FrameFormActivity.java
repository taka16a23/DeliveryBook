package info.no_ip.taka16.deliverybook.frame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import info.no_ip.taka16.deliverybook.R;
import info.no_ip.taka16.deliverybook.book.Book;
import info.no_ip.taka16.deliverybook.book.BookRepository;


public class FrameFormActivity extends Activity {

    public static final String AREA_NAME_INTENT_KEY = "area_name";

    private EditText nmView;
    private String areaName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_form);

        Intent intent = getIntent();
        this.areaName = intent.getStringExtra(AREA_NAME_INTENT_KEY);

        // title
        TextView textView = (TextView)findViewById(R.id.frame_form_area_name);
        textView.setText(this.areaName);

    }

    public void onClickFinish(View view) {
        // handle name
        EditText nameView = (EditText) findViewById(R.id.edit_client_name);
        String name = nameView.getText().toString();
        nameView.setText("");

        // handle address
        EditText addressView = (EditText) findViewById(R.id.edit_client_address);
        String address = addressView.getText().toString();
        addressView.setText("");

        // handle deliverable
        EditText deliverableView = (EditText)findViewById(R.id.editText_deliverable);
        String deliverable = deliverableView.getText().toString();
        deliverableView.setText("");

        // handle description
        EditText descriptionView = (EditText)findViewById(R.id.editText_description);
        String description = descriptionView.getText().toString();
        descriptionView.setText("");

        // save to sql
        BookRepository bookRepository = new BookRepository(this);
        Book book = bookRepository.getBook(this.areaName);
        Frame frame = new Frame();
        frame.setName(name);
        frame.setAddress(address);
        frame.setDeliverable(deliverable);
        frame.setDescription(description);

        FrameRepository frameRepository = new FrameRepository(this);
        frameRepository.register(frame);
        book.insert(frame);

        for (int i = 0; i < book.size(); i++) {
            Frame tmpframe = book.getFrame(i);
            Log.d(FrameFormActivity.class.getSimpleName(), tmpframe.getId() + ":" + tmpframe.getName() + ":" + tmpframe.getAddress());
        }

        Log.d(FrameFormActivity.class.getSimpleName(), " Saved" + name + " " + address);
        Toast.makeText(this, R.string.edit_save_notifier, Toast.LENGTH_SHORT).show();


    }

}

