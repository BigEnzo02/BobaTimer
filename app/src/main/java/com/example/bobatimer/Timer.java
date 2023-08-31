package com.example.bobatimer;

import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.annotation.IdRes;

public class Timer {
    private long millisecondsRemaining;
    private CountDownTimer countDownTimer;
    private TextView timerText;


    public Timer (int minutes, @IdRes int viewId){
        timerText = timerText.findViewById(viewId);
        millisecondsRemaining = minutes * 60000;
        startTimer();
        System.out.println(minutes + " Minute timer created");
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
        System.out.println(minutes + "minute timer: " + timeRemainingText);
    }
}
