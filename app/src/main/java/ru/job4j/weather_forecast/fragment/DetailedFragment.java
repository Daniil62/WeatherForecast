package ru.job4j.weather_forecast.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ru.job4j.weather_forecast.R;
import ru.job4j.weather_forecast.activity.DetailedActivity;
import ru.job4j.weather_forecast.adapter.DetailedAdapter;
import ru.job4j.weather_forecast.data_base.DbApp;
import ru.job4j.weather_forecast.data_base.ForecastDataBase;
import ru.job4j.weather_forecast.databinding.DetailedFrgBinding;
import ru.job4j.weather_forecast.model.Daily;
import ru.job4j.weather_forecast.tools.ImageLoader;
import ru.job4j.weather_forecast.tools.WeekDayConverter;
import ru.job4j.weather_forecast.tools.WindConverter;

public class DetailedFragment extends Fragment {

    private DetailedFrgBinding binding;
    private ForecastDataBase dataBase;
    private static Daily daily;
    private TextView date;
    private RecyclerView recycler;
    private ImageView picture;
    private TextView minTemp;
    private TextView maxTemp;
    private TextView description;
    private TextView windSpeed;
    private TextView windDegree;
    private ImageView windPicture;
    private TextView pressure;
    private TextView humidity;
    private TextView clouds;
    private TextView probOfPrecipitation;
    private TextView dewPoint;
    private TextView ultravioletInd;
    private TextView sunrise;
    private TextView sunset;
    private TextView moonrise;
    private TextView moonset;
    private TextView moonPhase;
    private static int index;

    public static DetailedFragment of(int value) {
        DetailedFragment fragment = new DetailedFragment();
        Bundle bundle = new Bundle();
        index = value;
        bundle.putInt(DetailedActivity.DETAILED_FOR, value);
        fragment.setArguments(bundle);
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DetailedFrgBinding.inflate(getLayoutInflater());
        findViews();
        Context context = getContext();
        setDataBase();
        daily = dataBase.itemDao().getItem().getDaily().get(index);
        recycler.setLayoutManager(new LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false));
        recycler.setAdapter(new DetailedAdapter(context, dataBase.itemDao().getItem().getHourly()));
        setTexts();
        return binding.getRoot();
    }

    private void setDataBase() {
        dataBase = DbApp.getDatabase();
    }

    private void findViews() {
        date = binding.dateDetailedTextView;
        recycler = binding.detailedRecycler;
        picture = binding.pictureDetailedImageView;
        minTemp = binding.minTemperatureDetailedTextView;
        maxTemp = binding.maxTemperatureDetailedTextView;
        description = binding.descriptionDetailedTextView;
        windSpeed = binding.windSpeedDetailedTextView;
        windDegree = binding.windDegreeDetailedTextView;
        windPicture = binding.detailedWindPicture;
        pressure = binding.atmosphericPressureDetailedTextView;
        humidity = binding.humidityDetailedTextView;
        clouds = binding.cloudsDetailedTextView;
        probOfPrecipitation = binding.probablyOfPrecipitationDetailedTextView;
        dewPoint = binding.dewPointDetailedTextView;
        ultravioletInd = binding.ultravioletIndexDetailedTextView;
        sunrise = binding.sunriseDetailedTextView;
        sunset = binding.sunsetDetailedTextView;
        moonrise = binding.moonriseDetailedTextView;
        moonset = binding.moonsetDetailedTextView;
        moonPhase = binding.moonPhaseDetailedTextView;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint({"SetTextI18n", "SimpleDateFormat", "DefaultLocale"})
    private void setTexts() {
        if (daily != null) {
            int windId = WindConverter.getDirection(daily.getWindDeg());
            ImageLoader.setIcon(picture, getString(R.string.path_for_download_icon)
                    + daily.getWeather().get(0).getIcon() + "@2x.png");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTS"));
            date.setText(getDateWithWeekDay(daily.getDt() * 1000));
            minTemp.setText(daily.getTemp().getNight() + " °C");
            maxTemp.setText(daily.getTemp().getMax() + " °C");
            description.setText(daily.getWeather().get(0).getDescription());
            windSpeed.setText(getResources().getString(R.string.wind_speed) + " " + daily.getWindSpeed()
                    + " " + getResources().getString(R.string.meter_sec));
            windDegree.setText(getResources().getString(R.string.wind_degree) + " "
                    + getString(windId));
            windPicture.setImageResource(WindConverter.getArrow(windId));
            pressure.setText(getResources().getString(R.string.pressure)
                    + " " + (String.format("%.1f", daily.getPressure() * 0.750064))
                    + " " + getResources().getString(R.string.mm_Hg));
            humidity.setText(getResources().getString(R.string.humidity) + " " + daily.getHumidity() + " %");
            clouds.setText(getResources().getString(R.string.clouds) + " " + daily.getClouds() + " %");
            probOfPrecipitation.setText(getResources().getString(R.string.probably_of_precipitation)
                    + " " + (String.format("%.0f", daily.getPop() * 100))+ " %");
            dewPoint.setText(getResources().getString(R.string.dew_point) + " " + daily.getDewPoint() + " °C");
            ultravioletInd.setText(getResources().getString(R.string.ultraviolet_index) + " " + daily.getUvi());
            simpleDateFormat = new SimpleDateFormat("HH:mm");
            sunrise.setText(getResources().getString(R.string.sunrise) + " "
                    + simpleDateFormat.format(new Date(daily.getSunrise() * 1000)));
            sunset.setText(getResources().getString(R.string.sunset) + " "
                    + simpleDateFormat.format(new Date(daily.getSunset() * 1000)));
            moonrise.setText(getResources().getString(R.string.moonrise) + " "
                    + simpleDateFormat.format(new Date(daily.getMoonrise() * 1000)));
            moonset.setText(getResources().getString(R.string.moonset) + " "
                    + simpleDateFormat.format(new Date(daily.getMoonset() * 1000)));
            moonPhase.setText(getString(R.string.moon_phase) + " " + daily.getMoonPhase());
        }
    }

    @SuppressLint({"SetTextI18n", "SimpleDateFormat"})
    private String getDateWithWeekDay(long timeValue) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTS"));
        Calendar calendar = Calendar.getInstance();
        Date dt = new Date(timeValue);
        calendar.setTime(dt);
        return simpleDateFormat.format(dt) + ", "
                + getString(WeekDayConverter.getDay(calendar.get(Calendar.DAY_OF_WEEK)));
    }
}
