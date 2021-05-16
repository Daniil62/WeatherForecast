package ru.job4j.weather_forecast.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import ru.job4j.weather_forecast.data_base.Constants;
import ru.job4j.weather_forecast.model.Item;

@Dao
public interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addItem(Item item);
    @Query("select * from " + Constants.ITEM_TAB)
    Item getItem();
    @Query("DELETE FROM " + Constants.ITEM_TAB)
    void deleteAll();
}
