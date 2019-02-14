package com.example.nexus_max.project;

public class Weather {
    String wid,dist,dc;             // dc stands for Degree Celsius
    public Weather(){}

    public Weather(String wid, String dist, String dc) {
        this.wid = wid;
        this.dist = dist;
        this.dc = dc;
    }

    public String getWid() {
        return wid;
    }

    public String getDist() {
        return dist;
    }

    public String getDc() {
        return dc;
    }
}
