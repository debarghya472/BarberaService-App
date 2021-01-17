package com.barbera.barberaserviceapp.ui.bookings;

import com.google.gson.annotations.SerializedName;

import java.sql.Time;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmObject;

public class BookingItem extends RealmObject {
    @SerializedName("name")
    private String name;
    @SerializedName("service")
    private String service;
    @SerializedName("date")
    private Date date;
    @SerializedName("time")
    private String time;
    @SerializedName("address")
    private  String address;
    @SerializedName("amount")
    private String amount;
    @SerializedName("assignedTo")
    private String assignee;
    @SerializedName("status")
    private int status;

    public BookingItem(){
        
    }

    public BookingItem(String name, String service, Date date, String time, String address, String amount, String assignee, int status) {
        this.name = name;
        this.service = service;
        this.date = date;
        this.time = time;
        this.address = address;
        this.amount = amount;
        this.assignee = assignee;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getService() {
        return service;
    }

    public Date getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getAddress() {
        return address;
    }

    public String getAmount() {
        return amount;
    }

    public String getAssignee() {
        return assignee;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
