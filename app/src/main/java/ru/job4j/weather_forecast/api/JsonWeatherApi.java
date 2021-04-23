package ru.job4j.weather_forecast.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.job4j.weather_forecast.model.Item;

public interface JsonWeatherApi {

    @GET("onecall?&units=metric&exclude=minutely")
    Call<Item> getForecast(@Query("lang") String lang, @Query("lat") double latitude,
                           @Query("lon") double longitude, @Query("appid") String key);
}
