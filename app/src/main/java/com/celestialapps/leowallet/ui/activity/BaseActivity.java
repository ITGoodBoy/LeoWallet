package com.celestialapps.leowallet.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.celestialapps.leowallet.constant.ExtraConstants;

@SuppressLint("Registered")
public class BaseActivity extends MvpAppCompatActivity {

    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (mSharedPreferences == null) {
            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        }

    }

    public void showSnackBarMessage(String message) {
        Snackbar
                .make(getWindow().getDecorView().getRootView(), message, Snackbar.LENGTH_LONG)
                .show();
    }

    public String getAuthToken() {
        return mSharedPreferences.getString(ExtraConstants.EXTRA_AUTH_TOKEN, "");
    }

    public void setAuthToken(String authToken) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();

        editor.putString(ExtraConstants.EXTRA_AUTH_TOKEN, authToken);

        editor.commit();
    }

    public void startActivity(Class clazz) {
        startActivity(new Intent(this, clazz).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
}
