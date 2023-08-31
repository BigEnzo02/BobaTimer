package com.example.bobatimer;

import android.os.CountDownTimer;
import android.widget.TextView;

public class TimerPage {
    private Timer timer90, timer60, timer10;
    public void initTimer(){
        //initialize the 3 timers and start them
        System.out.println("creating 3 timers");
        timer90 = new Timer(90, R.id.timer_90_text);
        System.out.println("timer created!");
//        timer60 = new Timer(60, R.id.timer_60_text);
//        timer10 = new Timer(10, R.id.timer_10_text);
        //after 90 mins close the page
        //ToDo: add button to close a timer page
    }
}
