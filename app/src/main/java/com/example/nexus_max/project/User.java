package com.example.nexus_max.project;

public class User {
    public String userid,emailid,phno,pswd,uname,place;
   public User(){

    }

    public User(String userid, String emailid, String phno, String pswd, String uname, String place) {
        this.userid = userid;
        this.emailid = emailid;
        this.phno = phno;
        this.pswd = pswd;
        this.uname = uname;
        this.place = place;
    }

    public String getUserid() {
        return userid;
    }

    public String getEmailid() {
        return emailid;
    }

    public String getPhno() {
        return phno;
    }

    public String getPswd() {
        return pswd;
    }

    public String getUname() {
        return uname;
    }

    public String getPlace() {
        return place;
    }
}
