package com.example.bobatimer;

import android.app.Activity;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.TaskStackBuilder;

public class Notif extends Application {
    public static TaskStackBuilder stBuilder;

    private NotificationManager nm;

    private NotificationManagerCompat nmcompat;

    private Context mainContext;

    public Notif(NotificationManager notificationManager, TaskStackBuilder stackBuilder, NotificationManagerCompat notificationManagerCompat, Context context){
        stBuilder = stackBuilder;
        nm = notificationManager;
        nmcompat = notificationManagerCompat;
        mainContext = context;
    }
    
    //create a notification object with builder object and send it
    public void sendNotification(int t_num, int t_title) {
        System.out.println("starting");
        String id = createNotificationChannel();
        System.out.println("past channel");

        //notification builder that contains all the information in the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mainContext, id)
                .setSmallIcon(R.drawable.boba)      //icon that appears in bar and next to notification
                .setContentTitle("Boba App")        //small title that appears above the notification
                .setContentText("Your " + t_title + " minute alarm on timer number " + t_num + " is done!")      //text that appears on the notification
                .setContentText("Your timer is done!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)       //where it appears in the notification list
                .setAutoCancel(true);       //notification is removed when clicked on

        System.out.println("past builder");

        PendingIntent resultPendingIntent =
                stBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(resultPendingIntent);

        nmcompat.notify(99, builder.build());

    }

    //private notification channel constructor
    private String createNotificationChannel() {
        String id = "bobaTimer";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(id, "timerChannel", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Timer notification");

            nm.createNotificationChannel(channel);
            return id;
        }
        return id;
    }
}
