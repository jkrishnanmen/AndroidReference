package com.example.empressum.jake.sharedPreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.empressum.jake.R;

public class SharedPref extends AppCompatActivity {

    EditText ed1, ed2, ed3;
    Button sav;
    SharedPreferences pref;
    TextView tv;
    final String MyPref = "MyPref", Name = "nameKey", Phone = "phoneKey", Email = "emailKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_pref);
        ed1 = (EditText) findViewById(R.id.et);
        ed2 = (EditText) findViewById(R.id.et1);
        ed3 = (EditText) findViewById(R.id.et2);

        tv=(TextView) findViewById(R.id.tv);

        sav = (Button) findViewById(R.id.but);

        pref = getSharedPreferences(MyPref, Context.MODE_PRIVATE);

        sav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = ed1.getText().toString();
                String ph = ed2.getText().toString();
                String e = ed3.getText().toString();

                SharedPreferences.Editor editor = pref.edit();

                editor.putString(Name, n);
                editor.putString(Phone, ph);
                editor.putString(Email, e);
                editor.commit();
                Toast.makeText(SharedPref.this, "Thanks", Toast.LENGTH_LONG).show();
                String st=(pref.getString(Name,""));
                tv.setText(st);

            }
        });
    }
//
//    public boolean onCreateOptionsMenu(Menu menu) {
//        //inflate the menu;this adds items to the action bar if it is present
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

//    public boolean onOptionsItemSelected(MenuItem item) {
//        //handle action bar item clicks here.the action bar will
//        //automaticlly handle clicks on the home up button so long
//        //as you specify a parent activity in androidmanifest.xml
//        int id = item.getItemId();
//        //noinspection SimpoifiableIfStatement
//        //if (id == R.id.action_settings) {
//          //  return true;
//        //}
//
//        return super.onOptionsItemSelected(item);
//    }
}
