package ru.job4j.weather_forecast.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import ru.job4j.weather_forecast.databinding.ForecastModuleBinding;
import ru.job4j.weather_forecast.fragment.ForecastFragment;
import ru.job4j.weather_forecast.model.Daily;
import ru.job4j.weather_forecast.tools.ImageLoader;
import ru.job4j.weather_forecast.tools.WeekDayConverter;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastHolder> {
    private ForecastModuleBinding binding;
    private final ForecastFragment.ForecastSelect select;
    private final List<Daily> list;
    private final Context context;
    public ForecastAdapter(Context context,
                           ForecastFragment.ForecastSelect select, List<Daily> list) {
        this.context = context;
        this.select = select;
        this.list = list;
    }
    @NonNull
    @Override
    public ForecastHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ForecastHolder(ForecastModuleBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
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
    public class ForecastHolder extends RecyclerView.ViewHolder {
        private TextView date;
        private ImageView image;
        private TextView dayTemp;
        private TextView nightTemp;
        public ForecastHolder(@NonNull ForecastModuleBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
            findViews();
        }
        private void findViews() {
            date = binding.forecastModuleHeaderTextView;//view.findViewById(R.id.forecast_module_header_textView);
            image = binding.forecastModuleImageView;//view.findViewById(R.id.forecast_module_imageView);
            nightTemp = binding.forecastModuleNightTempTextView;//view.findViewById(R.id.forecast_module_night_temp_textView);
            dayTemp = binding.forecastModuleDayTempTextView;//view.findViewById(R.id.forecast_module_day_temp_textView);
        }
        @RequiresApi(api = Build.VERSION_CODES.N)
        private void bind(Daily daily) {
            date.setText(getDateWithWeekDay(daily.getDt() * 1000));
            String PATH = "http://openweathermap.org/img/wn/";
            ImageLoader.setIcon(image, PATH + daily.getWeather().getIcon() + "@2x.png");
            nightTemp.setText(String.valueOf(daily.getTemp().getNight()));
            dayTemp.setText(String.valueOf(daily.getTemp().getMax()));
        }
        @SuppressLint({"SetTextI18n", "SimpleDateFormat"})
        private String getDateWithWeekDay(long timeValue) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTS"));
            Calendar calendar = Calendar.getInstance();
            Date dt = new Date(timeValue);
            calendar.setTime(dt);
            return simpleDateFormat.format(dt) + ", "
                    + context.getString(WeekDayConverter.getDay(calendar.get(Calendar.DAY_OF_WEEK)));
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
