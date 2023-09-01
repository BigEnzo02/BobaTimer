package com.example.bobatimer;

import android.os.CountDownTimer;
import android.widget.TextView;

public class TimerPage {

    int potNum;
    private Timer timer90, timer60, timer10;

    TimerPage(int num){
        potNum = num;
    }
    public void initTimer(TextView timerLong, TextView timerMed, TextView timerShort){
        timer90 = new Timer(90, timerLong);
        timer60 = new Timer(60, timerMed);
        timer10 = new Timer(10, timerShort);
    }

}

