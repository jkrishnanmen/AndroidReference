package com.example.empressum.jake.service.service1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

/**
 * Created by empressum on 25/8/16.
 */
public class ServiceClass extends Service{

    int runser;

    final class ThreadClass implements Runnable{

        int service_id;
        Intent intent;

        public ThreadClass(int service_id) {
            this.service_id = service_id;
        }

        public ThreadClass() {
            super();
        }

        @Override
        public void run() {

            synchronized (this){
                int i=0;
                while(i<10){
                    try{
                        wait(1500);
                        i++;
                        runser=i;
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
//        super.onDestroy();
        Toast.makeText(this,"Service Destroyed",Toast.LENGTH_LONG).show();
    }


    public IBinder onBind(Intent intent){

        return null;
    }

    private void sendBroadcast (boolean success){
        Intent intent=new Intent("gotit");
        intent.putExtra("getit",Integer.toString(runser));
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

}


