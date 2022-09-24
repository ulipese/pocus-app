package com.example.pocusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class WhatIsPomodoroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_is_pomodoro);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void goBack (View view) {
        Intent back = new Intent(this, ImportanceOfFocusActivity.class);
        startActivity(back);
        super.onBackPressed();
    }

    public void goTimer (View view) {
        Intent timer = new Intent(this, MainActivity.class);
        startActivity(timer);
        finish();
    }
}