package ru.job4j.weather_forecast.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.job4j.weather_forecast.adapter.ForecastAdapter;
import ru.job4j.weather_forecast.data_base.DbApp;
import ru.job4j.weather_forecast.data_base.ForecastDataBase;
import ru.job4j.weather_forecast.databinding.ForecastFrgBinding;

public class ForecastFragment extends Fragment {
    private ForecastDataBase dataBase;
    private ForecastFrgBinding binding;
    private RecyclerView recycler;
    private Context context;
    private TextView header;
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
        context = getContext();
        setDatabase();
        findViews();
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        observeOnDataBase();
        return binding.getRoot();
    }
    private void setDatabase() {
        dataBase = DbApp.getDatabase();
    }

    private void findViews() {
        recycler = binding.forecastRecycler;
        header = binding.forecastHeaderTextView;
    }

    private void observeOnDataBase() {
        dataBase.itemDao().getFlowableItem()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> {
                    recycler.setAdapter(new ForecastAdapter(
                            context, select, item.getDaily()));
                    String coordinates = item.getLat() + ", " + item.getLon();
                    header.setText(coordinates);
                }).isDisposed();
    }
}
