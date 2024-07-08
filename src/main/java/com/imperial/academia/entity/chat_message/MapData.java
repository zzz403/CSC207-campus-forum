package com.imperial.academia.entity.chat_message;

public class MapData {
    private double latitude;
    private double longitude;
    private String locationInfo;

    public MapData(double latitude, double longitude, String locationInfo) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.locationInfo = locationInfo;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getLocationInfo() {
        return locationInfo;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLocationInfo(String locationInfo) {
        this.locationInfo = locationInfo;
    }
}
