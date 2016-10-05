package com.example.empressum.jake.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.empressum.jake.MainActivity;

/**
 * Created by empressum on 19/8/16.
 */
public class BootCompletedActivity extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent i=new Intent(context, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);

        Toast.makeText(context,"Booting Completed",Toast.LENGTH_LONG).show();
    }
}
