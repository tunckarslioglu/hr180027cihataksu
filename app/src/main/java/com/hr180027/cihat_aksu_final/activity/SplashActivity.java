package com.hr180027.cihat_aksu_final.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.view.WindowManager;

import com.hr180027.cihat_aksu_final.Constants;
import com.hr180027.cihat_aksu_final.R;
import com.hr180027.cihat_aksu_final.utils.NetworkChecker;

public class SplashActivity extends AppCompatActivity {
    Context context;

    Boolean isCheckNetwork = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        context = this;
        startTimer();

    }



    private void startTimer() {

        new CountDownTimer(Constants.INTERVAL, Constants.MILLISECOND) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

                if (NetworkChecker.isNetworkAvailable(context))
                    changeScreen();
                else
                    createAlertDialog();
            }
        }.start();
    }

    private void openNetwork() {
        isCheckNetwork = true;
        Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
        startActivity(intent);
    }

    private void changeScreen() {
        Intent i = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public void createAlertDialog() {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Light_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(context);
        }
        builder.setTitle(R.string.networkTitle)
                .setMessage(R.string.networkContent)
                .setCancelable(false)
                .setNegativeButton(context.getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();

                    }
                })
                .setPositiveButton(R.string.openNetwork, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        openNetwork();
                    }
                }).create();
        builder.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (NetworkChecker.isNetworkAvailable(context) && isCheckNetwork)
            changeScreen();
    }
}