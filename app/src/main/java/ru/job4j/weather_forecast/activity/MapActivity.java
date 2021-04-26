package ru.job4j.weather_forecast.activity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.widget.Toast;

import org.osmdroid.api.IGeoPoint;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.Projection;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import ru.job4j.weather_forecast.BuildConfig;
import ru.job4j.weather_forecast.R;
import ru.job4j.weather_forecast.activator.ForecastActivator;

public class MapActivity extends AppCompatActivity {
    private MapView map;
    private boolean tumbler = true;
    private static long backPressed;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_fragment);
        setConfiguration();
        setMap();
        setController(map);
    }
    @Override
    public void onBackPressed() {
        if (backPressed + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            Toast.makeText(MapActivity.this,
                    getString(R.string.to_exit_toast), Toast.LENGTH_LONG).show();
        }
        backPressed = System.currentTimeMillis();
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int actionType = ev.getAction();
        switch (actionType) {
            case (MotionEvent.ACTION_MOVE) : {
                tumbler = false;
                break;
            }
            case (MotionEvent.ACTION_UP) : {
                if (tumbler) {
                    startForecast(ev);
                }
                break;
            }
        }
        return false;
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int actionType = ev.getAction();
        switch (actionType) {
            case (MotionEvent.ACTION_MOVE) : {
                tumbler = false;
                break;
            }
            case (MotionEvent.ACTION_UP) : {
                tumbler = true;
                break;
            }
        }
        return super.dispatchTouchEvent(ev);
    }
    private void startForecast(MotionEvent ev) {
        Projection projection = map.getProjection();
        IGeoPoint point = projection.fromPixels((int) ev.getX(), (int) ev.getY());
        Intent intent = new Intent(this, ForecastActivator.class);
        intent.putExtra("lat", point.getLatitude());
        intent.putExtra("lon", point.getLongitude());
        startActivity(intent);
    }
    private void setConfiguration() {
        Configuration.getInstance().load(this,
                PreferenceManager.getDefaultSharedPreferences(this));
        Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);
    }
    private void setMap() {
        map = findViewById(R.id.map);
        map.setMultiTouchControls(true);
        map.setTileSource(TileSourceFactory.MAPNIK);
    }
    private void setController(MapView map) {
        if (map != null) {
            GeoPoint geoPoint = new GeoPoint(54.5, 39.5);
            MapController controller = (MapController) map.getController();
            controller.setCenter(geoPoint);
            controller.setZoom(6);
        }
    }
}
