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
        Intent resultIntent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        NotificationManager notificationManager = null;

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            notificationManager = getSystemService(NotificationManager.class);
        }
        Notif notification = new Notif(notificationManager, stackBuilder, notificationManagerCompat, getApplicationContext());

        TimerPage newTimerPage = new TimerPage(numPots, notification);
        numPots++;
        newTimerPage.initTimer(findViewById(R.id.timer_90_text), findViewById(R.id.timer_60_text), findViewById(R.id.timer_10_text));
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