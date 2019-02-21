package com.example.nexus_max.project;

public class CampStatus {
    String csId,campId,item,status;

    public CampStatus(){}
    public CampStatus(String csId, String campId, String item, String status) {
        this.csId = csId;
        this.campId = campId;
        this.item = item;
        this.status = status;
    }

    public String getCsId() {
        return csId;
    }

    public String getCampId() {
        return campId;
    }

    public String getItem() {
        return item;
    }

    public String getStatus() {
        return status;
    }
}
