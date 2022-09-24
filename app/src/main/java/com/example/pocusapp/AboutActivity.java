package com.example.pocusapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Checando se a permissão não está garantida
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
        // Se não... (permissão garantida)
        else {

        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                // Se o usuário aceitar, grantResults  > 0 ou == 1
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Se a permissão for realmente concedida, irá exibir na tela ...
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                    }
                }
                // se não:
                else {
                    Intent permission = new Intent(this, permissions_activity.class);
                    startActivity(permission);
                    finish();
                }
                return;
            }
        }
    }

    public void chamarActivityPocus (View view) {
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


    public void goPermission(View view) {
        Intent permission = new Intent(this, permissions_activity.class);
        startActivity(permission);
        finish();
    }


    public void chamarActivityMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void sendEmail(View view) throws UnsupportedEncodingException {
        String uriText = "mailto:lauan.amorim@etec.sp.gov.br" + "?subject="+ URLEncoder.encode("Assunto", "utf-8")+
                "&body="+ URLEncoder.encode("Descreva\u00A0o\u00A0\u00A0problema/sugestão", "utf-8");
        Uri uri = Uri.parse(uriText);
        Intent iniciar = new Intent(Intent.ACTION_SENDTO);
        iniciar.setData(uri);
        startActivity(Intent.createChooser(iniciar, getString(R.string.sendEmail)));
    }


}