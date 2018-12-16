package com.example.poweruser.tashuapp;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class CommstateListener extends PhoneStateListener {
    protected TelephonyManager telephonyManager;
    protected Context context;
    public int nRssi;

    public CommstateListener(TelephonyManager telephonyManager, Context context) {
        this.telephonyManager = telephonyManager;
        this.context = context;
    }

    @Override
    public void onSignalStrengthsChanged(SignalStrength signalStrength) {
        super.onSignalStrengthsChanged(signalStrength);
        int nPhoneType = telephonyManager.getPhoneType();
        if (nPhoneType == TelephonyManager.PHONE_TYPE_GSM)
            nRssi = signalStrength.getGsmSignalStrength();
        else if (nPhoneType == TelephonyManager.PHONE_TYPE_CDMA)
            nRssi = signalStrength.getCdmaDbm();
        //Toast.makeText(context, "RSSI = " + nRssi, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        super.onCallStateChanged(state, incomingNumber);
        switch (state) {
            case TelephonyManager.CALL_STATE_RINGING:
                Toast.makeText(context, "Ringing: " + incomingNumber, Toast.LENGTH_SHORT).show();
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                Toast.makeText(context, "Offhook: " + incomingNumber, Toast.LENGTH_SHORT).show();
                break;
            case TelephonyManager.CALL_STATE_IDLE:
                Toast.makeText(context, "Idle", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
