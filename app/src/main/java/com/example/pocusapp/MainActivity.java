package com.example.pocusapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor proximity;
    public static final long START_TIME_IN_MILLIS = 25 * 60000;
    private TextView mTextViewCountDown;
    private TextView mTextViewButton;
    private ImageButton mButtonStartPause;
    private android.widget.Button mButtonReset;
//    private Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;

    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Checando se a permissão não está garantida
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Intent about = new Intent(this, AboutActivity.class);
            startActivity(about);
            finish();
        }

        mTextViewCountDown = findViewById(R.id.text_view_timer);
        mButtonStartPause = findViewById(R.id.btn_startPause);
        mButtonReset = findViewById(R.id.btn_reset);
        mTextViewButton = findViewById(R.id.txtviewbtnplaypause);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);


        if (sensorManager != null) {
            Sensor proximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

            if (proximity != null) {
                sensorManager.registerListener(this, proximity, sensorManager.SENSOR_DELAY_NORMAL);
            }
        }

    // setando o evento onClick
        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

    // setando o evento onClick
        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer();
            }
        });

        updateCountDownText();

    }

    private void startTimer() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(200);
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
//                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
//                v.cancel();
            }


            @Override
            public void onFinish() {
                mTimerRunning = false;
                // muda o icon
                mButtonStartPause.setImageResource(R.drawable.ic_start_timer);
                mTextViewButton.setText("Começar");
                //muda a visibilidade do reset
                mButtonReset.setVisibility(View.VISIBLE);
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                // Start without a delay
                // Vibrate for 100 milliseconds
                // Sleep for 1000 milliseconds
                long[] pattern = {0, 100, 100};

                // The '0' here means to repeat indefinitely
                // '0' is actually the index at which the pattern keeps repeating from (the start)'
                v.vibrate(pattern, 0);
            }
        }.start();
        mTimerRunning = true;
        // muda o icon
        mButtonStartPause.setImageResource(R.drawable.ic_stop_timer);
        mTextViewButton.setText("Pausar");
        //Muda a visibilidade do reset
        mButtonReset.setVisibility(View.INVISIBLE);
    }

    private void pauseTimer() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(200);
        mCountDownTimer.cancel();
        mTimerRunning = false;
        // muda o icon
        mButtonStartPause.setImageResource(R.drawable.ic_start_timer);
        mTextViewButton.setText("Começar");
        // Muda a visibilidade do reset
        mButtonReset.setVisibility(View.VISIBLE);
    }

    private void resetTimer() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.cancel();
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        mButtonReset.setVisibility(View.INVISIBLE);
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis/1000) /60;
        int seconds = (int) (mTimeLeftInMillis/1000) %60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
        mTextViewCountDown.setText(timeLeftFormatted);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            // ((TextView)findViewById(R.id.textView6)).setText("Values: " + event.values[0]); // muda o texto do "Começar" pra sabermos o valor do proximity

            if (event.values[0] == 0) {
                Toast.makeText(this, "Timer Started", Toast.LENGTH_SHORT).show();
                startTimer();
            }

            if (event.values[0] > 0 && mTimeLeftInMillis < (25 * 60000)) {
                Toast.makeText(this, "Timer Paused", Toast.LENGTH_LONG).show();
                pauseTimer();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    } // DON'T TOUCH !

    public void goAbout (View view) {
        Intent about = new Intent(this, AboutActivity.class);
        startActivity(about);
        finish();
    }
    public void goProfile (View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        Bundle extras = getIntent().getExtras();
        String username = extras.getString("myUsername");
        String password = extras.getString("myPassword");
        intent.putExtra("myUsername", username);
        intent.putExtra("myPassword", password);
        startActivity(intent);
    }

}