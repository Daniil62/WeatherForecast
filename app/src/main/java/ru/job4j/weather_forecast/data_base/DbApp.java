package ru.job4j.weather_forecast.data_base;

import android.app.Application;
import androidx.room.Room;

public class DbApp extends Application {
    public static DbApp instance;
    private static ForecastDataBase dataBase;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        dataBase = Room.databaseBuilder(this, ForecastDataBase.class,
                "forecast_database").allowMainThreadQueries().build();
    }
    public static ForecastDataBase getDatabase() {
        return dataBase;
    }
}
