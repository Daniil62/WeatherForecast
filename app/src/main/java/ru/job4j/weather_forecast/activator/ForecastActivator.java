package ru.job4j.weather_forecast.activator;

import android.content.Intent;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import ru.job4j.weather_forecast.R;
import ru.job4j.weather_forecast.activity.DetailedActivity;
import ru.job4j.weather_forecast.activity.MainActivity;
import ru.job4j.weather_forecast.fragment.DetailedFragment;
import ru.job4j.weather_forecast.fragment.ForecastFragment;

public class ForecastActivator extends MainActivity implements ForecastFragment.ForecastSelect{

    @Override
    public Fragment loadFrg() {
        return new ForecastFragment();
    }

    @Override
    public void selected(int index) {
        if (findViewById(R.id.detailed) == null) {
            Intent intent = new Intent(this, DetailedActivator.class);
            intent.putExtra(DetailedActivity.DETAILED_FOR, index);
            startActivity(intent);
        } else {
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.detailed, DetailedFragment.of(index)).commit();
        }
    }
}
