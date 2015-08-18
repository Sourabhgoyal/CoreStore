package com.sourabh.singletons;

import java.io.Serializable;

public class Location implements Serializable {
    private static Location location = new Location();
    String country;
    String state;
    String city;
    String locality;
    String gpsLat;
    String gpsLon;

    private Location() {

    }

    public static synchronized Location getInstance() {
        synchronized (location) {
            if (location == null) {
                location = new Location();
            }
            return location;
        }
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getGpsLat() {
        return gpsLat;
    }

    public void setGpsLat(String gpsLat) {
        this.gpsLat = gpsLat;
    }

    public String getGpsLon() {
        return gpsLon;
    }

    public void setGpsLon(String gpsLon) {
        this.gpsLon = gpsLon;
    }

}
