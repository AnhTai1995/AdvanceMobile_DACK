package com.example.doancuoiki.tim_tro_dack.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.doancuoiki.tim_tro_dack.R;

public class Splash_start extends AppCompatActivity {

    private ProgressBar mProgress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        // Show the splash screen
        setContentView(R.layout.activity_splash_start);
        mProgress = (ProgressBar) findViewById(R.id.splashprogressBar);
        mProgress.getIndeterminateDrawable().setColorFilter(Color.parseColor("#FFFF00"), PorterDuff.Mode.MULTIPLY);

        // Start lengthy operation in a background thread
        new Thread(new Runnable() {
            public void run() {
                doWork();
                startApp();
                finish();
            }
        }).start();
    }

    private void doWork() {
        for (int progress=0; progress<100; progress+=10) {
            try {
                Thread.sleep(200);
                mProgress.setProgress(progress);
            } catch (Exception e) {
                e.printStackTrace();
                //  Timber.e(e.getMessage());
            }
        }
    }

    private void startApp() {
        Intent intent = new Intent(Splash_start.this, MainActivity.class);
        startActivity(intent);
    }
}
