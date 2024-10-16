package com.example.bobatimer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.TaskStackBuilder;

import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.LinkedList;
// TODO:
//  settings menu for time and stuff
//  boba recipe tab
//  lore?
//  running in background? once app is closed? maybe more than is needed idk
//  combine timers so only 1 countdown timer controls all three timers?
//  make pretty!
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.timer_button);

        // set timer click listener, so click starts newTimer()
        startButton.setOnClickListener(view -> newTimerPage());

    }

    public void newTimerPage(){
        Intent resultIntent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        NotificationManager notificationManager = null;

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            notificationManager = getSystemService(NotificationManager.class);
        }
        Notif notification = new Notif(notificationManager, stackBuilder, notificationManagerCompat, getApplicationContext());

        TimerPage newTimerPage = new TimerPage(notification);

        // create linear layout, as each timer page should be arranged vertically and centered
        LinearLayout layout = getLinearLayout();

        int timerNum = getNextTimerNum();
        layout.setTag(timerNum);        // tag is used to find correct number in sequence later
        addTimerLabel(layout, timerNum);
        // create timers
        TextView[] newTimers = new TextView[3];
        addTimers(layout, newTimers);
        addRemoveButton(layout, newTimerPage);
        // add the linearlayout to the timer
        LinearLayout totalLayout = findViewById(R.id.timerLayout);
        totalLayout.addView(layout);

        // start each timer
        newTimerPage.initTimer(newTimers[0], newTimers[1], newTimers[2]);
    }

    private @NonNull LinearLayout getLinearLayout() {
        LinearLayout layout = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(500, 800);
        params.gravity = Gravity.CENTER;
        layout.setLayoutParams(params);
        layout.setPadding(5, 5, 5, 5);
        layout.setOrientation(LinearLayout.VERTICAL);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            layout.setHorizontalGravity(LinearLayout.TEXT_ALIGNMENT_GRAVITY);
        } else {
            layout.setHorizontalGravity(1);
        }
        return layout;
    }
    private void addTimerLabel(LinearLayout layout, int num) {
        TextView label = new TextView(getApplicationContext());
        label.setGravity(Gravity.CENTER_HORIZONTAL);
        // there is some warning here that I don't understand so I am just ignoring it
        label.setText(String.format("%s%d", getString(R.string.timerLabel), num));
        layout.addView(label);
    }
    private int getNextTimerNum() {
        LinearLayout timerLayout = findViewById(R.id.timerLayout);
        if (timerLayout.getChildCount() == 0) return 1;

        // get all used numbers, sort them and return the lowest free one
        //  probably not technically optimal but the amount of timers at any given point will be small enough it does not matter
        LinkedList<Integer> usedNums = new LinkedList<>();
        for (int i = 0; i < timerLayout.getChildCount(); i++) {
            usedNums.add((Integer) (timerLayout.getChildAt(i).getTag()));
        }
        Collections.sort(usedNums);
        int min = 1;
        for (Integer num : usedNums) {
            if (num == min) min++;
            else return min;
        }
        return min;
    }
    private void addRemoveButton(LinearLayout layout, TimerPage timerPage) {
        Button removeTimer = new Button(getApplicationContext());
        removeTimer.setLayoutParams(new LinearLayout.LayoutParams(300, 100));
        removeTimer.setOnClickListener(view -> {
            LinearLayout allTimers = findViewById(R.id.timerLayout);
            allTimers.removeView(layout);
            timerPage.cancelTimers();
        });
        removeTimer.setText(R.string.removeButton);

        layout.addView(removeTimer);
    }
    private void addTimers(LinearLayout layout, TextView[] newTimers) {
        for (int i = 0; i < 3; i++) {
            newTimers[i] = new TextView(getApplicationContext());
            newTimers[i].setTextSize(20);
            newTimers[i].setTextColor(Color.BLACK);
            newTimers[i].setGravity(Gravity.CENTER_HORIZONTAL);

            layout.addView(newTimers[i]);
        }
    }
}