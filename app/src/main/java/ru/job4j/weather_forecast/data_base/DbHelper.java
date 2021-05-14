package ru.job4j.weather_forecast.data_base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.Nullable;
import ru.job4j.weather_forecast.model.Daily;
import ru.job4j.weather_forecast.model.DailyWeather;
import ru.job4j.weather_forecast.model.Hourly;
import ru.job4j.weather_forecast.model.HourlyWeather;
import ru.job4j.weather_forecast.model.Item;
import ru.job4j.weather_forecast.model.Temp;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "weather_forecast.db";
    private static final int VERSION = 1;
    private final SQLiteDatabase readableDb = getReadableDatabase();
    private final SQLiteDatabase writableDb = getWritableDatabase();
    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "
                + DbSchema.DailyForecast.TAB_NAME + " ("
                + "_id integer primary key autoincrement, "
                + DbSchema.DailyForecast.Cols.DATE + " integer,"
                + DbSchema.DailyForecast.Cols.MAX_T + " real,"
                + DbSchema.DailyForecast.Cols.MIN_T + " real,"
                + DbSchema.DailyForecast.Cols.WIND_SPEED + " real,"
                + DbSchema.DailyForecast.Cols.WIND_DIR + " real,"
                + DbSchema.DailyForecast.Cols.PRESSURE + " integer,"
                + DbSchema.DailyForecast.Cols.HUMIDITY + " integer,"
                + DbSchema.DailyForecast.Cols.CLOUDS + " integer,"
                + DbSchema.DailyForecast.Cols.P_O_P + " real,"
                + DbSchema.DailyForecast.Cols.DEW_POINT + " real,"
                + DbSchema.DailyForecast.Cols.UVI + " real,"
                + DbSchema.DailyForecast.Cols.SUNRISE + " integer,"
                + DbSchema.DailyForecast.Cols.SUNSET + " integer,"
                + DbSchema.DailyForecast.Cols.MOONRISE + " integer,"
                + DbSchema.DailyForecast.Cols.MOONSET + " integer,"
                + DbSchema.DailyForecast.Cols.MOON_PHASE + " real,"
                + DbSchema.DailyForecast.Cols.ICON + " integer,"
                + DbSchema.DailyForecast.Cols.DESC + " text" + ")");
        sqLiteDatabase.execSQL("create table "
                + DbSchema.HourlyForecast.TAB_NAME + " ("
                + "_id integer primary key autoincrement, "
                + DbSchema.HourlyForecast.Cols.TIME + " integer,"
                + DbSchema.HourlyForecast.Cols.TEMP + " real,"
                + DbSchema.HourlyForecast.Cols.VISIBILITY + " integer,"
                + DbSchema.HourlyForecast.Cols.WIND_SPEED + " real,"
                + DbSchema.HourlyForecast.Cols.WIND_DIR + " real,"
                + DbSchema.HourlyForecast.Cols.PRESSURE + " integer,"
                + DbSchema.HourlyForecast.Cols.CLOUDS + " integer,"
                + DbSchema.HourlyForecast.Cols.P_O_P + " real,"
                + DbSchema.HourlyForecast.Cols.ICON + " integer"+ ")");
        sqLiteDatabase.execSQL("create table "
                + DbSchema.ItemTable.TAB_NAME + " ("
                + "_id integer primary key autoincrement, "
                + DbSchema.ItemTable.Cols.LAT + " real,"
                + DbSchema.ItemTable.Cols.LON + " real,"
                + DbSchema.ItemTable.Cols.TIME_ZONE + " text" + ")");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
    public void addItem(Item item) {
        if (item != null && item.getDaily() != null && item.getHourly() != null) {
            writableDb.delete(DbSchema.ItemTable.TAB_NAME, null, null);
            writableDb.delete(DbSchema.DailyForecast.TAB_NAME, null, null);
            writableDb.delete(DbSchema.HourlyForecast.TAB_NAME, null, null);
                ContentValues values = new ContentValues();
                values.put(DbSchema.ItemTable.Cols.LAT, item.getLat());
                values.put(DbSchema.ItemTable.Cols.LON, item.getLon());
                values.put(DbSchema.ItemTable.Cols.TIME_ZONE, item.getTimezone());
                writableDb.insert(DbSchema.ItemTable.TAB_NAME, null, values);
                addDailyList(item.getDaily());
                addHourlyList(item.getHourly());
        }
    }
    private void addDailyList(List<Daily> list) {
        ContentValues values = new ContentValues();
        for (Daily daily : list) {
            values.put(DbSchema.DailyForecast.Cols.DATE, daily.getDt());
            values.put(DbSchema.DailyForecast.Cols.MIN_T, daily.getTemp().getNight());
            values.put(DbSchema.DailyForecast.Cols.MAX_T, daily.getTemp().getMax());
            values.put(DbSchema.DailyForecast.Cols.WIND_SPEED, daily.getWindSpeed());
            values.put(DbSchema.DailyForecast.Cols.WIND_DIR, daily.getWindDeg());
            values.put(DbSchema.DailyForecast.Cols.PRESSURE, daily.getPressure());
            values.put(DbSchema.DailyForecast.Cols.HUMIDITY, daily.getHumidity());
            values.put(DbSchema.DailyForecast.Cols.CLOUDS, daily.getClouds());
            values.put(DbSchema.DailyForecast.Cols.P_O_P, daily.getPop());
            values.put(DbSchema.DailyForecast.Cols.DEW_POINT, daily.getDewPoint());
            values.put(DbSchema.DailyForecast.Cols.UVI, daily.getUvi());
            values.put(DbSchema.DailyForecast.Cols.SUNRISE, daily.getSunrise());
            values.put(DbSchema.DailyForecast.Cols.SUNSET, daily.getSunset());
            values.put(DbSchema.DailyForecast.Cols.MOONRISE, daily.getMoonrise());
            values.put(DbSchema.DailyForecast.Cols.MOONSET, daily.getMoonset());
            values.put(DbSchema.DailyForecast.Cols.MOON_PHASE, daily.getMoonPhase());
            values.put(DbSchema.DailyForecast.Cols.ICON, daily.getWeather().getIcon());
            values.put(DbSchema.DailyForecast.Cols.DESC, daily.getWeather().getDescription());
            writableDb.insert(DbSchema.DailyForecast.TAB_NAME, null, values);
            values.clear();
        }
    }
    private void addHourlyList(List<Hourly> list) {
        ContentValues values = new ContentValues();
        for (Hourly hourly : list) {
            values.put(DbSchema.HourlyForecast.Cols.TIME, hourly.getDt());
            values.put(DbSchema.HourlyForecast.Cols.TEMP, hourly.getTemp());
            values.put(DbSchema.HourlyForecast.Cols.VISIBILITY, hourly.getVisibility());
            values.put(DbSchema.HourlyForecast.Cols.WIND_SPEED, hourly.getWindSpeed());
            values.put(DbSchema.HourlyForecast.Cols.WIND_DIR, hourly.getWindDeg());
            values.put(DbSchema.HourlyForecast.Cols.PRESSURE, hourly.getPressure());
            values.put(DbSchema.HourlyForecast.Cols.CLOUDS, hourly.getClouds());
            values.put(DbSchema.HourlyForecast.Cols.P_O_P, hourly.getPop());
            values.put(DbSchema.HourlyForecast.Cols.ICON, hourly.getWeather().get(0).getIcon());
            writableDb.insert(DbSchema.HourlyForecast.TAB_NAME, null, values);
            values.clear();
        }
    }
    public List<Daily> getDailyList() {
        List<Daily> result = new ArrayList<>();
        Cursor cursor = readableDb.query(DbSchema.DailyForecast.TAB_NAME, null,
                null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Daily daily = new Daily(cursor.getLong(
                    cursor.getColumnIndex(DbSchema.DailyForecast.Cols.DATE)),
                    cursor.getLong(cursor.getColumnIndex(DbSchema.DailyForecast.Cols.SUNRISE)),
                    cursor.getLong(cursor.getColumnIndex(DbSchema.DailyForecast.Cols.SUNSET)),
                    cursor.getLong(cursor.getColumnIndex(DbSchema.DailyForecast.Cols.MOONRISE)),
                    cursor.getLong(cursor.getColumnIndex(DbSchema.DailyForecast.Cols.MOONSET)),
                    cursor.getDouble(cursor.getColumnIndex(DbSchema.DailyForecast.Cols.MOON_PHASE)),
                    new Temp(cursor.getDouble(cursor.getColumnIndex(DbSchema.DailyForecast.Cols.MAX_T)),
                            cursor.getDouble(cursor.getColumnIndex(DbSchema.DailyForecast.Cols.MIN_T))),
                            cursor.getInt(cursor.getColumnIndex(DbSchema.DailyForecast.Cols.PRESSURE)),
                            cursor.getInt(cursor.getColumnIndex(DbSchema.DailyForecast.Cols.HUMIDITY)),
                            cursor.getDouble(cursor.getColumnIndex(DbSchema.DailyForecast.Cols.DEW_POINT)),
                            cursor.getDouble(cursor.getColumnIndex(DbSchema.DailyForecast.Cols.WIND_SPEED)),
                            cursor.getInt(cursor.getColumnIndex(DbSchema.DailyForecast.Cols.WIND_DIR)),
                            new DailyWeather(cursor.getString(cursor.getColumnIndex(DbSchema.DailyForecast.Cols.DESC)),
                                    cursor.getString(cursor.getColumnIndex(DbSchema.DailyForecast.Cols.ICON))),
                            cursor.getInt(cursor.getColumnIndex(DbSchema.DailyForecast.Cols.CLOUDS)),
                            cursor.getDouble(cursor.getColumnIndex(DbSchema.DailyForecast.Cols.P_O_P)),
                            cursor.getDouble(cursor.getColumnIndex(DbSchema.DailyForecast.Cols.UVI)));
            daily.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            result.add(daily);
            cursor.moveToNext();
        }
        cursor.close();
        return result;
    }
    public List<Hourly> getHourlyList() {
        List<Hourly> result = new ArrayList<>();
        Cursor cursor = readableDb.query(DbSchema.HourlyForecast.TAB_NAME, null,
                null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            result.add(new Hourly(cursor.getLong(
                    cursor.getColumnIndex(DbSchema.HourlyForecast.Cols.TIME)),
                    cursor.getDouble(cursor.getColumnIndex(DbSchema.HourlyForecast.Cols.TEMP)),
                    cursor.getInt(cursor.getColumnIndex(DbSchema.HourlyForecast.Cols.PRESSURE)),
                    cursor.getInt(cursor.getColumnIndex(DbSchema.HourlyForecast.Cols.CLOUDS)),
                    cursor.getInt(cursor.getColumnIndex(DbSchema.HourlyForecast.Cols.VISIBILITY)),
                    cursor.getDouble(cursor.getColumnIndex(DbSchema.HourlyForecast.Cols.WIND_SPEED)),
                    cursor.getInt(cursor.getColumnIndex(DbSchema.HourlyForecast.Cols.WIND_DIR)),
                    new HourlyWeather(cursor.getString(
                            cursor.getColumnIndex(DbSchema.HourlyForecast.Cols.ICON))),
                    cursor.getDouble(cursor.getColumnIndex(DbSchema.HourlyForecast.Cols.P_O_P))));
            cursor.moveToNext();
        }
        cursor.close();
        return result;
    }
    public Daily getDaily(int id) {
        Daily result = new Daily();
        Cursor cursor = readableDb.query(DbSchema.DailyForecast.TAB_NAME, null,
                "_id = " + id, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if (cursor.getInt(cursor.getColumnIndex("_id")) == id) {
                result = new Daily(cursor.getLong(
                        cursor.getColumnIndex(DbSchema.DailyForecast.Cols.DATE)),
                        cursor.getLong(cursor.getColumnIndex(DbSchema.DailyForecast.Cols.SUNRISE)),
                        cursor.getLong(cursor.getColumnIndex(DbSchema.DailyForecast.Cols.SUNSET)),
                        cursor.getLong(cursor.getColumnIndex(DbSchema.DailyForecast.Cols.MOONRISE)),
                        cursor.getLong(cursor.getColumnIndex(DbSchema.DailyForecast.Cols.MOONSET)),
                        cursor.getDouble(cursor.getColumnIndex(DbSchema.DailyForecast.Cols.MOON_PHASE)),
                        new Temp(cursor.getDouble(cursor.getColumnIndex(DbSchema.DailyForecast.Cols.MAX_T)),
                                cursor.getDouble(cursor.getColumnIndex(DbSchema.DailyForecast.Cols.MIN_T))),
                        cursor.getInt(cursor.getColumnIndex(DbSchema.DailyForecast.Cols.PRESSURE)),
                        cursor.getInt(cursor.getColumnIndex(DbSchema.DailyForecast.Cols.HUMIDITY)),
                        cursor.getDouble(cursor.getColumnIndex(DbSchema.DailyForecast.Cols.DEW_POINT)),
                        cursor.getDouble(cursor.getColumnIndex(DbSchema.DailyForecast.Cols.WIND_SPEED)),
                        cursor.getInt(cursor.getColumnIndex(DbSchema.DailyForecast.Cols.WIND_DIR)),
                        new DailyWeather(cursor.getString(cursor.getColumnIndex(DbSchema.DailyForecast.Cols.DESC)),
                                cursor.getString(cursor.getColumnIndex(DbSchema.DailyForecast.Cols.ICON))),
                        cursor.getInt(cursor.getColumnIndex(DbSchema.DailyForecast.Cols.CLOUDS)),
                        cursor.getDouble(cursor.getColumnIndex(DbSchema.DailyForecast.Cols.P_O_P)),
                        cursor.getDouble(cursor.getColumnIndex(DbSchema.DailyForecast.Cols.UVI)));
            }
            cursor.moveToNext();
        }
        cursor.close();
        return result;
    }
}
