package com.github.mgrl39.consumrandomuserapi.model;

/*
        "coordinates": {
          "latitude": "-69.8246",
          "longitude": "134.8719"
        },
 */
public class Coordinates {
    private String latitude;
    private String longitude;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
