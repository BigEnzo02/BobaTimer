package com.example.bobatimer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.TaskStackBuilder;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;

import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {
    private Button startButton;
    int numPots = 1;

    float x1, x2, y1, y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.timer_button);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newTimer();
            }
        });

    }

    public void newTimer(){
        System.out.println("new Timer Added");
        TimerPage newTimerPage = new TimerPage(numPots);
        numPots++;
        newTimerPage.initTimer(findViewById(R.id.timer_90_text), findViewById(R.id.timer_60_text), findViewById(R.id.timer_10_text));
    }

    //create a notification object with builder object and send it
    public void sendNotification(int t_num, String t_title) {
        String id = createNotificationChannel();


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, id)
                .setSmallIcon(R.drawable.boba)      //icon that appears in bar and next to notification
                .setContentTitle("Boba App")        //small title that appears above the notification
                .setContentText("Your " + t_title + " alarm on timer number " + t_num + " is done!")      //text that appears on the notification
                .setContentText("Your timer is done!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)       //where it appears in the notification list
                .setAutoCancel(true);       //notification is removed when clicked on

        //declare intent to open app when clicked on
        Intent resultIntent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(resultPendingIntent);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(99, builder.build());

    }

    //private notification channel constructor
    private String createNotificationChannel() {
        String id = "bobaTimer";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(id, "timerChannel", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Timer notification");

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
            return id;
        }
        return id;
    }

    public boolean onTouchEvent(MotionEvent touchEvent){
        switch(touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1= touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2= touchEvent.getY();
                if(x2 < x1){
                    startActivity(new Intent(MainActivity.this, TestSwipeActivityRight.class));
                } /*else if (x2 > x1){
                    startActivity(new Intent(MainActivity.this, TestSwipeActivityRight.class));
                }*/
                break;
        }
        return false;
    }
}