package com.example.empressum.jake.layoutDemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.empressum.jake.R;

public class Weight extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
        Intent i2=getIntent();
        String gs=i2.getStringExtra("bla");

        Toast.makeText(this,gs,Toast.LENGTH_LONG).show();
    }
}
