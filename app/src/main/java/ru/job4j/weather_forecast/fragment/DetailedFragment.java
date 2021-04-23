package ru.job4j.weather_forecast.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ru.job4j.weather_forecast.R;
import ru.job4j.weather_forecast.activity.DetailedActivity;
import ru.job4j.weather_forecast.adapter.DetailedAdapter;
import ru.job4j.weather_forecast.data_base.DbHelper;
import ru.job4j.weather_forecast.model.Daily;
import ru.job4j.weather_forecast.tools.ImageLoader;
import ru.job4j.weather_forecast.tools.WindConverter;

public class DetailedFragment extends Fragment {
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
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detailed_frg, container, false);
        findViews(view);
        Context context = getContext();
        DbHelper helper = new DbHelper(context);
        daily = helper.getDaily(index);
        recycler.setLayoutManager(new LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false));
        recycler.setAdapter(new DetailedAdapter(context, helper.getHourlyList()));
        setTexts();
        return view;
    }
    private void findViews(View view) {
        date = view.findViewById(R.id.date_detailed_textView);
        recycler = view.findViewById(R.id.detailed_recycler);
        picture = view.findViewById(R.id.picture_detailed_imageView);
        minTemp = view.findViewById(R.id.min_temperature_detailed_textView);
        maxTemp = view.findViewById(R.id.max_temperature_detailed_textView);
        description = view.findViewById(R.id.description_detailed_textView);
        windSpeed = view.findViewById(R.id.wind_speed_detailed_textView);
        windDegree = view.findViewById(R.id.wind_degree_detailed_textView);
        windPicture = view.findViewById(R.id.detailed_wind_picture);
        pressure = view.findViewById(R.id.atmospheric_pressure_detailed_textView);
        humidity = view.findViewById(R.id.humidity_detailed_textView);
        clouds = view.findViewById(R.id.clouds_detailed_textView);
        probOfPrecipitation = view.findViewById(R.id.probably_of_precipitation_detailed_textView);
        dewPoint = view.findViewById(R.id.dew_point_detailed_textView);
        ultravioletInd = view.findViewById(R.id.ultraviolet_index_detailed_textView);
        sunrise = view.findViewById(R.id.sunrise_detailed_textView);
        sunset = view.findViewById(R.id.sunset_detailed_textView);
        moonrise = view.findViewById(R.id.moonrise_detailed_textView);
        moonset = view.findViewById(R.id.moonset_detailed_textView);
        moonPhase = view.findViewById(R.id.moon_phase_detailed_textView);
    }
    @SuppressLint({"SetTextI18n", "SimpleDateFormat", "DefaultLocale"})
    private void setTexts() {
        if (daily != null) {
            int windId = WindConverter.getDirection(daily.getWindDeg());
            ImageLoader.setIcon(picture, getString(R.string.path_for_download_icon)
                    + daily.getWeather().getIcon() + "@2x.png");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTS"));
            int PLUS_THREE_HOURS = 10800000;
            date.setText(simpleDateFormat.format(new Date(daily.getDt() * 1000 + PLUS_THREE_HOURS)));
            minTemp.setText(daily.getTemp().getNight() + " °C");
            maxTemp.setText(daily.getTemp().getMax() + " °C");
            description.setText(daily.getWeather().getDescription());
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
                    + simpleDateFormat.format(new Date(daily.getSunrise() * 1000 + PLUS_THREE_HOURS)));
            sunset.setText(getResources().getString(R.string.sunset) + " "
                    + simpleDateFormat.format(new Date(daily.getSunset() * 1000 + PLUS_THREE_HOURS)));
            moonrise.setText(getResources().getString(R.string.moonrise) + " "
                    + simpleDateFormat.format(new Date(daily.getMoonrise() * 1000 + PLUS_THREE_HOURS)));
            moonset.setText(getResources().getString(R.string.moonset) + " "
                    + simpleDateFormat.format(new Date(daily.getMoonset() * 1000 + PLUS_THREE_HOURS)));
            moonPhase.setText(getString(R.string.moon_phase) + " " + daily.getMoonPhase());
        }
    }
}
