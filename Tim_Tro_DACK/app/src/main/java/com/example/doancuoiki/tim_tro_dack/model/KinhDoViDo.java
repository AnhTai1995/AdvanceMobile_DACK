package com.example.doancuoiki.tim_tro_dack.model;

/**
 * Created by asus on 1/20/2017.
 */

public class KinhDoViDo {
    private double lat;
    private double lng;

    public KinhDoViDo(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }
}
