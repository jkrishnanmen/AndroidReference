package com.example.empressum.jake;

import android.app.Dialog;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.empressum.jake.FaceDetection.FaceDetection;
import com.example.empressum.jake.Gallery.GalleryOpenActivityUri;
import com.example.empressum.jake.expandableListView.ExpandableListViewMain;
import com.example.empressum.jake.recyclerView.Recycler_view;
import com.example.empressum.jake.service.service2Intent.IntentServiceActivity;
import com.example.empressum.jake.sharedPreference.SharedPref;
import com.example.empressum.jake.maps.Maps;
import com.example.empressum.jake.spinnerDynamic.LoginSuccessfull;
import com.example.empressum.jake.webService.json.JsonParsingWebService;

public class MainActivity extends AppCompatActivity {
    Intent intent1,intent2,intent3,intent4,intent5,intent6,intent7,intent8,intent9,intent10,intent11;
//    final Context context=this;
    Dialog dialog;
    final String tag="States Main Activity";

    private NfcAdapter nfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent1= new Intent(this,LoginSuccessfull.class);
        intent2=new Intent(this,Recycler_view.class);
        intent3=new Intent(this,FakeLogin.class);
        intent4=new Intent(this, SharedPref.class);
        intent5=new Intent(this,ExpandableListViewMain.class);
        intent6=new Intent(this,Maps.class);
        intent7=new Intent(this, IntentServiceActivity.class);
//        intent8=new Intent(this,OpenGalleryWithIntents.class);
        intent8=new Intent(this,GalleryOpenActivityUri.class);
        intent9=new Intent(this, FaceDetection.class);
        intent10=new Intent(this, JsonParsingWebService.class);
        intent11=new Intent(this,ListViewActivity.class);
        Button but=(Button) findViewById(R.id.but1);
        Button but1=(Button) findViewById(R.id.but2);


        nfcAdapter=NfcAdapter.getDefaultAdapter(this);
        if(nfcAdapter==null){
            Toast.makeText(this,"NFC NOT supported on this device",Toast.LENGTH_LONG).show();
            finish();
        }else if(!nfcAdapter.isEnabled()){
            Toast.makeText(this,"NFC NOT Enabled",Toast.LENGTH_LONG).show();
            finish();
        }

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast=Toast.makeText(getApplicationContext(),"Login Successfull",Toast.LENGTH_SHORT);
                toast.show();


                dialog=new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.custom);
                dialog.setTitle("Menu");

                TextView text=(TextView) dialog.findViewById(R.id.tv);
                text.setText("Choose from \n the following");

                Button dialogB= (Button) dialog.findViewById(R.id.but);
                Button rv=(Button) dialog.findViewById(R.id.but1);
                Button sqli=(Button) dialog.findViewById(R.id.but2);
                Button shpr=(Button) dialog.findViewById(R.id.but3);
                Button map=(Button) dialog.findViewById(R.id.but4);
                Button service=(Button) dialog.findViewById(R.id.ser);
                Button gal=(Button) dialog.findViewById(R.id.but5);
                Button face=(Button) dialog.findViewById(R.id.but7);
                Button ws=(Button) dialog.findViewById(R.id.but6);
                Button lv=(Button) dialog.findViewById(R.id.but8);
                dialogB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        startActivity(intent1);
                    }
                });
                rv.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view){
                        dialog.dismiss();
                        startActivity(intent2);
                    }
                });
                sqli.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view){
                        dialog.dismiss();
                        startActivity(intent3);
                    }
                });
                shpr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        startActivity(intent4);
                    }
                });
                map.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        startActivity(intent6);
                    }
                });
                service.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        startActivity(intent7);
                    }
                });
                gal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        startActivity(intent8);
                    }
                });
                face.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        startActivity(intent9);
                    }
                });
                ws.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        startActivity(intent10);
                    }
                });
                lv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        startActivity(intent11);
                    }
                });
                dialog.show();


            }
        });

        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent5);

            }
        });
        Log.d(tag,"OnCreate()");
    }
    protected void onRestart(){
        super.onRestart();
        Log.d(tag,"onRestart()");
    }
    protected void onStart() {
        super.onStart();
        Log.d(tag, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(tag, "onResume()");


        Intent intent = getIntent();
        String action = intent.getAction();

        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)) {
            Toast.makeText(this,
                    "onResume() - ACTION_TAG_DISCOVERED",
                    Toast.LENGTH_SHORT).show();

            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            if(tag == null){

                Toast.makeText(this,
                        "tag == null",
                        Toast.LENGTH_SHORT).show();

                //textViewInfo.setText("tag == null");
            }else{
                String tagInfo = tag.toString() + "\n";

                tagInfo += "\nTag Id: \n";
                byte[] tagId = tag.getId();
                tagInfo += "length = " + tagId.length +"\n";
                for(int i=0; i<tagId.length; i++){
                    tagInfo += Integer.toHexString(tagId[i] & 0xFF) + " ";
                }
                tagInfo += "\n";

                String[] techList = tag.getTechList();
                tagInfo += "\nTech List\n";
                tagInfo += "length = " + techList.length +"\n";
                for(int i=0; i<techList.length; i++){
                    tagInfo += techList[i] + "\n ";
                }
                Toast.makeText(this,
                        tagInfo,
                        Toast.LENGTH_SHORT).show();

                //textViewInfo.setText(tagInfo);
            }
        }else{
            Toast.makeText(this,
                    "onResume() : " + action,
                    Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d(tag, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(tag, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(tag, "onDestroy()");
    }

}
