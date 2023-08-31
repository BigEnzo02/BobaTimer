package com.example.bobatimer;

import android.os.CountDownTimer;
import android.widget.TextView;

public class TimerPage {
    private Timer timer90, timer60, timer10;
    public void initTimer(TextView timerLong, TextView timerMed, TextView timerShort){
        //initialize the 3 timers and start them
        timer90 = new Timer(90, timerLong);
        timer60 = new Timer(60, timerMed);
        timer10 = new Timer(10, timerShort);
        //after 90 mins close the page
        //ToDo: add button to close a timer page
    }
}
