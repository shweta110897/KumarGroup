package com.example.kumarGroup;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootupReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1  =new Intent(context,MainActivity.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent1);
    }
}
