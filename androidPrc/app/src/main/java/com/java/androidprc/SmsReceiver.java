package com.java.androidprc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.*;

public class SmsReceiver extends BroadcastReceiver {
    private static final String TAG = "SMS Receiver";
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        Log.d(TAG, "onReceiver() 호출됨");

        Bundle bundle = intent.getExtras();
        SmsMessage[] messages =  parseSmsMessage(bundle);


        if (messages.length > 0) {
            String sender = messages[0].getOriginatingAddress();
            Log.d(TAG, "sender : " + sender);

            String contents = messages[0].getMessageBody().toString();
            Log.d(TAG, "contents : " + contents);

            Date receiveDate = new Date(messages[0].getTimestampMillis());
            Log.d(TAG, "receiveDate : " + receiveDate);

            sendToActivity(context, sender, contents, receiveDate);
        }
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void sendToActivity(Context context, String sender, String contents, Date receiveDate) {
        Intent intent = new Intent(context, SmsActivity.class);
        intent.addFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_SINGLE_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("sender", sender);
        intent.putExtra("contents", contents);
        intent.putExtra("receiveDate", format.format(receiveDate));

        context.startActivity(intent);
    }

    private SmsMessage[] parseSmsMessage(Bundle bundle) {
        Object[] objs = (Object[]) bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[objs.length];

        for (int i = 0; i< objs.length; i++) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                String format = bundle.getString("format");
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i], format);
            }
            else {
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i]);
            }
        }
        return messages;
    }
}
