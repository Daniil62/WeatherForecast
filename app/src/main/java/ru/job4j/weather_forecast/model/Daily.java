package ru.job4j.weather_forecast.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.Collections;
import java.util.List;

public class Daily {
    private int id;
    @SerializedName("dt")
    @Expose
    private long dt;
    @SerializedName("sunrise")
    @Expose
    private long sunrise;
    @SerializedName("sunset")
    @Expose
    private long sunset;
    @SerializedName("moonrise")
    @Expose
    private long moonrise;
    @SerializedName("moonset")
    @Expose
    private long moonset;
    @SerializedName("moon_phase")
    @Expose
    private double moonPhase;
    @SerializedName("temp")
    @Expose
    private Temp temp;
    @SerializedName("pressure")
    @Expose
    private int pressure;
    @SerializedName("humidity")
    @Expose
    private int humidity;
    @SerializedName("dew_point")
    @Expose
    private double dewPoint;
    @SerializedName("wind_speed")
    @Expose
    private double windSpeed;
    @SerializedName("wind_deg")
    @Expose
    private int windDeg;
    @SerializedName("weather")
    @Expose
    private List<DailyWeather> weather;
    @SerializedName("clouds")
    @Expose
    private int clouds;
    @SerializedName("pop")
    @Expose
    private double pop;
    @SerializedName("uvi")
    @Expose
    private double uvi;
    public Daily() {
    }
    public Daily(long dt, long sunrise, long sunset, long moonrise, long moonset, double moonPhase,
                 Temp temp, int pressure, int humidity, double dewPoint, double windSpeed,
                 int windDeg, DailyWeather weather, int clouds, double pop, double uvi) {
        super();
        this.dt = dt;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.moonrise = moonrise;
        this.moonset = moonset;
        this.moonPhase = moonPhase;
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.dewPoint = dewPoint;
        this.windSpeed = windSpeed;
        this.windDeg = windDeg;
        this.weather = Collections.singletonList(weather);
        this.clouds = clouds;
        this.pop = pop;
        this.uvi = uvi;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public long getDt() {
        return dt;
    }
    public long getSunrise() {
        return sunrise;
    }
    public long getSunset() {
        return sunset;
    }
    public long getMoonrise() {
        return moonrise;
    }
    public long getMoonset() {
        return moonset;
    }
    public double getMoonPhase() {
        return moonPhase;
    }
    public Temp getTemp() {
        return temp;
    }
    public int getPressure() {
        return pressure;
    }
    public int getHumidity() {
        return humidity;
    }
    public double getDewPoint() {
        return dewPoint;
    }
    public double getWindSpeed() {
        return windSpeed;
    }
    public int getWindDeg() {
        return windDeg;
    }
    public DailyWeather getWeather() {
        return weather.get(0);
    }
    public int getClouds() {
        return clouds;
    }
    public double getPop() {
        return pop;
    }
    public double getUvi() {
        return uvi;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Daily.class.getName()).append('@').append(Integer.toHexString(
                System.identityHashCode(this))).append('[');
        sb.append("dt");
        sb.append('=');
        sb.append(this.dt);
        sb.append(',');
        sb.append("sunrise");
        sb.append('=');
        sb.append(this.sunrise);
        sb.append(',');
        sb.append("sunset");
        sb.append('=');
        sb.append(this.sunset);
        sb.append(',');
        sb.append("moonrise");
        sb.append('=');
        sb.append(this.moonrise);
        sb.append(',');
        sb.append("moonset");
        sb.append('=');
        sb.append(this.moonset);
        sb.append(',');
        sb.append("temp");
        sb.append('=');
        sb.append(((this.temp == null)?"<null>":this.temp));
        sb.append(',');
        sb.append("pressure");
        sb.append('=');
        sb.append(this.pressure);
        sb.append(',');
        sb.append("humidity");
        sb.append('=');
        sb.append(this.humidity);
        sb.append(',');
        sb.append("dewPoint");
        sb.append('=');
        sb.append(this.dewPoint);
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
        sb.append("clouds");
        sb.append('=');
        sb.append(this.clouds);
        sb.append(',');
        sb.append("pop");
        sb.append('=');
        sb.append(this.pop);
        sb.append(',');
        sb.append("uvi");
        sb.append('=');
        sb.append(this.uvi);
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
}
