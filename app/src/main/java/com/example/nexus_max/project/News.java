package com.example.nexus_max.project;

public class News {
    String nid,place,time,heading,news_cont;

   public News(){}

    public News(String nid, String place, String time, String heading, String news_cont) {
        this.nid = nid;
        this.place = place;
        this.time = time;
        this.heading = heading;
        this.news_cont = news_cont;
    }

    public String getNid() {
        return nid;
    }

    public String getPlace() {
        return place;
    }

    public String getTime() {
        return time;
    }

    public String getHeading() {
        return heading;
    }

    public String getNews_cont() {
        return news_cont;
    }
}
