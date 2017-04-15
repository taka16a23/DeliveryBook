package info.no_ip.taka16.deliverybook.book;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import info.no_ip.taka16.deliverybook.R;


public class BookAdditionActivity extends AppCompatActivity {

    public static final String INTENT_KEY = "addition";

    private BookRepository mBookRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_addition);

        mBookRepository = new BookRepository(this);
        Button saveButton = (Button)findViewById(R.id.area_save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText areaNameForm = (EditText)findViewById(R.id.edit_area_name);
                String areaName = areaNameForm.getText().toString();
                if(!mBookRepository.hasBook(areaName)){
                    mBookRepository.getBook(areaName);
                    Toast.makeText(getBaseContext(), areaName + " " + getResources().getString(R.string.edit_save_notifier), Toast.LENGTH_SHORT).show();
                }
                areaNameForm.setText(""); // clear edit form
            }
        });
    }
}
