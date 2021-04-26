package ru.job4j.weather_forecast.fragment;

import android.annotation.SuppressLint;
//import android.app.AlarmManager;
//import android.app.AlertDialog;
//import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
//import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.RadioGroup;
import android.widget.TextView;
import java.util.Objects;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.job4j.weather_forecast.BuildConfig;
import ru.job4j.weather_forecast.R;
import ru.job4j.weather_forecast.adapter.ForecastAdapter;
import ru.job4j.weather_forecast.api.JsonWeatherApi;
import ru.job4j.weather_forecast.data_base.DbHelper;
import ru.job4j.weather_forecast.model.Item;
//import ru.job4j.weather_forecast.service.ForecastPullService;

public class ForecastFragment extends Fragment {
    private static DbHelper helper;
    private static RecyclerView recycler;
    private static String lang;
//    private ImageView settings;
//    private int period;
    @SuppressLint("StaticFieldLeak")
    private static TextView header;
    private static double latitude;
    private static double longitude;
    public interface ForecastSelect {
        void selected(int index);
    }
    private static ForecastSelect select;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        select = (ForecastSelect) context;
    }
    @Override
    public void onDetach() {
        super.onDetach();
        select = null;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.forecast_frg, container, false);
        Intent intent = Objects.requireNonNull(getActivity()).getIntent();
        latitude = intent.getDoubleExtra("lat", 54.5);
        longitude = intent.getDoubleExtra("lon", 39.5);
        helper = new DbHelper(getContext());
        lang = getString(R.string.lang);
        findViews(view);
//        settings.setOnClickListener(v -> showSettingsDialog());
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        update();
        return view;
    }
    private void findViews(View view) {
        recycler = view.findViewById(R.id.forecast_recycler);
//        settings = view.findViewById(R.id.forecast_settings_imageView);
        header = view.findViewById(R.id.forecast_header_textView);
    }
    public void update() {
        ForecastLoader loader = new ForecastLoader();
        loader.loadForecast();
//        Intent intent = new Intent(getActivity(), ForecastPullService.class);
//        AlarmManager alarmManager = (AlarmManager) getActivity()
//                .getSystemService(Context.ALARM_SERVICE);
//        PendingIntent pendingIntent = PendingIntent.getService(getContext(),
//                0, intent, 0);
//        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
//                SystemClock.elapsedRealtime(), period, pendingIntent);
//        ForecastPullService.enqueueWork(getContext(), intent);
    }
//    private void showSettingsDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        @SuppressLint("InflateParams") View view = LayoutInflater.from(getContext())
//                .inflate(R.layout.settings_dialog, null);
//        builder.setView(view);
//        builder.setPositiveButton("Ok", (dialog, which) -> {
//            RadioGroup variants = ((AlertDialog) dialog).findViewById(R.id.settings_radioGroup);
//            int result = 0;
//            switch (variants.getCheckedRadioButtonId()) {
//                case (R.id.radioButton1): {
//                    result = 3600000;
//                    break;
//                }
//                case (R.id.radioButton2): {
//                    result = 10800000;
//                    break;
//                }
//                case (R.id.radioButton3): {
//                    result = 18000000;
//                    break;
//                }
//                case (R.id.radioButton4): {
//                    result = 86400000;
//                    break;
//                }
//            }
//            period = result;
//        });
//        builder.show();
//    }
    public static class ForecastLoader {
        private final String PATH = "https://api.openweathermap.org/data/2.5/";
        private final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        private final OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(interceptor);
        private final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PATH)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
        private final JsonWeatherApi weatherApi = retrofit.create(JsonWeatherApi.class);
        private void levelSelector(HttpLoggingInterceptor interceptor) {
            if (BuildConfig.DEBUG) {
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            } else {
                interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            }
        }
        public void loadForecast() {
            levelSelector(interceptor);
            String KEY = "f27e570b2e9a73607f080159fe8c117f";
            Call<Item> getItems = weatherApi.getForecast(lang, latitude, longitude, KEY);
            getItems.enqueue(new Callback<Item>() {
                @SuppressLint({"SetTextI18n", "SimpleDateFormat"})
                @Override
                public void onResponse(@NonNull Call<Item> call, @NonNull Response<Item> response) {
                    if (response.body() != null) {
                        Item item = response.body();
                        helper.addItem(item);
                        recycler.setAdapter(new ForecastAdapter(select, helper.getDailyList()));
                        header.setText(item.getLat() + ", " + item.getLon());
                    }
                }
                @Override
                public void onFailure(@NonNull Call<Item> call, @NonNull Throwable t) {
                    Log.d("TAG", "<<< " + t.getMessage() + " >>>");
                }
            });
        }
    }
}
