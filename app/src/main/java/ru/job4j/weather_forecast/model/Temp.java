package ru.job4j.weather_forecast.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Temp {
//    @SerializedName("day")
//    @Expose
//    private double day;
//    @SerializedName("min")
//    @Expose
//    private double min;
    @SerializedName("max")
    @Expose
    private final double max;
    @SerializedName("night")
    @Expose
    private final double night;
//    @SerializedName("eve")
//    @Expose
//    private double eve;
//    @SerializedName("morn")
//    @Expose
//    private double morn;
    public Temp(double max, double night) {
        super();
        this.max = max;
        this.night = night;
    }
//    public Temp(double day, double min, double max, double night, double eve, double morn) {
//        super();
//        this.day = day;
//        this.min = min;
//        this.max = max;
//        this.night = night;
//        this.eve = eve;
//        this.morn = morn;
//    }
 //   public double getDay() {
 //       return day;
 //   }
 //   public double getMin() {
 //       return min;
 //   }
    public double getMax() {
        return max;
    }
    public double getNight() {
        return night;
    }
 //   public double getEve() {
 //       return eve;
 //   }
 //   public double getMorn() {
 //       return morn;
 //   }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Temp.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
//        sb.append("day");
//        sb.append('=');
//        sb.append(((this.day == 0)?"<null>":this.day));
//        sb.append(',');
//        sb.append("min");
//        sb.append('=');
//        sb.append(((this.min == 0)?"<null>":this.min));
//        sb.append(',');
        sb.append("max");
        sb.append('=');
        sb.append(((this.max == 0)?"<null>":this.max));
        sb.append(',');
        sb.append("night");
        sb.append('=');
        sb.append(((this.night == 0)?"<null>":this.night));
        sb.append(',');
//        sb.append("eve");
//        sb.append('=');
//        sb.append(((this.eve == 0)?"<null>":this.eve));
//        sb.append(',');
//        sb.append("morn");
//        sb.append('=');
//        sb.append(((this.morn == 0)?"<null>":this.morn));
//        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
}
