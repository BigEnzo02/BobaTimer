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

public class MainActivity extends AppCompatActivity {
    private Button startButton;

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
        TimerPage newTimerPage = new TimerPage();
    }

<<<<<<< Updated upstream
    public void startTimer() {
        //creates a new countdowntimer w/ tick every second
        countDownTimer = new CountDownTimer(millisecondsRemaining, 1000) {
            //updates l (remaining time in timer) every second
            @Override
            public void onTick(long l) {
                millisecondsRemaining = l;
                updateTimer();
            }

            @Override
            public void onFinish() {

            }

        }.start(); //starts timer immediately

        timerButton.setText(R.string.stop);
        timerOn = true;
    }

    public void stopTimer() {
        countDownTimer.cancel();
        timerButton.setText(R.string.start);
        timerOn = false;
    }

    //updates clock every tick
    public void updateTimer() {
        int minutes = (int) millisecondsRemaining / 60000;
        int seconds = (int) millisecondsRemaining % 60000 / 1000;

        String timeRemainingText = "" + minutes + ":";
        //ensures 2 digits in the seconds column
        if (seconds < 10) {
            timeRemainingText += "0";
        }
        //timer is up
        if (seconds <= 0){
            sendNotification();
        }

        timeRemainingText += seconds;
        timerText.setText(timeRemainingText);
    }
=======
>>>>>>> Stashed changes

    //create a notification object with builder object and send it
    public void sendNotification() {
        String id = createNotificationChannel();



        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, id)
                .setSmallIcon(R.drawable.boba)      //icon that appears in bar and next to notification
                .setContentTitle("Boba App")        //small title that appears above the notification
                .setContentText("Your timer is done!")      //text that appears on the notification
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
}