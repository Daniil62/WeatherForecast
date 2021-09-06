package ru.job4j.weather_forecast.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
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
import ru.job4j.weather_forecast.tools.ForecastRepository;

public class MapActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private static long backPressed;
    private MapView map;
    private IGeoPoint center;
    private double zoomLevel;
    private final String LAT = "lat";
    private final String LON = "lon";
    private final String ZOOM = "zoom";
    private ForecastRepository repository;

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_fragment);
        preferences = getSharedPreferences("map_preferences", MODE_PRIVATE);
        editor = preferences.edit();
        setConfiguration();
        setMap();
        center = restoreCoordinates();
        zoomLevel = restoreZoomLevel();
        repository = ForecastRepository.getInstance();
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
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            if (center.equals(map.getMapCenter())) {
                startForecast(ev);
            }
            center = map.getMapCenter();
        }
        return super.dispatchTouchEvent(ev);
    }

    private void startForecast(MotionEvent ev) {
        Projection projection = map.getProjection();
        IGeoPoint point = projection.fromPixels((int) ev.getX(), (int) ev.getY());
        Intent intent = new Intent(this, ForecastActivator.class);
        repository.getForecast(getString(R.string.lang),
                point.getLatitude(), point.getLongitude(), getString(R.string.key));
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
        map.setMaxZoomLevel(17.0);
        map.setMinZoomLevel(2.0);
        map.setTileSource(TileSourceFactory.MAPNIK);
    }

    private void setController(MapView map) {
        if (map != null) {
            MapController controller = (MapController) map.getController();
            controller.setCenter(center);
            controller.setZoom(zoomLevel);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveCoordinates();
        saveZoomLevel();
    }

    private void saveCoordinates() {
        editor.putLong(LAT, Double.doubleToLongBits(center.getLatitude()));
        editor.putLong(LON, Double.doubleToLongBits(center.getLongitude()));
        editor.apply();
    }

    private GeoPoint restoreCoordinates() {
        return new GeoPoint(
                Double.longBitsToDouble(
                        preferences.getLong(LAT, Double.doubleToLongBits(54.5))),
                Double.longBitsToDouble(
                        preferences.getLong(LON, Double.doubleToLongBits(39.5))));
    }

    private void saveZoomLevel() {
        editor.putLong(ZOOM, Double.doubleToLongBits(map.getZoomLevelDouble())).apply();
    }

    private double restoreZoomLevel() {
        return Double.longBitsToDouble(
                preferences.getLong(ZOOM, Double.doubleToLongBits(6.0)));
    }
}
