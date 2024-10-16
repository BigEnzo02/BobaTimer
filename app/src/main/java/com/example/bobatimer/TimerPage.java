package com.example.bobatimer;

import android.os.CountDownTimer;
import android.widget.TextView;

public class TimerPage {
    // not currently used but it will be once settings are added!
    public static final int repeating = 10;
    public static final int cooking = 60;

    private Timer timer90, timer60, timer10;
    private final Notif notification;

    public TimerPage(Notif notificationObject){
        notification = notificationObject;
    }

    public void initTimer(TextView timerLong, TextView timerMed, TextView timerShort){
        timer90 = new Timer(90, timerLong, notification);
        timer60 = new Timer(60, timerMed, notification);
        timer10 = new Timer(10, timerShort, notification, 60);
    }
    public void cancelTimers() {
        timer90.stopTimer();
        timer60.stopTimer();
        timer10.stopTimer();
    }

}

