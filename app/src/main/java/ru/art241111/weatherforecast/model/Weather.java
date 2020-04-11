package ru.art241111.weatherforecast.model;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Weather {
    private String city;
    private String posterWeather;
    private String temperature;
    private String condition;
    private String date;

    public Weather() {
    }

    public Weather(String city, String posterWeather, String temperature, String condition, String date) {
        this.city = city;
        this.posterWeather = posterWeather;
        this.temperature = temperature;
        this.condition = condition;
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPosterWeather() {
        return posterWeather;
    }

    public void setPosterWeather(String posterWeather) {
        this.posterWeather = posterWeather;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDate() {
        return date;
    }

    public void setDateUTC(String date) {
        long unixSeconds = Long.parseLong(date);
        Date correctDate = new java.util.Date(unixSeconds*1000L);

        SimpleDateFormat sdf = new java.text.SimpleDateFormat("E d MMMM, H:m");

        sdf.setTimeZone(java.util.TimeZone.getDefault());
        this.date = sdf.format(correctDate);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String analyzeWeather(){
        String analysis = "";

        int temp = Integer.parseInt(temperature);
        if (temp < -15){
            analysis = "Лучше не выходить на улицу, уж очень холодно";
        } else if (temp < -2){
            analysis = "Одевайтесь теплее, на улице прохладно";
        } else if (temp < 10){
            analysis = "В принципе тепло, но все таки лучше одеться потеплее";
        } else if (temp < 30){
            analysis = "На улице тепло, шубу можно оставить на вешалке";
        }else {
            analysis = "Что-то жарко на улице, может лучше дома?";
        }

        analysis+="\n";

        if(condition.equals("дождь")){
            analysis += "Обязательно возьми зонт, иначе промокнешь";
        }

        return analysis;
    }
}
