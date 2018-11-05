package com.example.dean.deanweather.gson;

import com.google.gson.annotations.SerializedName;

public class Basic {
    @SerializedName("city")
    public String cityName;     //城市名称

    @SerializedName("id")
    public String weatherId;    //天气ID

    public Update update;

    public class Update{
        @SerializedName("loc")
        public String updateTime;   //更新时间
    }
}
