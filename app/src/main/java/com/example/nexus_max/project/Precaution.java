package com.example.nexus_max.project;

public class Precaution {
    public String pid,calam,precs;
    public Precaution(){}

    public Precaution(String pid,String calam, String precs) {
        this.calam = calam;
        this.precs = precs;
        this.pid = pid;
    }

    public String getCalam() {
        return calam;
    }

    public String getPrecs() {
        return precs;
    }
    public String getPid() {
        return pid;
    }
}
