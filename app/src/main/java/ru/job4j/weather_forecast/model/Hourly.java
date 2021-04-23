package ru.job4j.weather_forecast.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class Hourly {
    @SerializedName("dt")
    @Expose
    private final long dt;
    @SerializedName("temp")
    @Expose
    private final double temp;
    @SerializedName("pressure")
    @Expose
    private final int pressure;
    @SerializedName("clouds")
    @Expose
    private final int clouds;
    @SerializedName("visibility")
    @Expose
    private final int visibility;
    @SerializedName("wind_speed")
    @Expose
    private final double windSpeed;
    @SerializedName("wind_deg")
    @Expose
    private final double windDeg;
    @SerializedName("weather")
    @Expose
    private final List<HourlyWeather> weather;
    @SerializedName("pop")
    @Expose
    private final double pop;
    public Hourly(long dt, double temp, int pressure, int clouds, int visibility, double windSpeed,
                  int windDeg, HourlyWeather weather, double pop) {
        super();
        this.dt = dt;
        this.temp = temp;
        this.pressure = pressure;
        this.clouds = clouds;
        this.visibility = visibility;
        this.windSpeed = windSpeed;
        this.windDeg = windDeg;
        this.weather = Collections.singletonList(weather);
        this.pop = pop;
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
