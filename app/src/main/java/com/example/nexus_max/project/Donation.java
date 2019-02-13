package com.example.nexus_max.project;

public class Donation {
    String did,dname,dbranch,dacc,difsc,damt;
    public Donation(){}

    public Donation(String did, String dname, String dbranch, String dacc, String difsc, String damt) {
        this.did = did;
        this.dname = dname;
        this.dbranch = dbranch;
        this.dacc = dacc;
        this.difsc = difsc;
        this.damt = damt;
    }

    public String getDid() {
        return did;
    }

    public String getDname() {
        return dname;
    }

    public String getDbranch() {
        return dbranch;
    }

    public String getDacc() {
        return dacc;
    }

    public String getDifsc() {
        return difsc;
    }

    public String getDamt() {
        return damt;
    }
}
