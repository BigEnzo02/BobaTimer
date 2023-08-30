package com.example.bobatimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView timerText;
    private Button timerButton;

    private CountDownTimer countDownTimer;
    private long millisecondsRemaining = 20000; //20 seconds
    private boolean timerOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        timerText = findViewById(R.id.timer_text);
        timerButton = findViewById(R.id.timer_button);

        timerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                toggleTimer();
            }
        });

        //create new notification class
        //NotifPusher notif = new NotifPusher();
        //create channel and listener from button ID
        //notif.initNotif(timerButton);

    }
    public void toggleTimer() {
        if (timerOn){
            stopTimer();
        } else {
            startTimer();
        }
    }

    public void startTimer(){
        //creates a new countdowntimer w/ tick every second
        countDownTimer = new CountDownTimer(millisecondsRemaining, 1000){
            //updates l (remaining time in timer) every second
            @Override
            public void onTick(long l){
                millisecondsRemaining = l;
                updateTimer();
            }
            @Override
            public void onFinish(){

            }

        }.start(); //starts timer immediately

        timerButton.setText("STOP");
        timerOn = true;

    }
    public void stopTimer(){
        countDownTimer.cancel();
        timerButton.setText("START");
        timerOn = false;
    }

    //updates clock every tick
    public void updateTimer(){
        int minutes = (int) millisecondsRemaining / 60000;
        int seconds = (int) millisecondsRemaining % 60000 / 1000;

        String timeRemainingText = "" + minutes + ":";
        //ensures 2 digits in the seconds column
        if (seconds < 10){
            timeRemainingText += "0";
        }
        timeRemainingText += seconds;
        timerText.setText(timeRemainingText);
    }

}