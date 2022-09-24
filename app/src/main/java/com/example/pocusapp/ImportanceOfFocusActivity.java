package com.example.pocusapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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

    public void goBack (View view) {
        Intent back = new Intent(this, WhatIsPocusActivity.class);
        startActivity(back);
        super.onBackPressed();
    }

    public void openOnBrowser (View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://folhadirigida.com.br/blog/sessao-estudos-produtiva/"));
        startActivity(Intent.createChooser(intent, "Escolha um browser"));
    }

    public void mostrarMapa(View view){
        if(ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                &&
                ActivityCompat.checkSelfPermission(
                        this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_NETWORK_STATE}, 1);
            return;
        } else {
            LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
            Intent mapIntent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/biblioteca/" + longitude + "," + latitude + "," + "17z"));
            startActivity(mapIntent);
        }
    }
}