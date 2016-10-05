package com.example.empressum.jake.database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.empressum.jake.R;
import com.example.empressum.jake.database.DBArrayList;
import com.example.empressum.jake.database.UserSpace;

import java.util.ArrayList;

public class MultipleFieldsSQL extends AppCompatActivity {
    TextView tv;
    EditText user,pass,addr,ph;
    Button but1,but2;
    ArrayList<String> list;
    DBArrayList al;
    UserSpace userspace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_multiple_fields_sql);
        setContentView(R.layout.activity_fake_login);
        tv= (TextView) findViewById(R.id.desc);
        tv.setText("Enter Multiple fileds here");
        user=(EditText)findViewById(R.id.usr);
        pass=(EditText)findViewById(R.id.pass);
        user.setHint("Enter Text for Field 1 here");
        pass.setHint("Enter Text for Field 2 here");
        but1=(Button) findViewById(R.id.submit);
        but2=(Button) findViewById(R.id.pageswitcher);
        ph=(EditText) findViewById(R.id.phone);
        addr=(EditText) findViewById(R.id.addr);
        al=new DBArrayList(this,null,null,1);


        but2.setVisibility(View.GONE);
        list=new ArrayList<String>();

        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add(user.getText().toString());
                list.add(pass.getText().toString());
                list.add(ph.getText().toString());
                list.add(addr.getText().toString());

                //al.addUsers(list);

            }
        });
    }
}
