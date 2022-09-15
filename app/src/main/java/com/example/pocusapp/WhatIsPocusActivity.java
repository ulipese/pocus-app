package com.example.pocusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WhatIsPocusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_is_pocus);
    }

    public void chamarActivityImportance(View view) {
        Intent intent = new Intent(this, ImportanceOfFocusActivity.class);
        startActivity(intent);
        finish();
    }
}