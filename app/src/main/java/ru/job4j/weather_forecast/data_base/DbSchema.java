package ru.job4j.weather_forecast.data_base;

public class DbSchema {
    public static class DailyForecast {
        public static final String TAB_NAME = "daily_tab";
        public static class Cols {
            public static final String DATE = "date";
            public static final String MAX_T = "max_t";
            public static final String MIN_T = "min_t";
            public static final String WIND_SPEED = "d_wind_speed";
            public static final String WIND_DIR = "d_wind_dir";
            public static final String PRESSURE = "d_pressure";
            public static final String HUMIDITY = "humidity";
            public static final String CLOUDS = "d_clouds";
            public static final String P_O_P = "d_precipitation";
            public static final String DEW_POINT = "dew_point";
            public static final String UVI = "uvi";
            public static final String SUNRISE = "sunrise";
            public static final String SUNSET = "sunset";
            public static final String MOONRISE = "moonrise";
            public static final String MOONSET = "moonset";
            public static final String MOON_PHASE = "moon_phase";
            public static final String ICON = "d_icon";
            public static final String DESC = "d_description";
        }
    }
    public static final class HourlyForecast {
        public static final String TAB_NAME = "hourly_tab";
        public static final class Cols {
            public static final String TIME = "time";
            public static final String TEMP = "temperature";
            public static final String VISIBILITY = "visibility";
            public static final String WIND_SPEED = "h_wind_speed";
            public static final String WIND_DIR = "h_wind_dir";
            public static final String PRESSURE = "h_pressure";
            public static final String CLOUDS = "h_clouds";
            public static final String P_O_P = "h_precipitation";
            public static final String ICON = "h_icon";
        }
    }
    public static final class ItemTable {
        public static final String TAB_NAME = "item_tab";
        public static final class Cols {
            public static final String LAT = "lat";
            public static final String LON = "lon";
            public static final String TIME_ZONE = "time_zone";
        }
    }
}
