package ru.job4j.weather_forecast.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import ru.job4j.weather_forecast.data_base.Constants;
import ru.job4j.weather_forecast.tools.FieldConverter;

@Entity(tableName = Constants.DAILY_TAB)
public class Daily {

    @PrimaryKey(autoGenerate = true)
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
    @TypeConverters(FieldConverter.class)
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
    @TypeConverters(FieldConverter.class)
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

    public Daily(int id, long dt, long sunrise, long sunset, long moonrise, long moonset, double moonPhase,
                 Temp temp, int pressure, int humidity, double dewPoint, double windSpeed,
                 int windDeg, List<DailyWeather> weather, int clouds, double pop, double uvi) {
        this.id = id;
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
        this.weather = weather;
        this.clouds = clouds;
        this.pop = pop;
        this.uvi = uvi;
    }

    // getters
    public int getId() {
        return id;
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
    public List<DailyWeather> getWeather() {
        return weather;
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

    //setters
    public void setId(int id) {
        this.id = id;
    }
    public void setDt(long dt) {
        this.dt = dt;
    }
    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }
    public void setSunset(long sunset) {
        this.sunset = sunset;
    }
    public void setMoonrise(long moonrise) {
        this.moonrise = moonrise;
    }
    public void setMoonset(long moonset) {
        this.moonset = moonset;
    }
    public void setMoonPhase(double moonPhase) {
        this.moonPhase = moonPhase;
    }
    public void setTemp(Temp temp) {
        this.temp = temp;
    }
    public void setPressure(int pressure) {
        this.pressure = pressure;
    }
    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
    public void setDewPoint(double dewPoint) {
        this.dewPoint = dewPoint;
    }
    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }
    public void setWindDeg(int windDeg) {
        this.windDeg = windDeg;
    }
    public void setWeather(List<DailyWeather> weather) {
        this.weather = weather;
    }
    public void setClouds(int clouds) {
        this.clouds = clouds;
    }
    public void setPop(double pop) {
        this.pop = pop;
    }
    public void setUvi(double uvi) {
        this.uvi = uvi;
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
