package ru.job4j.weather_forecast.tools;

import java.util.HashMap;
import java.util.Map;
import ru.job4j.weather_forecast.R;

public class WindConverter {

    private static final Map<Integer, Integer> arrowsStore = new HashMap<>();
    static {
        arrowsStore.put(R.string.n, R.drawable.n_arrow);
        arrowsStore.put(R.string.ne, R.drawable.ne_arrow);
        arrowsStore.put(R.string.e, R.drawable.e_arrow);
        arrowsStore.put(R.string.se, R.drawable.se_arrow);
        arrowsStore.put(R.string.s, R.drawable.s_arrow);
        arrowsStore.put(R.string.sw, R.drawable.sw_arrow);
        arrowsStore.put(R.string.w, R.drawable.w_arrow);
        arrowsStore.put(R.string.nw, R.drawable.nw_arrow);
    }

    public static int getDirection(double degree) {
        int result = 0;
        if (degree >= 0 && degree < 22.5 || degree >= 337.5 && degree <= 360) {
            result = R.string.n;
        } else if (degree >= 22.5 && degree < 67.5) {
            result = R.string.ne;
        } else if (degree >= 67.5 && degree < 112.5) {
            result = R.string.e;
        } else if (degree >= 112.5 && degree < 157.5) {
            result = R.string.se;
        } else if (degree >= 157.5 && degree < 205.5) {
            result = R.string.s;
        } else if (degree >= 205.5 && degree < 247.5) {
            result = R.string.sw;
        } else if (degree >= 247.5 && degree < 292.5) {
            result = R.string.w;
        } else if (degree >= 292.5 && degree < 337.5) {
            result = R.string.nw;
        }
        return result;
    }

    public static int getArrow(int key) {
        return arrowsStore.get(key);
    }
}
