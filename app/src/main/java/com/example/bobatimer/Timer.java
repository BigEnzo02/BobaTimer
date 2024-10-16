package com.example.bobatimer;

import android.os.CountDownTimer;
import android.widget.TextView;

public class Timer {
    private long millisecondsRemaining;
    private final TextView timerText;
    private final Notif notification;
    private final int min;
    private CountDownTimer countDownTimer;
    private int repeats = 0;

    public Timer(int minutes, TextView text, Notif notificationObject){
        //findViewById only works when called in MainActivity (something to do with setContentView?)
        //so it must be passed in as an already established TextView param
        timerText = text;
        min = minutes;
        notification = notificationObject;

        millisecondsRemaining = minutes * 60 * 1000L;
        this.startTimer();
    }
    public Timer(int minutes, TextView text, Notif notificationObject, int repeatUntil) {
        this(minutes, text, notificationObject);
        repeats = repeatUntil;
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
                notification.sendNotification(1, min, Notif.convertMessage(min));
                // if timer would finish in without overrunning minutes, schedule this again
                if ((repeats - min) > 0) {
                    repeats -= min;
                    millisecondsRemaining = Math.min(repeats, min) * 60 * 1000L;
                    cancel();
                    start();
                }
            }

        }.start(); //starts timer immediately

    }

    //updates clock every tick
    public void updateTimer() {
        if (millisecondsRemaining > 0) {
            int minutes = (int) millisecondsRemaining / 60000;
            int seconds = (int) millisecondsRemaining % 60000 / 1000;

            String timeRemainingText = minutes + ":";
            //ensures 2 digits in the seconds column
            if (seconds < 10) timeRemainingText += "0";

            timeRemainingText += seconds;

            timerText.setText(timeRemainingText);
            }
    }
    public void stopTimer() {
        countDownTimer.cancel();
    }
}
