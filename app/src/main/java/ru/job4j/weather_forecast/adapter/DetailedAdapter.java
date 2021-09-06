package ru.job4j.weather_forecast.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import ru.job4j.weather_forecast.R;
import ru.job4j.weather_forecast.databinding.HourlyRecyclerModuleBinding;
import ru.job4j.weather_forecast.model.Hourly;
import ru.job4j.weather_forecast.tools.ImageLoader;
import ru.job4j.weather_forecast.tools.WindConverter;

public class DetailedAdapter extends RecyclerView.Adapter<DetailedAdapter.DetailedHolder>{

    private HourlyRecyclerModuleBinding binding;
    private final Context context;
    private ImageView picture;
    private TextView time;
    private TextView temperature;
    private TextView visibility;
    private TextView windDirection;
    private ImageView windPicture;
    private TextView windSpeed;
    private TextView pressure;
    private TextView clouds;
    private TextView pop;
    private final List<Hourly> list;

    public DetailedAdapter(Context context, List<Hourly> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DetailedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DetailedHolder(HourlyRecyclerModuleBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull DetailedHolder holder, int position) {
        findViews();
        setViews(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private void findViews() {
        picture = binding.pictureDetailedHourlyImageView;
        time = binding.timeDetailedHourlyTextView;
        windSpeed = binding.hourlyWindSpeedDetailedTextView;
        windDirection = binding.hourlyWindDirectionDetailedTextView;
        windPicture = binding.hourlyWindPicture;
        temperature = binding.hourlyTempDetailedTextView;
        visibility = binding.hourlyVisibilityDetailedTextView;
        clouds = binding.hourlyCloudDetailedTextView;
        pressure = binding.hourlyPressureDetailedTextView;
        pop = binding.hourlyPopDetailedTextView;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint({"SetTextI18n", "SimpleDateFormat", "DefaultLocale"})
    private void setViews(Hourly hourly) {
        int windId = WindConverter.getDirection(hourly.getWindDeg());
        ImageLoader.setIcon(picture, context.getString(R.string.path_for_download_icon)
                + hourly.getWeather().get(0).getIcon() + "@2x.png");
        SimpleDateFormat sdf = new SimpleDateFormat("dd / HH:mm");
        time.setText(sdf.format(new Date(hourly.getDt() * 1000)));
        temperature.setText(hourly.getTemp() + " °C");
        visibility.setText(hourly.getVisibility() + " " + context.getString(R.string.meter));
        windSpeed.setText(hourly.getWindSpeed() + " " + context.getString(R.string.meter_sec));
        windDirection.setText(context.getString(windId));
        windPicture.setImageResource(WindConverter.getArrow(windId));
        clouds.setText(hourly.getClouds() + " %");
        pressure.setText(String.format("%.1f",hourly.getPressure() * 0.750064)
                + " " + context.getString(R.string.mm_Hg));
        pop.setText(String.format("%.0f", hourly.getPop() * 100) + " %");
    }

    public class DetailedHolder extends RecyclerView.ViewHolder {
        public DetailedHolder(@NonNull HourlyRecyclerModuleBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
