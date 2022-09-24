package com.example.pocusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class WhatIsPocusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_is_pocus);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void chamarActivityImportance(View view) {
        Intent intent = new Intent(this, ImportanceOfFocusActivity.class);
        startActivity(intent);
        finish();
    }

    public void goBack (View view) {
        Intent back = new Intent(this, AboutActivity.class);
        startActivity(back);
        super.onBackPressed();
    }
}