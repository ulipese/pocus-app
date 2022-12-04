package com.example.pocusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText txtUsername, txtPassword, txtPasswordConfirm;
    Button btnRegister;
    DBHelper myDb;

    public void callLoginScreen(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        txtPasswordConfirm = findViewById(R.id.txtPasswordConfirm);
        btnRegister = findViewById(R.id.btnRegister);
        myDb = new DBHelper(this);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();
                String passwordConfirm = txtPasswordConfirm.getText().toString();

                if (username.equals("") || password.equals("") || passwordConfirm.equals("")){
                    Toast.makeText(RegisterActivity.this, "Fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    if(password.equals(passwordConfirm)) {
                        Boolean checkUser = myDb.checkUser(username);
                        if(checkUser == false) {
                            Boolean insert = myDb.Register(username, password);
                            txtUsername.setText("");
                            txtPassword.setText("");

                            if(insert==true) {
                                Toast.makeText(RegisterActivity.this, "User registered.", Toast.LENGTH_SHORT).show();
                                Intent profile = new Intent(getApplicationContext(), ProfileActivity.class);
                                profile.putExtra("myUsername", username);
                                txtUsername.setText("");
                                txtPassword.setText("");
                                startActivity(profile);

                            } else {
                                Toast.makeText(RegisterActivity.this, "User register failed :(", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(RegisterActivity.this, "User already exists! Try login.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Passwords have different values", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}