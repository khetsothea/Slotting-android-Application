package com.developer.myownapp.slotting;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Date;

/**
 * Created by Murthy on 31-07-2016.
 */
@IgnoreExtraProperties
public class MyObjects {
    String user;
    String locname;
    String slottime;
    String date;
   public  MyObjects(){}

    public MyObjects(String user,String locname,String slottime,String date) {
        this.user = user;
        this.locname = locname;
        this.slottime = slottime;
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getLocname() {
        return locname;
    }

    public void setLocname(String locname) {
        this.locname = locname;
    }

    public String getSlottime() {
        return slottime;
    }

    public void setSlottime(String slottime) {
        this.slottime = slottime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
