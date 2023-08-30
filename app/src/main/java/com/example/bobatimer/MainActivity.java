package com.example.bobatimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create new notification class
        NotifPusher notif = new NotifPusher();
        //create channel and listener from button ID
        notif.initNotif(findViewById(R.id.bobaTimer));
    }
}