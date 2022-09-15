package com.example.pocusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ImportanceOfFocusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_importance_of_focus);
    }

    public void chamarActivityWhatIsPomodoro(View view) {
        Intent intent = new Intent(this, WhatIsPomodoroActivity.class);
        startActivity(intent);
        finish();
    }
}