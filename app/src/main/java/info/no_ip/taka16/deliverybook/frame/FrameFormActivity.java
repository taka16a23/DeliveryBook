package info.no_ip.taka16.deliverybook.frame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import info.no_ip.taka16.deliverybook.R;
import info.no_ip.taka16.deliverybook.book.Book;
import info.no_ip.taka16.deliverybook.book.BookRepository;


public class FrameFormActivity extends AppCompatActivity {

    public static final String AREA_NAME_INTENT_KEY = "area_name";
    public static final String INTENT_KEY_FRAME_ID = "frame_id";

    private EditText nmView;
    private String areaName;
    private int frameId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_form);

        Intent intent = getIntent();
        this.areaName = intent.getStringExtra(AREA_NAME_INTENT_KEY);

        frameId = intent.getIntExtra(INTENT_KEY_FRAME_ID, -1);
        if (frameId != -1) {
            FrameRepository frameRepository = new FrameRepository(this);
            Frame frame = frameRepository.getFrame(frameId);
            EditText nameView = (EditText) findViewById(R.id.edit_client_name);
            nameView.setText(frame.getName());

            // handle address
            EditText addressView = (EditText) findViewById(R.id.edit_client_address);
            addressView.setText(frame.getAddress());

            // handle deliverable
            EditText deliverableView = (EditText)findViewById(R.id.editText_deliverable);
            deliverableView.setText(frame.getDeliverable());

            // handle description
            EditText descriptionView = (EditText)findViewById(R.id.editText_description);
            descriptionView.setText(frame.getDescription());
        }

        // title
        TextView textView = (TextView)findViewById(R.id.frame_form_area_name);
        textView.setText(this.areaName);

        // set area name to action bar
        setTitle(this.areaName + " " + getTitle());
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
        FrameRepository frameRepository = new FrameRepository(this);
        Frame frame;
        if(frameId == -1) {
            frame = new Frame();
        } else {
            frame = frameRepository.getFrame(frameId);
        }
        frame.setName(name);
        frame.setAddress(address);
        frame.setDeliverable(deliverable);
        frame.setDescription(description);
        frameRepository.register(frame);

        if(frameId == -1) {
            book.insert(frame);
        }

        Log.d(FrameFormActivity.class.getSimpleName(), " Saved " + name + " " + address);
        Toast toast = Toast.makeText(this, R.string.edit_save_notifier, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();


    }

}

