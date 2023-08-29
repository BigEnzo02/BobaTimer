package com.example.bobatimer;

import androidx.core.app.NotificationCompat;

public class notifPusher {
    public void sendNotif(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "lemubitA")
                .setSmallIcon(R.drawable.boba)
                .setContentTitle("Boba App")
                .setContentText("Your timer is done!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

    }

    private void  createNotificationChannel(){
        
    }
}
