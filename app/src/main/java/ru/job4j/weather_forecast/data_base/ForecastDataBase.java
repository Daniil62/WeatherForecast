package ru.job4j.weather_forecast.data_base;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import ru.job4j.weather_forecast.dao.DailyDao;
import ru.job4j.weather_forecast.dao.HourlyDao;
import ru.job4j.weather_forecast.dao.ItemDao;
import ru.job4j.weather_forecast.model.Daily;
import ru.job4j.weather_forecast.model.Hourly;
import ru.job4j.weather_forecast.model.Item;

@Database(entities = {Item.class, Daily.class, Hourly.class}, version = 1, exportSchema = false)
public abstract class ForecastDataBase extends RoomDatabase {

    public abstract ItemDao itemDao();
//    public abstract DailyDao dailyDao();
//    public abstract HourlyDao hourlyDao();
}
