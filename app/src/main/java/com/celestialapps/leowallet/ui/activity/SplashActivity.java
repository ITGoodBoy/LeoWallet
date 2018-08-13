package com.celestialapps.leowallet.ui.activity;

import android.os.Bundle;

import com.celestialapps.leowallet.R;

import java.util.concurrent.TimeUnit;

public class SplashActivity extends BaseActivity {

    private Thread mThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (mThread == null) {
            mThread = new Thread(() -> {
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(3));
                    startActivity(LoginActivity.class);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            mThread.start();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mThread != null) {
            mThread.interrupt();
            mThread = null;
        }
    }
}
