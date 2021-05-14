package ru.job4j.weather_forecast.tools;

import java.util.HashMap;
import java.util.Map;
import ru.job4j.weather_forecast.R;

public class WeekDayConverter {
    private static final Map<Integer, Integer> WEEK_DAY_STORE = new HashMap<>();
    static {
        WEEK_DAY_STORE.put(1, R.string.sun);
        WEEK_DAY_STORE.put(2, R.string.mon);
        WEEK_DAY_STORE.put(3, R.string.tue);
        WEEK_DAY_STORE.put(4, R.string.wed);
        WEEK_DAY_STORE.put(5, R.string.thu);
        WEEK_DAY_STORE.put(6, R.string.fri);
        WEEK_DAY_STORE.put(7, R.string.sat);
    }
    public static int getDay(int key) {
        return WEEK_DAY_STORE.get(key);
    }
}
