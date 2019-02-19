package com.example.nexus_max.project;

public class EmergencyCall {
    String ecId,ec_name,ec_num;
public EmergencyCall(){}
    public EmergencyCall(String ecId, String ec_name, String ec_num) {
        this.ecId = ecId;
        this.ec_name = ec_name;
        this.ec_num = ec_num;
    }

    public String getEcId() {
        return ecId;
    }

    public String getEc_name() {
        return ec_name;
    }

    public String getEc_num() {
        return ec_num;
    }

    public void setEcId(String ecId) {
        this.ecId = ecId;
    }

    public void setEc_name(String ec_name) {
        this.ec_name = ec_name;
    }

    public void setEc_num(String ec_num) {
        this.ec_num = ec_num;
    }
}
