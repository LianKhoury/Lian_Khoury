package com.example.liankhoury;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Receiver extends BroadcastReceiver {
    @Override
    public void onReceive (Context context, Intent intent) {
        Intent service = new Intent(context,MyIntentService.class);
        context.startService(service);
    }
}
