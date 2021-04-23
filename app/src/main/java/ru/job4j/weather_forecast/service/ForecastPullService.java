package ru.job4j.weather_forecast.service;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;
import ru.job4j.weather_forecast.fragment.ForecastFragment;

public class ForecastPullService extends JobIntentService {
    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        ForecastFragment.ForecastLoader loader = new ForecastFragment.ForecastLoader();
        loader.loadForecast();
    }
    public static void enqueueWork(Context context, Intent intent) {
        enqueueWork(context, ForecastPullService.class, 0, intent);
    }
}
