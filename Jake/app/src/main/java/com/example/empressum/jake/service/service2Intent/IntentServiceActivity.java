package com.example.empressum.jake.service.service2Intent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.empressum.jake.R;

public class IntentServiceActivity extends AppCompatActivity {
    TextView tv;
    Intent intent;
    Button start,stop;
    Boolean check=false;
    private BroadcastReceiver m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        start=(Button) findViewById(R.id.stser);
        stop=(Button) findViewById(R.id.ssser);
        intent=new Intent(this,IntentServiceClass.class);
        tv=(TextView) findViewById(R.id.tv);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(check==false)
                startService(intent);
//                Toast.makeText(getApplicationContext(),"StartService",Toast.LENGTH_LONG).show();

            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                check=false;
                stopService(intent);
//                Toast.makeText(getApplicationContext(),"StopService",Toast.LENGTH_LONG).show();
                Intent intent=new Intent();
                intent.setAction("stopstop");
                intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
//                intent.putExtra("Stop","Stop");
                sendBroadcast(intent);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter=new IntentFilter("babablacksheep");
        m=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                check=true;
                String tag="IntentServiceActivity";
                String s=intent.getStringExtra("timer");
                Log.d(tag,s);
                tv.setText(s);
            }
        };
        this.registerReceiver(m,intentFilter);
    }

    protected void onPause(){
        super.onPause();
//        unregisterReceiver(m);

    }

    protected void onStop(){
        super.onStop();
        unregisterReceiver(m);
    }

//        public class MyBroadcastReceiver extends BroadcastReceiver{
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String s =intent.getStringExtra("Timer");
//            //tv.setText(s);
//        }
//    }
}

