package com.example.ashwini.notificationtest;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.TrafficStats;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;



public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private String TAG ="Firebase";

    @Override
    public void onMessageReceived(RemoteMessage rm) {


        Log.d(TAG, "From: " + rm.getFrom());


        if (rm.getData().size() > 0) {

            Log.d(TAG, "Message data payload: " + rm.getData().get("test"));

            try {
                Log.d(TAG, "startNotification");

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }

        }


    }


}
