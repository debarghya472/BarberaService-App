package com.barbera.barberaserviceapp.network;

import com.barbera.barberaserviceapp.ui.bookings.BookingList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("/")
    Call<BookingList> getBookings();
}
