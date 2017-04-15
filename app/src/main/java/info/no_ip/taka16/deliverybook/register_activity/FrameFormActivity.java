package info.no_ip.taka16.deliverybook.register_activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import info.no_ip.taka16.deliverybook.R;
import info.no_ip.taka16.deliverybook.book.Book;
import info.no_ip.taka16.deliverybook.book.BookRepository;
import info.no_ip.taka16.deliverybook.frame.Frame;
import info.no_ip.taka16.deliverybook.frame.FrameRepository;


public class FrameFormActivity extends Activity {

    private EditText nmView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_form);
    }

    public void onClickFinish(View view) {
        // handle name
        EditText nameView = (EditText) findViewById(R.id.edit_client_name);
        String name = nameView.getText().toString();

        // handle address
        EditText addressView = (EditText) findViewById(R.id.edit_client_address);
        String address = addressView.getText().toString();

        // save to sql
        BookRepository bookRepository = new BookRepository(this);
        Book book = bookRepository.getBook("11area");
        Frame frame = new Frame();
        frame.setName(name);
        frame.setAddress(address);

        FrameRepository frameRepository = new FrameRepository(this);
        frameRepository.register(frame);
        book.insert(frame);

        for (int i = 0; i < book.size(); i++) {
            Frame tmpframe = book.getFrame(i);
            Log.d(FrameFormActivity.class.getSimpleName(), tmpframe.getId() + ":" + tmpframe.getName() + ":" + tmpframe.getAddress());
        }

        Log.d(FrameFormActivity.class.getSimpleName(), " Saved" + name + " " + address);
        Toast.makeText(this, R.string.edit_save_notifier, Toast.LENGTH_SHORT).show();

        // clear form
        nameView.setText("");
        addressView.setText("");
    }

}

