package info.no_ip.taka16.deliverybook.deliverable;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import info.no_ip.taka16.deliverybook.R;

public class DeliverableListActivity extends AppCompatActivity {

    private ArrayList<String> deliverableNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliverable_list);

        deliverableNames = new ArrayList<String>();
        DeliverableRepository deliverableRepository = new DeliverableRepository(this);
        List<Deliverable> deliverables = deliverableRepository.findAll();
        if (deliverables != null) {
            for (Deliverable deliverable : deliverables) {
                if (deliverable != null) {
                    deliverableNames.add(deliverable.getName());
                }
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, deliverableNames);
        ListView listView = (ListView)findViewById(R.id.listview_deliverable);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(view.getContext(), deliverableNames.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        // FAB for add
        FloatingActionButton floatingActionButton = (FloatingActionButton)findViewById(R.id.fab_add_deliverable);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DeliverableFormActivity.class);
                startActivity(intent);
            }
        });


    }
}
