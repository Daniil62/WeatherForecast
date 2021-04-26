package ru.job4j.weather_forecast.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ru.job4j.weather_forecast.R;
import ru.job4j.weather_forecast.fragment.ForecastFragment;
import ru.job4j.weather_forecast.model.Daily;
import ru.job4j.weather_forecast.tools.ImageLoader;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastHolder> {
    private final ForecastFragment.ForecastSelect select;
    private final List<Daily> list;
    public ForecastAdapter(ForecastFragment.ForecastSelect select, List<Daily> list) {
        this.select = select;
        this.list = list;
    }
    @NonNull
    @Override
    public ForecastHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ForecastHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.forecast_module, parent, false));
    }
    @Override
    public void onBindViewHolder(@NonNull ForecastHolder holder, int position) {
        Daily daily = list.get(position);
        holder.bind(daily);
        holder.itemView.setOnClickListener(v -> select.selected(daily.getId()));
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class ForecastHolder extends RecyclerView.ViewHolder {
        private TextView date;
        private ImageView image;
        private TextView dayTemp;
        private TextView nightTemp;
        public ForecastHolder(@NonNull View itemView) {
            super(itemView);
            findViews(itemView);
        }
        private void findViews(View view) {
            date = view.findViewById(R.id.forecast_module_header_textView);
            image = view.findViewById(R.id.forecast_module_imageView);
            nightTemp = view.findViewById(R.id.forecast_module_night_temp_textView);
            dayTemp = view.findViewById(R.id.forecast_module_day_temp_textView);
        }
        private void bind(Daily daily) {
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTS"));
            date.setText(simpleDateFormat.format(new Date(daily.getDt() * 1000)));
            String PATH = "http://openweathermap.org/img/wn/";
            ImageLoader.setIcon(image, PATH + daily.getWeather().getIcon() + "@2x.png");
            nightTemp.setText(String.valueOf(daily.getTemp().getNight()));
            dayTemp.setText(String.valueOf(daily.getTemp().getMax()));
        }
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
}
