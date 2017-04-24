package info.no_ip.taka16.deliverybook.book;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import info.no_ip.taka16.deliverybook.R;


public class BookFormActivity extends AppCompatActivity {

    public static final String INTENT_KEY = "addition";

    private BookRepository mBookRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_form);

        mBookRepository = new BookRepository(this);
        Button saveButton = (Button)findViewById(R.id.area_save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText areaNameForm = (EditText)findViewById(R.id.edit_area_name);
                String areaName = areaNameForm.getText().toString();
                String textMessage = areaName;
                if(!mBookRepository.hasBook(areaName)){
                    mBookRepository.getBook(areaName);
                    textMessage += " " + getResources().getString(R.string.edit_save_notifier);
                } else {
                    textMessage += " " + getResources().getString(R.string.already_exists);
                }
                Toast toast = Toast.makeText(getBaseContext(), textMessage, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                areaNameForm.setText(""); // clear edit form
            }
        });

        if(mBookRepository.isEmpty()){
            ((TextView)findViewById(R.id.missing_book_prompt1)).setVisibility(View.VISIBLE);
            ((TextView)findViewById(R.id.missing_book_prompt2)).setVisibility(View.VISIBLE);
        }

    }

}
