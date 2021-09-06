package ru.job4j.weather_forecast.tools;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import androidx.room.TypeConverter;
import ru.job4j.weather_forecast.model.Daily;
import ru.job4j.weather_forecast.model.DailyWeather;
import ru.job4j.weather_forecast.model.Hourly;
import ru.job4j.weather_forecast.model.HourlyWeather;
import ru.job4j.weather_forecast.model.Temp;

public class FieldConverter {

    private static final Gson gson = new Gson();
    @TypeConverter
    public static List<Daily> dailyListConvert(String word) {
        Type dailyListType = new TypeToken<List<Daily>>() {}.getType();
        return word == null ? Collections.emptyList() : gson.fromJson(word, dailyListType);
    }

    @TypeConverter
    public static String dailyStringConvert(List<Daily> list) {
        return gson.toJson(list);
    }

    @TypeConverter
    public static List<Hourly> hourlyListConvert(String word) {
        Type hourlyListType = new TypeToken<List<Hourly>>() {}.getType();
        return word == null ? Collections.emptyList() : gson.fromJson(word, hourlyListType);
    }

    @TypeConverter
    public static String hourlyStringConvert(List<Hourly> list) {
        return gson.toJson(list);
    }

    @TypeConverter
    public static Temp tempConvert(String word) {
        Type tempType = new TypeToken<Temp>() {}.getType();
        return word == null ? null : gson.fromJson(word, tempType);
    }

    @TypeConverter
    public static String tempStringConvert(Temp temp) {
        return gson.toJson(temp);
    }

    @TypeConverter
    public static List<DailyWeather> dailyWeatherListConvert(String word) {
        Type dailyWeatherListType = new TypeToken<List<DailyWeather>>() {}.getType();
        return word == null ? Collections.emptyList() : gson.fromJson(word, dailyWeatherListType);
    }

    @TypeConverter
    public static String dailyWeatherStringConvert(List<DailyWeather> list) {
        return gson.toJson(list);
    }

    @TypeConverter
    public static List<HourlyWeather> hourlyWeatherListConvert(String word) {
        Type hourlyWeatherListType = new TypeToken<List<HourlyWeather>>() {}.getType();
        return word == null ? Collections.emptyList() : gson.fromJson(word, hourlyWeatherListType);
    }

    @TypeConverter
    public static String hourlyWeatherStringConvert(List<HourlyWeather> list) {
        return gson.toJson(list);
    }
}
