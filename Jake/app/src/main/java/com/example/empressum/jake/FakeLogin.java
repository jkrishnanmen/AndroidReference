package com.example.empressum.jake;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.empressum.jake.database.DBArrayList;
import com.example.empressum.jake.database.MultipleFieldsSQL;
import com.example.empressum.jake.database.Saver;

import java.util.ArrayList;

public class FakeLogin extends AppCompatActivity {
    EditText usr,pass,addr,ph;
    TextView tv;
    String usrname,passw,address,phone,temp,dbString,s1,s2,s3,s4,TAG;
    String[] values;
    Button but1,but2;
    DBArrayList dbal;
    Intent intent;
    ListView lv;
    ArrayAdapter<String> adapter;
    ArrayList<Saver> al;
    //UserSpace us;
    Saver us;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fake_login);
        temp="";
        addr=(EditText) findViewById(R.id.addr);
        ph=(EditText) findViewById(R.id.phone);
        intent=new Intent(FakeLogin.this,MultipleFieldsSQL.class);
        usr=(EditText) findViewById(R.id.usr);
        pass=(EditText) findViewById(R.id.pass);
        tv=(TextView) findViewById(R.id.tv);
        but1=(Button) findViewById(R.id.submit);
        but2=(Button) findViewById(R.id.pageswitcher);
        lv=(ListView)findViewById(R.id.lv);
        dbal=new DBArrayList(this,null,null,1);
        //UserSpace userSpace=new UserSpace("Jaya","Kinnan","Aksh","Eliz");
        //dbHandler.addUsers(userSpace);
        us = dbal.databaseToString();
        al=new ArrayList<Saver>();

        //us =new UserSpace();
//        us=new Saver();
//        TAG="ERROR";
//        s1=us.getName();
//        Log.v(TAG,"US.GETNAME="+us.getName());
//
//
        if(us!=null && us.getName()!=null) {



//            values = new String[]{us.getName().toString(), us.getPass().toString(), us.getAddress().toString(), us.getPhone().toString()
//
//            };
//            for(int i=0; i<values.length; i++)
//                Log.d("i value", values[i]);


            values = new String[] { us.getName(),us.getPass(),us.getAddress(),us.getPhone()
             };

            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1,values);
            lv.setAdapter(adapter);
//        printDatabase();

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //List View Clicked Item Position
                    int itemposition = position;

                    //List View Clicked Item Value
                    String itemvalue = (String) lv.getItemAtPosition(position);

                    //show Alert
                    Toast.makeText(getApplicationContext(), "Position=" + itemposition + "ListItem" + itemvalue, Toast.LENGTH_LONG);
                }
            });
        }

        else{

            Log.v(TAG,"NULL");
        }
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usrname=usr.getText().toString();
                passw=pass.getText().toString();
                address=addr.getText().toString();
                phone=ph.getText().toString();
                //UserSpace userSpace=new UserSpace(usrname,passw,address,phone);
                //Saver saver= new Saver()


                al.add( new Saver(usrname,passw,address,phone));
                dbal.addUsers(al);

            }
        });

        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

    }
    public void printDatabase() {
        dbString = us.toString();
        Toast.makeText(getApplicationContext(), dbString , Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onStop() {
        super.onStop();

       dbal.databaseToString();
    }
}


