package com.example.appweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.appweather.retrofiltutils.Data;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {


    public static final String TAG_LOCATION = "LOCATION";
    EditText etLatitude;
    EditText etLongitude;
    Button btnGetWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etLatitude = findViewById(R.id.et_latitude);
        etLongitude = findViewById(R.id.et_longitude);
        btnGetWeather = findViewById(R.id.btn_get_weather);


        btnGetWeather.setOnClickListener(v -> {
            String latitude = etLatitude.getText().toString();
            String longitude = etLongitude.getText().toString();

            if (latitude.isEmpty() || longitude.isEmpty()) {
                Snackbar.make(btnGetWeather, R.string.errorMessage, Snackbar.LENGTH_SHORT).show();
            }else {
                Data data = new Data(Double.parseDouble(latitude), Double.parseDouble(longitude));
                Intent intent = new Intent(this, WeatherActivity.class);
                intent.putExtra(TAG_LOCATION, data);
                startActivity(intent);
            }

        });
    }




}