package com.example.bobatimer;

import android.os.CountDownTimer;
import android.widget.TextView;

public class TimerPage {
    private TextView timer90Text, timer60Text, timer10Text;
    private CountDownTimer countDownTimer;
    private long millisecondsRemaining = 5000; //5 seconds


    public void main(String[] args){
        timer90Text = findViewById(R.id.timer_90_text);
        timer60Text = findViewById(R.id.timer_60_text);
        timer10Text = findViewById(R.id.timer_10_text);
    }

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
            //send notification
            //MainActivity.sendNotification();
        }

        timeRemainingText += seconds;

        timerText.setText(timeRemainingText);
    }


}
