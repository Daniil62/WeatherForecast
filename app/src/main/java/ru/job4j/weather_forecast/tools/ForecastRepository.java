package ru.job4j.weather_forecast.tools;

import android.util.Log;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.job4j.weather_forecast.api.JsonWeatherApi;
import ru.job4j.weather_forecast.data_base.DbApp;
import ru.job4j.weather_forecast.data_base.ForecastDataBase;
import ru.job4j.weather_forecast.model.Item;

public class ForecastRepository {

    private final String TAG = "forecast_repository";
    private static ForecastRepository loader;
    private final JsonWeatherApi api;
    private final ForecastDataBase dataBase = DbApp.getDatabase();

    public ForecastRepository() {
        api = RetrofitService.createService(JsonWeatherApi.class);
    }

    public static ForecastRepository getInstance() {
        if (loader == null) {
            loader = new ForecastRepository();
        }
        return loader;
    }

    public void getForecast(
            String lang, double latitude, double longitude, String key) {
        api.getForecast(lang, latitude, longitude, key).enqueue(new Callback<Item>() {
            @Override
            public void onResponse(@NonNull Call<Item> call, @NonNull Response<Item> response) {
                if (response.isSuccessful() && response.body() != null) {
                    dataBase.itemDao().deleteAll();
                    dataBase.itemDao().addItem(response.body());
                }
            }
            @Override
            public void onFailure(@NonNull Call<Item> call, @NonNull Throwable t) {
                Log.e(TAG, "Throwable: " + t.getMessage());
            }
        });
    }
}
