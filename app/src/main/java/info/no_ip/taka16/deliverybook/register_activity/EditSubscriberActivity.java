package info.no_ip.taka16.deliverybook.register_activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import info.no_ip.taka16.deliverybook.R;
import info.no_ip.taka16.deliverybook.subscribers.Subscriber;
import info.no_ip.taka16.deliverybook.subscribers.SubscribersBook;

public class EditSubscriberActivity extends Activity {

    private EditText nmView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_subscriber);
    }

    public void onClickFinish(View view){
        // handle name
        EditText nameView = (EditText)findViewById(R.id.edit_client_name);
        String name = nameView.getText().toString();

        // handle address
        EditText addressView = (EditText)findViewById(R.id.edit_client_address);
        String address = addressView.getText().toString();

        // save to sql
        SubscribersBook dbTable = new SubscribersBook(this);
        Subscriber subscriber = new Subscriber(name, name, address, address);
        dbTable.save(subscriber);

        Log.d(EditSubscriberActivity.class.getSimpleName(), "Saved" + name + " " + address);
        Toast.makeText(this, R.string.edit_save_notifier, Toast.LENGTH_SHORT).show();

        // clear form
        nameView.setText("");
        addressView.setText("");

    }

}
