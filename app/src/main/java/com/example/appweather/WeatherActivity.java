package com.example.appweather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.appweather.retrofiltutils.Data;
import com.example.appweather.retrofiltutils.WeatherRes;
import com.example.appweather.retrofitdata.APIRestService;
import com.example.appweather.retrofitdata.RetrofitClient;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WeatherActivity extends AppCompatActivity {

    private static final String API_KEY = "11ce4328111023379e0fdc9d28c24a02";
    public static final String CLAVE_EXCLUDE = "?exclude=minutely,hourly,daily,alerts,flags&lang=";
    public static final String CLAVE_LANG = "es";
    TextView tvCity;
    ImageView ivCloud;
    TextView tvTime;
    TextView tvTemp;
    TextView tvResultHumidity;
    TextView tvResultRain;
    TextView tvResultTime;
    ProgressBar pbLoading;
    Data data;
    
    
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        references();
        data = (Data) getIntent().getParcelableExtra(MainActivity.TAG_LOCATION);
        getWeather(data.getLatitude(), data.getLongitude());
    }

    private void getWeather(Double latitude, Double longitude) {
        // Client Retrofit que nos permite hacer la petición
        Retrofit retrofit = RetrofitClient.getClient(APIRestService.BASE_URL);
        // Creamos la instancia de la interfaz
        APIRestService apiRestService = retrofit.create(APIRestService.class);
        // llamamos a la API con lo parametros que necesitamos
       Call<WeatherRes> call = apiRestService.getWeather(API_KEY,latitude,longitude,CLAVE_EXCLUDE,CLAVE_LANG);
       pbLoading.setVisibility(View.VISIBLE);
       // Ejecutamos la petición
       call.enqueue(new retrofit2.Callback<WeatherRes>(){
           // Si la petición es correcta y nos devuelve un 200 OK
           @Override
           public void onResponse(Call<WeatherRes> call, Response<WeatherRes> response) {
               pbLoading.setVisibility(View.GONE);
               if(response.isSuccessful()){
                   // Obtenemos el objeto WeatherRes para poder acceder a sus atributos
                   WeatherRes weatherRes = response.body();
                   tvCity.setText(weatherRes.getTimezone());
                   int icon = getResources().getIdentifier(weatherRes.getCurrently().getIcon(),
                           "drawable", getPackageName());
                     ivCloud.setImageResource(icon);
                     // Formateamos la fecha
                   Date date = new Date(weatherRes.getCurrently().getTime() * 1000);
                   tvTime.setText(new SimpleDateFormat("hh:mm a").format(date));
                   // Formateamos la temperatura ya que viene en grados Farenheit
                   double dgC = ((weatherRes.getCurrently().getTemperature() - 32) * 5/9);
                     tvTemp.setText((int) dgC);
                        tvResultHumidity.setText(weatherRes.getCurrently().getHumidity()*100 + "%");
                        tvResultRain.setText(weatherRes.getCurrently().getPrecipProbability()*100 + "%");
                        tvResultTime.setText(weatherRes.getCurrently().getSummary());
               }
           }

           @Override
           public void onFailure(Call<WeatherRes> call, Throwable t) {
               Log.e("Error", t.getMessage());
           }
       });
    }

    private void references() {
        tvCity = findViewById(R.id.tv_city);
        ivCloud = findViewById(R.id.iv_cloud);
        tvTime = findViewById(R.id.tv_time);
        tvTemp = findViewById(R.id.tv_temp);
        tvResultHumidity = findViewById(R.id.tv_result_humidity);
        tvResultRain = findViewById(R.id.tv_result_rain);
        tvResultTime = findViewById(R.id.tv_detailTime);
        pbLoading = findViewById(R.id.progressBar);
        pbLoading.setVisibility(View.GONE);
        
    }
}