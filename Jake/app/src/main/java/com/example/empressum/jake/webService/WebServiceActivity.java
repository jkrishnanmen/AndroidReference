package com.example.empressum.jake.webService;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.empressum.jake.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class WebServiceActivity extends AppCompatActivity {
    EditText username,password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_service);
        password=(EditText) findViewById(R.id.pass);
        username=(EditText) findViewById(R.id.usr);
        login= (Button) findViewById(R.id.don);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=username.getText().toString();
                String s2=password.getText().toString();
                new ExecuteTask().execute(s1,s2);
            }
        });
    }

    class ExecuteTask extends AsyncTask<String,Integer,String>{
        @Override
        protected String doInBackground(String... params) {
            String res= PostData(params);
            return res;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
//            Toast.makeText(getApplicationContext(),result,3000).show();
        }
    }

    public String PostData(String[] valuse){
        String s="";
        try{
            HttpClient httpClient= new DefaultHttpClient();
            HttpPost httpPost= new HttpPost("http://10.0.0.8:7777//HttpPostServlet/servlet/Login");

//            List<NameValuepair> list= new ArrayList<NameValuePair>();
//            list.add(new BasicNameValuePair("name",valuse[0]));
//            list.add(new BasicNameValuePair("pass",valuse[1]));
//            httpPost.setEntity(new UrlEncodedFormEntity(list));
            HttpResponse httpResponse= httpClient.execute(httpPost);

//            HttpEntity httpEntity=httpResponse.getIntity();
            s= readResponse(httpResponse);
        }

        catch(Exception e){

        }
        return s;
    }

    public String readResponse(HttpResponse res){
        InputStream is=null;
        String return_text="";

//        try{
//            is=res.getEntity().getContent();
//            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(is));
//            String line="";
//            StringBuffer sb= new StringBuffer();
////            while((line=bufferedReader.readLine()!=null)){
//                sb.append(line);
//            }
//            return_text=sb.toString();
//        }catch(Exception e){
//
//        }
        return return_text;
    }


}
