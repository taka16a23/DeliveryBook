package info.no_ip.taka16.deliverybook.register_activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import info.no_ip.taka16.deliverybook.R;
import info.no_ip.taka16.deliverybook.book.Book;
import info.no_ip.taka16.deliverybook.book.BookRepository;
import info.no_ip.taka16.deliverybook.frame.Frame;
import info.no_ip.taka16.deliverybook.frame.FrameRepository;
import info.no_ip.taka16.deliverybook.root.Root;
import info.no_ip.taka16.deliverybook.root.RootRepository;


public class FrameFormActivity extends Activity {

    private EditText nmView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_subscriber);
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
        if (!bookRepository.hasBook("11area")) {
            bookRepository.register(new Book("11area"));
        }
        Book book = bookRepository.getBook("11area");
        Frame frame = new Frame();
        frame.setName(name);
        frame.setAddress(address);
        FrameRepository frameRepository = new FrameRepository(this);
        frameRepository.register(frame);
        book.insert(frame);
        bookRepository.register(book);

        Book book2 = bookRepository.getBook("11area");
//        book2.remove(5);
        bookRepository.register(book2);
//        frameRepository.unregister(book2.getFrame(0));
        Book book3 = bookRepository.getBook("11area");
//        for (int i = 0; i < book3.size(); i++) {
//            Frame tmpframe = book3.getFrame(i);
//            Log.d(FrameFormActivity.class.getSimpleName(), tmpframe.getId() + ":" + tmpframe.getName() + ":" + tmpframe.getAddress());
//        }

        Log.d(FrameFormActivity.class.getSimpleName(), " Saved" + name + " " + address);
        Toast.makeText(this, R.string.edit_save_notifier, Toast.LENGTH_SHORT).show();

        RootRepository rootRepository = new RootRepository(this);
        Root root1 = rootRepository.getRoot("hello");
        Root root2 = rootRepository.getRoot("world");

        ArrayList<String> areaNames = rootRepository.listAreaNames();
        for (int i = 0; i<areaNames.size();i++){
            Log.d(FrameFormActivity.class.getSimpleName(), areaNames.get(i));
        }
        // clear form
        nameView.setText("");
        addressView.setText("");

    }

}

