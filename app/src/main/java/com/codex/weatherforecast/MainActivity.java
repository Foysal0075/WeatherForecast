package com.codex.weatherforecast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.codex.weatherforecast.api_class.WeatherResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private String apiKey = "d31ac27d3e383a7baf33b5038b945844";
    String location = "dhaka,bangladesh";
    ApiEndpoint apiEndpoint;
    Button button ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         apiEndpoint = ApiClient.getRetrofit().create(ApiEndpoint.class);
         button= findViewById(R.id.get_weather_button);
         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 getWeather();
             }
         });

    }

    public void getWeather() {
        Call<WeatherResponse>  call = apiEndpoint.getForecastData(location,apiKey);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                WeatherResponse weatherResponse = response.body();
                if (weatherResponse!=null){
                    String city = weatherResponse.getCity().getName() ;
                    String temperature = city+" " +" Temperature  " +String.format("%.0f", weatherResponse.getList().get(0).getMain().getTemp())+(char) 0x00B0+"F";
                    Toast.makeText(MainActivity.this,temperature, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
