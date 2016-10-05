package com.example.empressum.jake.webService.json;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

//package info.androidhive.jsonparsing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.empressum.jake.MainActivity;
import com.example.empressum.jake.R;
import com.example.empressum.jake.webService.ServiceHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class JsonParsingWebService extends AppCompatActivity{

    private String TAG= MainActivity.class.getSimpleName();

    private ProgressDialog pDialog;

    //URL to get contacts JSON
    private static String url="http://api.androidhive.info/contacts/";

    //JSON Node names
//    private static final String
//            TAG_CONTACTS="contacts",
//    TAG_ID="id",
//    TAG_NAME="name",
//    TAG_EMAIL="email",
//    TAG_ADDRESS="address",
//    TAG_GENDER="gender",
//    TAG_PHONE="phone",
//    TAG_PHONE_MOBILE="mobile",
//    TAG_PHONE_HOME="home",
//    TAG_PHONE_OFFICE="office";
//
//    //contacts JSONArray
//    JSONArray contacts=null;
//
//    //hashmap for LIstView
    ArrayList<HashMap<String,String>> contactList;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_parsing_web_service);

        contactList =new ArrayList<>();
        lv=(ListView) findViewById(R.id.list);

        //calling async task to get json
        new GetContacts().execute();
    }

    //async task class to get json by making http call

    private class GetContacts extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //showing progress dialog
            pDialog = new ProgressDialog(JsonParsingWebService.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            //Creating service handler class instance
            HttpHandler sh= new HttpHandler();

            //Making a request to url and getting response
            String jsonStr= sh.makeServiceCall(url);

            Log.d("Response from url: ",jsonStr);
            if(jsonStr!=null){
                try{
                    JSONObject jsonObj= new JSONObject(jsonStr);
                   // Getting Json Array node
                    JSONArray contacts= jsonObj.getJSONArray("contacts");

                    //looping through all contacts
                    for(int i=0;i<contacts.length();i++){
                        JSONObject c= contacts.getJSONObject(i);

                        String id=c.getString("id"),
                                name=c.getString("name"),
                                email=c.getString("email"),
                                address=c.getString("address"),
                                gender=c.getString("gender");

                        //phone node is JSON Object
                        JSONObject phone= c.getJSONObject("phone");
                        String mobile=phone.getString("mobile");
                        String home=phone.getString("home");
                        String office= phone.getString("office");

                        //tmp hashmap for single contact
                        HashMap<String,String> contact= new HashMap<>();

                        //adding each child node to HashMap key=> value
                        contact.put("id",id);
                        contact.put("name",name);
                        contact.put("email",email);
                        contact.put("mobile",mobile);

                        //adding contact to contact list
                        contactList.add(contact);
                    }
                } catch(final JSONException e){
                    Log.e(TAG,"Json Parsing error: "+e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " +e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });
//                    e.printStackTrace();
                }
            }else {
//                Log.e("ServiceHandler","Couldn't get any data from the url");
                Log.e(TAG,"Couldnt get json from server.");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),
                                        "couldnt get json from server.Check logcat for possible errors",Toast.LENGTH_LONG).show();
                            }
                        });
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            super.onPostExecute(result);
            if(pDialog.isShowing())
                pDialog.dismiss();

            //updating parsed JSON data into Listview

//            ListAdapter adapter = new SimpleAdapter(

            ListAdapter adapter= new SimpleAdapter(
                    JsonParsingWebService.this,contactList,R.layout.list_item,new String[]{"name","email","mobile"}, new int[]{R.id.name,R.id.email,R.id.mobile}
            );
            lv.setAdapter(adapter);

//            setListAdapter(adapter);
        }
    }
}
