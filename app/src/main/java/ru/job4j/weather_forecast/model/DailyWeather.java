package ru.job4j.weather_forecast.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DailyWeather {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("description")
    @Expose
    private final String description;
    @SerializedName("icon")
    @Expose
    private final String icon;

    public DailyWeather(String description, String icon) {
        super();
        this.description = description;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public String getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DailyWeather.class.getName()).append('@').append(Integer.toHexString(
                System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(this.id);
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null) ? "<null>" : this.description));
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
