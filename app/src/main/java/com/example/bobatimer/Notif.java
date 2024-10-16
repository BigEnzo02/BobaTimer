package com.example.bobatimer;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.TaskStackBuilder;

import java.util.HashMap;
import java.util.Map;

public class Notif extends Application {
    private static final Map<Integer, String> messageConversion = Map.of(10, "Time to stir your pot!", 60, "Your pot is done cooking!",
                                                                         90, "Boba is finished! Don't forget the sugar and enjoy :)");
    private static int curID = 0;

    private final NotificationManager nm;
    private final NotificationManagerCompat nmcompat;
    private final Context mainContext;

    public TaskStackBuilder stBuilder;

    public Notif(NotificationManager notificationManager, TaskStackBuilder stackBuilder, NotificationManagerCompat notificationManagerCompat, Context context){
        stBuilder = stackBuilder;
        nm = notificationManager;
        nmcompat = notificationManagerCompat;
        mainContext = context;
        createNotificationChannel();
    }
    
    //create a notification object with builder object and send it
    public void sendNotification(int t_num, int t_title, String msg) {
        //notification builder that contains all the information in the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mainContext, "bobaTimer")
                .setSmallIcon(R.drawable.boba)      //icon that appears in bar and next to notification
                .setContentTitle("Boba app")        //small title that appears above the notification
//                .setContentText(String.format("Your %d minute alarm on timer number %d is done!", t_title, t_num))      //text that appears on the notification
                .setContentText(msg)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)       //where it appears in the notification list
                .setAutoCancel(true);       //notification is removed when clicked on

//        System.out.println("past builder");

        PendingIntent resultPendingIntent =
                stBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(resultPendingIntent);

        nmcompat.notify(curID, builder.build());
        curID += 1;
        curID %= 1000;
    }

    //private notification channel constructor
    private void createNotificationChannel() {
        String id = "bobaTimer";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(id, "timerChannel", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Timer notification");

            nm.createNotificationChannel(channel);
        }
    }
    public static String convertMessage(int min) {
        return messageConversion.get(min) != null ? messageConversion.get(min) : "Your " + min + " minute timer is finished!";
    }
}
