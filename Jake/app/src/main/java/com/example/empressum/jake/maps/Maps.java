package com.example.empressum.jake.maps;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.empressum.jake.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Maps extends AppCompatActivity  {
   LatLng lng;
    Button btmap,btsat,bthyb,btter,custom;
    private GoogleMap googleMap;
    CameraPosition cp;
    Dialog dialog;
    float zoom,bear,tilt,lat,longt;
    EditText lt,ln,br,zm,tt;
    TextView tv;
    MarkerOptions bla;
    CameraPosition SEATTLE=CameraPosition.builder().target(new LatLng(47.6204,-122.2491)).zoom(10).bearing(0).tilt(45).build();
    MarkerOptions markerOptions;
    MarkerOptions markerOptions1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        btmap=(Button) findViewById(R.id.btnMap);
        btsat=(Button) findViewById(R.id.btnSat);
        bthyb=(Button) findViewById(R.id.btnHybrid);
        btter=(Button) findViewById(R.id.btnTer);
        custom=(Button) findViewById(R.id.custom);

        markerOptions =new MarkerOptions().position(new LatLng(47.489805,-122.120502));
        markerOptions1 =new MarkerOptions().position(new LatLng(47.978748,-122.202001));

        btmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(mapReady)
                    googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
        });

        btsat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(mapReady)
                    googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            }
        });

        bthyb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(mapReady)
                    googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            }
        });

        btter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(mapReady)
                    googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            }
        });



        try{
            if(googleMap==null){
                googleMap=((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
            }
            googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            Marker TP= googleMap.addMarker(new MarkerOptions().position(lng).title("FindMapsHere"));
            Marker marker=googleMap.addMarker(new MarkerOptions());
        }
        catch (Exception e){
            e.printStackTrace();
        }

        googleMap.addMarker(markerOptions);
        googleMap.addMarker(markerOptions1);
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(SEATTLE));


        custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                googleMap.setMyLocationEnabled(true);

                dialog=new Dialog(Maps.this);
                dialog.setContentView(R.layout.maplayout1);


                Button don=(Button) dialog.findViewById(R.id.donee);
                lt=(EditText) dialog.findViewById(R.id.np);
                ln=(EditText) dialog.findViewById(R.id.np1);
                br=(EditText) dialog.findViewById(R.id.np2);
                zm=(EditText) dialog.findViewById(R.id.np3);
                tt=(EditText) dialog.findViewById(R.id.np4);
                tv=(TextView) dialog.findViewById(R.id.tv5);

//                lt.setText("0");
//                ln.setText("0");
//                br.setText("0");
//                zm.setText("0");
//                tt.setText("0");

                LatLng lng1=new LatLng(00,00);
                lng1=googleMap.getCameraPosition().target;

                lt.setText(Double.toString(lng1.latitude));
                ln.setText(Double.toString(lng1.longitude));
                br.setText(Float.toString(googleMap.getCameraPosition().bearing));
                zm.setText(Float.toString(googleMap.getCameraPosition().zoom));
                tt.setText(String.valueOf(googleMap.getCameraPosition().tilt));

//                 String s =googleMap.getProjection().toString();
                //tv.setText(googleMap.getProjection().toString());
//                float f=googleMap.getMaxZoomLevel();
//                tv.setText(String.valueOf(f));
//                tv.setText("hai");
                String t=googleMap.getCameraPosition().toString();




                don.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(lt.getText().toString().trim().length()!=0&&ln.getText().toString()!=null&&br.getText().toString()!=null&&tt.getText().toString()!=null&&zm.getText().toString()!=null){
                            lat=Float.parseFloat(lt.getText().toString()); //zoom,bear,tilt,lat,longt;
                            longt=Float.parseFloat(ln.getText().toString());
                            bear=Float.parseFloat(br.getText().toString());
                            tilt=Float.parseFloat(tt.getText().toString());
                            zoom=Float.parseFloat(zm.getText().toString());

                            lng = new LatLng(lat,longt);
                            if(lat!=0 && longt!=0 && bear!=0 && tilt!=0 && zoom!=0) {
                                CameraPosition cp1 = CameraPosition.builder().target(lng).zoom(zoom).bearing(bear).tilt(tilt).build();
                                googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cp1));
                                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cp1), 15000, null);
                            }
                            dialog.dismiss();
                        }else{
                            tv.setText("Invalid Parameters , Please try again");
                        }

                    }
                });

                dialog.show();





//                lat=Integer.valueOf(ln.getText().toString());
            }
        });


    }
    public void onMapReady(GoogleMap googleMap){
        googleMap.addMarker(markerOptions);
        googleMap.addMarker(markerOptions1);
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(SEATTLE));
    }

}
