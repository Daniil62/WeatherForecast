package ru.job4j.weather_forecast.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import ru.job4j.weather_forecast.tools.FieldConverter;

@Entity(tableName = "hourly_tab")
public class Hourly {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @SerializedName("dt")
    @Expose
    private long dt;
    @SerializedName("temp")
    @Expose
    private double temp;
    @SerializedName("pressure")
    @Expose
    private int pressure;
    @SerializedName("clouds")
    @Expose
    private int clouds;
    @SerializedName("visibility")
    @Expose
    private int visibility;
    @SerializedName("wind_speed")
    @Expose
    private double windSpeed;
    @SerializedName("wind_deg")
    @Expose
    private double windDeg;
    @SerializedName("weather")
    @Expose
    @TypeConverters(FieldConverter.class)
    private List<HourlyWeather> weather;
    @SerializedName("pop")
    @Expose
    private double pop;

    public Hourly(int id, long dt, double temp, int pressure, int clouds, int visibility,
                  double windSpeed, double windDeg, List<HourlyWeather> weather, double pop) {
        this.id = id;
        this.dt = dt;
        this.temp = temp;
        this.pressure = pressure;
        this.clouds = clouds;
        this.visibility = visibility;
        this.windSpeed = windSpeed;
        this.windDeg = windDeg;
        this.weather = weather;
        this.pop = pop;
    }

    // getters
    public int getId() {
        return id;
    }
    public long getDt() {
        return dt;
    }
    public double getTemp() {
        return temp;
    }
    public int getPressure() {
        return pressure;
    }
    public int getClouds() {
        return clouds;
    }
    public int getVisibility() {
        return visibility;
    }
    public double getWindSpeed() {
        return windSpeed;
    }
    public double getWindDeg() {
        return windDeg;
    }
    public List<HourlyWeather> getWeather() {
        return weather;
    }
    public double getPop() {
        return pop;
    }

    //setters
    public void setId(int id) {
        this.id = id;
    }
    public void setDt(long dt) {
        this.dt = dt;
    }
    public void setTemp(double temp) {
        this.temp = temp;
    }
    public void setPressure(int pressure) {
        this.pressure = pressure;
    }
    public void setClouds(int clouds) {
        this.clouds = clouds;
    }
    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }
    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }
    public void setWindDeg(double windDeg) {
        this.windDeg = windDeg;
    }
    public void setWeather(List<HourlyWeather> weather) {
        this.weather = weather;
    }
    public void setPop(double pop) {
        this.pop = pop;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Hourly.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("dt");
        sb.append('=');
        sb.append(this.dt);
        sb.append(',');
        sb.append("temp");
        sb.append('=');
        sb.append(this.temp);
        sb.append(',');
        sb.append("pressure");
        sb.append('=');
        sb.append(this.pressure);
        sb.append(',');
        sb.append("clouds");
        sb.append('=');
        sb.append(this.clouds);
        sb.append(',');
        sb.append("visibility");
        sb.append('=');
        sb.append(this.visibility);
        sb.append(',');
        sb.append("windSpeed");
        sb.append('=');
        sb.append(this.windSpeed);
        sb.append(',');
        sb.append("windDeg");
        sb.append('=');
        sb.append(this.windDeg);
        sb.append(',');
        sb.append("weather");
        sb.append('=');
        sb.append(((this.weather == null)?"<null>":this.weather));
        sb.append(',');
        sb.append("pop");
        sb.append('=');
        sb.append(this.pop);
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
}
