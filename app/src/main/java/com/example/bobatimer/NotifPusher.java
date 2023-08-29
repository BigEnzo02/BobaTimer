package com.example.bobatimer;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotifPusher extends AppCompatActivity {
    public void initNotif(Button notificationButton){
        createNotificationChannel();


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "bobaTimer")
                .setSmallIcon(R.drawable.boba)
                .setContentTitle("Boba App")
                .setContentText("Your timer is done!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        notificationButton.setOnClickListener(v -> {notificationManager.notify(100, builder.build());});

    }

    private void  createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "timerChannel";
            String desc = "Channel to send notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("bobaTimer", name, importance);
            channel.setDescription(desc);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
