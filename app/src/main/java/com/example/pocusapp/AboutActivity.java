package com.example.pocusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    public void chamarActivityPocus(View view) {
        Intent intent = new Intent(this, WhatIsPocusActivity.class);
        startActivity(intent);
        finish();
    }

    public void chamarActivityImportancia(View view) {
        Intent intent = new Intent(this, ImportanceOfFocusActivity.class);
        startActivity(intent);
        finish();
    }

    public void chamarActivityOquePomodoro(View view) {
        Intent intent = new Intent(this, WhatIsPomodoroActivity.class);
        startActivity(intent);
        finish();
    }
}