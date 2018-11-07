package com.example.dean.deanweather.db;

import org.litepal.crud.DataSupport;

/**
 * 查看过的城市的天气
 */
public class WeatherCities extends DataSupport {

    private int id;

    private String countyName;//城市名

    private String weatherId;//天气di

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }
}
