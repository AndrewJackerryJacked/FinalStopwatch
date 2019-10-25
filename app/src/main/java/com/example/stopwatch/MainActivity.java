package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;

import android.os.CountDownTimer;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Look up the log class for Android.
    //List all the levels of logging and how they're used.
    private boolean is_counting = false;
    private long last_number = SystemClock.elapsedRealtime();
    private Button bStart_stop;
    private Button bReset;
    private Chronometer chTime;
    private boolean count;
    public static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wireWidgets();
        Listeners();
        Log.d(TAG, "onResume;");
    }

    private void wireWidgets() {
        bStart_stop = findViewById(R.id.button_main_stop);
        bStart_stop.setText("Start");
        bReset = findViewById(R.id.button_main_start);
        chTime = findViewById(R.id.chronometer_main_timer);
        chTime.stop();
        last_number = SystemClock.elapsedRealtime();
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart;");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.d(TAG, "onResume;");
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        Log.d(TAG, "onPause;");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.d(TAG, "onDestroy;");
    }

    private void Listeners(){
        bStart_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(is_counting){
                    chTime.stop();
                    last_number = SystemClock.elapsedRealtime();
                    bStart_stop.setText("Start");
                    is_counting = false;
                }
                else if(!count){
                    chTime.setBase(chTime.getBase() + (SystemClock.elapsedRealtime() - last_number));
                    chTime.start();
                    bStart_stop.setText("Stop");
                    count = true;
                }
            }
        });
        bReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chTime.setBase(SystemClock.elapsedRealtime());
                last_number = SystemClock.elapsedRealtime();
                chTime.stop();
                is_counting = false;
                bStart_stop.setText("Start");
            }
        });
    }
}
