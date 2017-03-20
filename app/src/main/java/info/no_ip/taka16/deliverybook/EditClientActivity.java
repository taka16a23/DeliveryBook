package info.no_ip.taka16.deliverybook;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditClientActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_client);
    }

    public void onClickFinish(View view){
        // handle name
        EditText nameView = (EditText)findViewById(R.id.edit_client_name);
        String name = nameView.getText().toString();
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        Log.d("Debug", name);

        // handle address
        EditText addressView = (EditText)findViewById(R.id.edit_client_address);
        String address = addressView.getText().toString();
        Toast.makeText(this, address, Toast.LENGTH_SHORT).show();
        Log.d("Debug", address);

    }
}
