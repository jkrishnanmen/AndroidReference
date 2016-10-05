package com.example.empressum.jake.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * Created by empressum on 18/8/16.
 */
public class MyPhoneReceiver extends BroadcastReceiver {
    @Override

    public void onReceive(Context context, Intent intent) {
        Bundle extras= intent.getExtras();
        if(extras!=null){
            String state=extras.getString(TelephonyManager.EXTRA_STATE);
            if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                String phoneNumber=extras.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                Toast.makeText(context,"received call",Toast.LENGTH_LONG).show();
                Vibrator vibrate=(Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                vibrate.vibrate(2000);
            }
        }
    }
}
