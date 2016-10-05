package com.example.empressum.jake.service.service2Intent;

import android.app.IntentService;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by empressum on 25/8/16.
 */
public class IntentServiceClass extends Service {
    public Intent intent;
    String TAG="INTENTSERVICECLASS";

    final class ThreadClass implements Runnable{

        int service_id;


        public ThreadClass(int service_id){
            this.service_id=service_id;
        }

        public ThreadClass(){
            super();
        }

        @Override
        public void run() {

            intent=new Intent();

            synchronized (this){
                int i=0;
                while(i<130){
                    try{
                        wait(1500);
                        i++;
                        intent.setAction("babablacksheep");
                        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                        intent.putExtra("timer",Integer.toString(i));
//                        Log.d(TAG,"intent.putExtra");
                        sendBroadcast(intent);
                    }
                    catch(InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                stopSelf(service_id);
            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        return super.onStartCommand(intent, flags, startId);
        Toast.makeText(this,"Service Started",Toast.LENGTH_LONG).show();

        Thread thread = new Thread(new ThreadClass(startId));
        thread.start();


        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"Service Destroyed",Toast.LENGTH_LONG).show();
//        stopSelf(service_id);
    }


    public IBinder onBind(Intent intent){

        return null;
    }

}
