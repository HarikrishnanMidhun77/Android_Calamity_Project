package com.example.nexus_max.project;

public class ReplyClass {
    String rid,to_mail,from_mail,msg;
    public ReplyClass(){

    }

    public ReplyClass(String rid,String to_mail, String from_mail, String msg) {
        this.to_mail = to_mail;
        this.from_mail = from_mail;
        this.msg = msg;
        this.rid = rid;
    }

    public String getTo_mail() {
        return to_mail;
    }
    public String getRid() {
        return rid;
    }
    public String getFrom_mail() {
        return from_mail;
    }

    public String getMessage() {
        return msg;
    }
}
