package com.example.pocusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class talk_developers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk_developers);
    }

    public void sendEmail(View view) throws UnsupportedEncodingException {
        String uriText = "mailto:lauan.amorim@etec.sp.gov.br" + "?subject="+ URLEncoder.encode("Assunto\u00A0do\u00A0email", "utf-8")+
                "&body="+ URLEncoder.encode("texto\u00A0do\u00A0email", "utf-8");
        Uri uri = Uri.parse(uriText);
        Intent iniciar = new Intent(Intent.ACTION_SENDTO);
        iniciar.setData(uri);
        startActivity(Intent.createChooser(iniciar, getString(R.string.sendEmail)));
    }
}