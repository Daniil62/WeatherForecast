package ru.job4j.weather_forecast.dao;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import ru.job4j.weather_forecast.data_base.Constants;
import ru.job4j.weather_forecast.model.Daily;

@Dao
public interface DailyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addDailyList(List<Daily> list);
    @Query("select * from " + Constants.DAILY_TAB)
    List<Daily> getDailyList();
    @Query("select * from " + Constants.DAILY_TAB + " where id = :id")
    Daily getDaily(int id);
    @Query("DELETE FROM " + Constants.DAILY_TAB)
    void deleteAll();
}
