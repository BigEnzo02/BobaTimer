package com.example.bobatimer;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.TimerTask;

public class Timer {

    Timer timer = new Timer();
    TimerTask t = new TimerTask() {
        @Override
        public void run() {

            System.out.println("1");
        }
    };

}
