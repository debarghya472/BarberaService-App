package com.barbera.barberaserviceapp.ui.service;

import com.barbera.barberaserviceapp.ui.bookings.BookingItem;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServiceList {
    @SerializedName("service")
    private List<ServiceItem> serviceItemList;

    public ServiceList(List<ServiceItem> serviceItemList) {
        this.serviceItemList = serviceItemList;
    }

    public List<ServiceItem> getServiceItemList() {
        return serviceItemList;
    }
}
