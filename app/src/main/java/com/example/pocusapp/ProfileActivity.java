package com.example.pocusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ProfileActivity extends AppCompatActivity {
    TextView txtUsername, txtPassword;
    Button btnLogout;
    DBHelper myDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        txtUsername = findViewById(R.id.txtUsernameView);
        txtPassword = findViewById(R.id.txtPasswordView);
        btnLogout = findViewById(R.id.btnLogout);
        myDb = new DBHelper(this);

        // Get username
        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            String username = extras.getString("myUsername");
            Cursor cursor = myDb.getData(username);
            if (cursor.moveToFirst()) {
                do {
                    String dbUsername = cursor.getString(1);
                    String dbPassword = cursor.getString(2);
                    txtUsername.setText("Hello " + dbUsername + ", it's time to focus!");
                    txtPassword.setText("Don't forget, your password is '" + dbPassword + "'.");
                } while (cursor.moveToNext());
            }
        }
    }
    public void Logout (View view) {
        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("remember", "false");
        editor.apply();

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
    public void chamarActivityMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        Bundle extras = getIntent().getExtras();
        String username = extras.getString("myUsername");
        String password = extras.getString("myPassword");
        intent.putExtra("myUsername", username);
        intent.putExtra("myPassword", password);
        startActivity(intent);
    }

}