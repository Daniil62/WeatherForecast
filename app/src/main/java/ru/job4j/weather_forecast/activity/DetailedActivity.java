package ru.job4j.weather_forecast.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import ru.job4j.weather_forecast.R;

public abstract class DetailedActivity extends FragmentActivity {
    public static final String DETAILED_FOR = "detailed_for";
    public abstract Fragment loadFrg();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_host_frg);
        FragmentManager fm = getSupportFragmentManager();
        if (fm.findFragmentById(R.id.detailed_host) == null) {
            fm.beginTransaction()
                    .add(R.id.detailed_host, loadFrg())
                    .commit();
        }
    }
}
