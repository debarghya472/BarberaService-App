package com.barbera.barberaserviceapp.ui.service;

import com.google.gson.annotations.SerializedName;

public class ServiceItem {
    @SerializedName("barberid")
    private String barberid;
    @SerializedName("start")
    private String startdt;
    @SerializedName("end")
    private String enddt;
    @SerializedName("time")
    private String total;
    @SerializedName("customer_id")
    private String customer_id;
    @SerializedName("servname")
    private String sername;

    public ServiceItem(String barberid,String startdt,String enddt,String total,String customer_id,String sername){
        this.barberid=barberid;
        this.customer_id=customer_id;
        this.enddt=enddt;
        this.sername=sername;
        this.startdt=startdt;
        this.total=total;
    }

    public String getBarberid() {
        return barberid;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public String getEnddt() {
        return enddt;
    }

    public String getSername() {
        return sername;
    }

    public String getStartdt() {
        return startdt;
    }

    public String getTotal() {
        return total;
    }
}
