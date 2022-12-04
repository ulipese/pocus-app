package com.example.pocusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    EditText txtUsername, txtPassword;
    Button btnLogin;
    CheckBox rememberMe;
    DBHelper myDb;
    ArrayList<String> username, password;
    public static final String SHARED_PREFS = "sharedPrefs";

    public void callRegisterScreen(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        rememberMe = findViewById(R.id.btnRememberMe);

        txtUsername = findViewById(R.id.txtUsernameLogin);
        txtPassword = findViewById(R.id.txtPasswordLogin);
        btnLogin = findViewById(R.id.btnLogin);
        myDb = new DBHelper(this);

        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String checkbox = preferences.getString("remember", "");

        if (checkbox.equals("true")) {
            Intent main = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(main);
        }
        if (checkbox.equals("false")) {
            Toast.makeText(LoginActivity.this, "Enter your data to login.", Toast.LENGTH_SHORT).show();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();

                if (username.equals("") || password.equals("")) {
                    Toast.makeText(LoginActivity.this, "Fill all fields.", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkPassword = myDb.checkPassword(username, password);
                    if (checkPassword == true) {
                        Toast.makeText(LoginActivity.this, "Login completed.", Toast.LENGTH_SHORT).show();
                        // Intent home = new Intent(LoginActivity.this, MainActivity.class);
                        Intent profile = new Intent(getApplicationContext(), ProfileActivity.class);
                        profile.putExtra("myUsername", username);
                        profile.putExtra("myPassword", password);
                        txtUsername.setText("");
                        txtPassword.setText("");
                        startActivity(profile);
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid infos.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}