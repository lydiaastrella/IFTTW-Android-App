package com.example.ifttw.openweather;

import java.io.Serializable;

public class Location implements Serializable {
    private long sunrise;
    private String country;
    private String city;

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public void setCity(String city) {
        this.city = city;
    }
}

