package com.example.poweruser.tashuapp;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class PhoneCallService extends Service {
    protected PhoneCallReceiver phoneCallReceiver;  // Outgoing
    protected TelephonyManager telephonyManager;

    public PhoneCallService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        phoneCallReceiver = new PhoneCallReceiver();
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_NEW_OUTGOING_CALL);
        registerReceiver(phoneCallReceiver, intentFilter);
        intentFilter = new IntentFilter(Intent.ACTION_CALL_BUTTON);
        registerReceiver(phoneCallReceiver, intentFilter);
        telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service started.", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service stopped.", Toast.LENGTH_SHORT).show();
        unregisterReceiver(phoneCallReceiver);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
