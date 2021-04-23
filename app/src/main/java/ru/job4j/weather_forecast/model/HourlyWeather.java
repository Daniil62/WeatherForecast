package ru.job4j.weather_forecast.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HourlyWeather {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("icon")
    @Expose
    private final String icon;
    public HourlyWeather(String icon) {
        super();
        this.icon = icon;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getIcon() {
        return icon;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(HourlyWeather .class.getName()).append('@').append(Integer.toHexString(
                System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(this.id);
        sb.append(',');
        sb.append(',');
        sb.append("icon");
        sb.append('=');
        sb.append(((this.icon == null) ? "<null>" : this.icon));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
}
