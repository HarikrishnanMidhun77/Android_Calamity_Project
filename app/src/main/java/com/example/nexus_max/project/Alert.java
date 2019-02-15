package com.example.nexus_max.project;

public class Alert {
    String aid,loc,alert;

    public Alert(){}
    public Alert(String aid, String loc, String alert) {
        this.aid = aid;
        this.loc = loc;
        this.alert = alert;
    }

    public String getAid() {
        return aid;
    }

    public String getLoc() {
        return loc;
    }

    public String getAlert() {
        return alert;
    }
}
