package com.example.bobatimer;

import android.os.CountDownTimer;
import android.widget.TextView;

public class TimerPage {

    int potNum;
    private Timer timer90, timer60, timer10;

    private Notif notification;
    TimerPage(int num, Notif notificationObject){
        potNum = num;
        notification = notificationObject;
    }
    public void initTimer(TextView timerLong, TextView timerMed, TextView timerShort){
        timer90 = new Timer(90, timerLong, notification);
        timer60 = new Timer(60, timerMed, notification);
        timer10 = new Timer(2, timerShort, notification);
    }

}

