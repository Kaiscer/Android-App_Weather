package com.example.appweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.material.snackbar.Snackbar;

import java.time.Instant;

public class MainActivity extends AppCompatActivity {

    EditText etLatitude;
    EditText etLongitude;
    Button btnGetWeather;

    ProgressBar pb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etLatitude = findViewById(R.id.et_latitude);
        etLongitude = findViewById(R.id.et_longitude);
        btnGetWeather = findViewById(R.id.btn_get_weather);
        pb = findViewById(R.id.progressBar);
        pb.setVisibility(View.GONE);

        btnGetWeather.setOnClickListener(v -> {
            String latitude = etLatitude.getText().toString();
            String longitude = etLongitude.getText().toString();

            if (latitude.isEmpty() || longitude.isEmpty()) {
                Snackbar.make(btnGetWeather, R.string.errorMessage, Snackbar.LENGTH_SHORT).show();
            }else {
                pb.setVisibility(View.VISIBLE);
                Intent intent = new Intent(this, WeatherActivity.class);
                intent.putExtra("latitude", latitude);
                intent.putExtra("longitude", longitude);
                startActivity(intent);
            }

        });
    }




}