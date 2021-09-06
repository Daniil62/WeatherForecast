package ru.job4j.weather_forecast.activator;

import androidx.fragment.app.Fragment;
import ru.job4j.weather_forecast.activity.DetailedActivity;
import ru.job4j.weather_forecast.fragment.DetailedFragment;

public class DetailedActivator extends DetailedActivity {

    @Override
    public Fragment loadFrg() {
        return DetailedFragment.of(getIntent()
                .getIntExtra(DetailedActivity.DETAILED_FOR, 0));
    }
}
