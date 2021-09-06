package ru.job4j.weather_forecast.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import ru.job4j.weather_forecast.tools.FieldConverter;

@Entity(tableName = "item_tab")
public class Item {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @SerializedName("lat")
    @Expose
    private double lat;
    @SerializedName("lon")
    @Expose
    private double lon;
    @SerializedName("timezone")
    @Expose
    private String timezone;
    @SerializedName("timezone_offset")
    @Expose
    private int timezoneOffset;
    @SerializedName("hourly")
    @Expose
    @TypeConverters(FieldConverter.class)
    private List<Hourly> hourly;
    @SerializedName("daily")
    @Expose
    @TypeConverters(FieldConverter.class)
    private List<Daily> daily;

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

 //  getters
    public int getId() {
        return id;
    }
    public double getLat() {
        return lat;
    }
    public double getLon() {
        return lon;
    }
    public int getTimezoneOffset() {
        return timezoneOffset;
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

 //  setters
    public void setId(int id) {
        this.id = id;
    }
    public void setLat(double lat) {
        this.lat = lat;
    }
    public void setLon(double lon) {
        this.lon = lon;
    }
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
    public void setTimezoneOffset(int timezoneOffset) {
        this.timezoneOffset = timezoneOffset;
    }
    public void setHourly(List<Hourly> hourly) {
        this.hourly = hourly;
    }
    public void setDaily(List<Daily> daily) {
        this.daily = daily;
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
