package ru.job4j.weather_forecast.dao;

import java.util.List;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import ru.job4j.weather_forecast.data_base.Constants;
import ru.job4j.weather_forecast.model.Hourly;

@Dao
public interface HourlyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addHourlyList(List<Hourly> list);
    @Query("select * from " + Constants.HOURLY_TAB)
    List<Hourly> getHourlyList();
    @Query("DELETE FROM " + Constants.HOURLY_TAB)
    void deleteAll();
}
