package com.example.ashwini.notificationtest;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
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

            if (rm.getNotification() != null) {
                Log.d(TAG, "Message Notification Body: " + rm.getNotification().getBody());
            }

            try {
                Log.i("NextActivity", "startNotification");

                // Sets an ID for the notification
                int mNotificationId = 001;

                Notification mBuilder =
                        new NotificationCompat.Builder(this)
                                .setSmallIcon(R.drawable.ic_stat_name)
                                .setContentTitle(rm.getFrom()).build();


                mBuilder.contentIntent = PendingIntent.getActivity(this, 0,
                        new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }

        }


    }


}
