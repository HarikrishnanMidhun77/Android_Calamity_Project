package com.example.nexus_max.project;

public class Message {
public String mid,loc,to_phno,uname,calam,from_email;
public Message(){

}

    public Message(String mid,String loc, String to_phno, String uname, String calam, String from_email) {
        this.mid = mid;
        this.loc = loc;
        this.to_phno = to_phno;
        this.uname = uname;
        this.calam = calam;
        this.from_email = from_email;
    }

    public String getMid() {
        return mid;
    }
    public String getLoc() {
        return loc;
    }

    public String getTo_phno() {
        return to_phno;
    }

    public String getUname() {
        return uname;
    }

    public String getCalam() {
        return calam;
    }
    public String getFrom_email() {
        return from_email;
    }
}
