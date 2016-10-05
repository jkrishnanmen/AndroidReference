package com.example.empressum.jake.spinnerDynamic;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.empressum.jake.R;

import java.util.ArrayList;
import java.util.List;

public class LoginSuccessfull extends AppCompatActivity {
    Spinner spinner,spinner1;
    Context con=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_successfull);

        spinner= (Spinner) findViewById(R.id.spin);
        ArrayList<String> arraylist1=new ArrayList<String>();
        arraylist1.add("");
        arraylist1.add("ernakulam");
        arraylist1.add("chennai");
        arraylist1.add("kolkata");
        ArrayAdapter<String> adp= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,arraylist1);
        spinner.setAdapter(adp);
        spinner.setVisibility(View.VISIBLE);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i).toString()!=""){
                    String city = "The city is " + adapterView.getItemAtPosition(i).toString();
                    Toast.makeText(adapterView.getContext(), city, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });
    }



}
