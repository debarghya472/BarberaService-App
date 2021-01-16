package com.barbera.barberaserviceapp.ui.bookings;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookingList {

    @SerializedName("user")
    private List<BookingItem> bookingItemList;

    public BookingList(List<BookingItem> bookingItemList) {
        this.bookingItemList = bookingItemList;
    }

    public List<BookingItem> getBookingItemList() {
        return bookingItemList;
    }
}
