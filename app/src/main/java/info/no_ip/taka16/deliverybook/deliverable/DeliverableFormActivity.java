package info.no_ip.taka16.deliverybook.deliverable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import info.no_ip.taka16.deliverybook.R;

public class DeliverableFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliverable_form);

        // save deliverable
        Button saveButton = (Button)findViewById(R.id.button_deliverable_form_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText deliverableNameForm = (EditText)findViewById(R.id.edittext_deliverable_form_name);
                String name = deliverableNameForm.getText().toString();
                DeliverableRepository deliverableRepository = new DeliverableRepository(v.getContext());
//                Deliverable newDeliverable = deliverableRepository.getDeliverable(name);
                Deliverable newDeliverable = new Deliverable();
                newDeliverable.setName(name);
                deliverableRepository.save(newDeliverable);
                Toast.makeText(v.getContext(), getString(R.string.save), Toast.LENGTH_SHORT).show();
                Deliverable tmp = deliverableRepository.getDeliverable(name);
            }
        });

    }
}
