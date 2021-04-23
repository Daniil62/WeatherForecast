package ru.job4j.weather_forecast.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Item {
    @SerializedName("lat")
    @Expose
    private final double lat;
    @SerializedName("lon")
    @Expose
    private final double lon;
    @SerializedName("timezone")
    @Expose
    private final String timezone;
    @SerializedName("timezone_offset")
    @Expose
    private final int timezoneOffset;
    @SerializedName("hourly")
    @Expose
    private final List<Hourly> hourly;
    @SerializedName("daily")
    @Expose
    private final List<Daily> daily;
    public Item(double lat, double lon, String timezone, int timezoneOffset, List<Hourly> hourly,
                List<Daily> daily) {
        super();
        this.lat = lat;
        this.lon = lon;
        this.timezone = timezone;
        this.timezoneOffset = timezoneOffset;
        this.hourly = hourly;
        this.daily = daily;
    }
    public double getLat() {
        return lat;
    }
    public double getLon() {
        return lon;
    }
    public String getTimezone() {
        return timezone;
    }
    public List<Hourly> getHourly() {
        return hourly;
    }
    public List<Daily> getDaily() {
        return daily;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Item.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("lat");
        sb.append('=');
        sb.append(this.lat);
        sb.append(',');
        sb.append("lon");
        sb.append('=');
        sb.append(this.lon);
        sb.append(',');
        sb.append("timezone");
        sb.append('=');
        sb.append(((this.timezone == null)?"<null>":this.timezone));
        sb.append(',');
        sb.append("timezoneOffset");
        sb.append('=');
        sb.append(this.timezoneOffset);
        sb.append(',');
        sb.append("hourly");
        sb.append('=');
        sb.append(((this.hourly == null)?"<null>":this.hourly));
        sb.append(',');
        sb.append("daily");
        sb.append('=');
        sb.append(((this.daily == null)?"<null>":this.daily));

        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
}
