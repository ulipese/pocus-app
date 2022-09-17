package com.example.pocusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WhatIsPomodoroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_is_pomodoro);
    }

    public void goBack (View view) {
        Intent back = new Intent(this, ImportanceOfFocusActivity.class);
        startActivity(back);
        super.onBackPressed();
    }
}