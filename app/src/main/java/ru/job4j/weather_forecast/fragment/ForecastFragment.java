package ru.job4j.weather_forecast.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import ru.job4j.weather_forecast.databinding.ForecastFrgBinding;
import ru.job4j.weather_forecast.model.Item;

public class ForecastFragment extends Fragment {
    private ForecastFrgBinding binding;
    private DbHelper helper;
    private RecyclerView recycler;
    private String lang;
    private Context context;
    private TextView header;
    private double latitude;
    private double longitude;
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
        binding = ForecastFrgBinding.inflate(getLayoutInflater());
        Intent intent = Objects.requireNonNull(getActivity()).getIntent();
        latitude = intent.getDoubleExtra("lat", 54.5);
        longitude = intent.getDoubleExtra("lon", 39.5);
        context = getContext();
        helper = new DbHelper(context);
        lang = getString(R.string.lang);
        findViews();
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        update();
        return binding.getRoot();
    }
    private void findViews() {
        recycler = binding.forecastRecycler;
        header = binding.forecastHeaderTextView;
    }
    public void update() {
        ForecastLoader loader = new ForecastLoader();
        loader.loadForecast();
    }
    public class ForecastLoader {
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
            interceptor.setLevel(BuildConfig.DEBUG ?
                    HttpLoggingInterceptor.Level.BODY :
                    HttpLoggingInterceptor.Level.BASIC);
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
                        setAdapter();
                        header.setText(item.getLat() + ", " + item.getLon());
                    }
                }
                @SuppressLint("ShowToast")
                @Override
                public void onFailure(@NonNull Call<Item> call, @NonNull Throwable t) {
                    Log.d("TAG", "<<< " + t.getMessage() + " >>>");
                    setAdapter();
                }
            });
        }
        private void setAdapter() {
            recycler.setAdapter(new ForecastAdapter(
                    context, select, helper.getDailyList()));
        }
    }
}
