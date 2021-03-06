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
import ru.job4j.weather_forecast.R;
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ForecastHolder holder, int position) {
        Daily daily = list.get(position);
        holder.bind(daily);
        holder.itemView.setOnClickListener(v -> select.selected(position));
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
            date = binding.forecastModuleHeaderTextView;
            image = binding.forecastModuleImageView;
            nightTemp = binding.forecastModuleNightTempTextView;
            dayTemp = binding.forecastModuleDayTempTextView;
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        private void bind(Daily daily) {
            date.setText(getDateWithWeekDay(daily.getDt() * 1000));
            ImageLoader.setIcon(image, context.getString(R.string.path_for_download_icon)
                    + daily.getWeather().get(0).getIcon() + "@2x.png");
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
