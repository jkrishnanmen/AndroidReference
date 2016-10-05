package com.example.empressum.jake;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListViewActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        //Get listview object form xml
        listView=(ListView) findViewById(R.id.list);

        //Defined array values to shot in Listview
        String[] values= new String[] {"Android List View","aDAPTER IMPLEMENTATION", "simple list view","create list view android","android example","list view source code","list view array adapter","android example list view"};

        //define a new adapter
        //first parameter-context
        //second parameter- layout for the row
        //third parameter - id of the textveiw to which the data is written
        //forth - the array of data

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,values);

        //assign adapter to listveiw
        listView.setAdapter(adapter);

        //listview item click listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //list view clicked item index
                int itemPosition= position;

                // list view clicked item value
                String itemValue=(String) listView.getItemAtPosition(position);

                //show alert
                Toast.makeText(getApplicationContext(),"position:"+itemPosition+"ListItem:"+itemValue,Toast.LENGTH_LONG).show();
            }
        });
    }
}
