package com.barbera.barberaserviceapp.network;

import com.barbera.barberaserviceapp.ui.bookings.BookingList;

import java.util.Date;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {

    @GET("exec")
    Call<BookingList> getBookings();

    @POST("exec")
    Call<BookingList> updateAssignee(@Query("name") String name, @Query("service")String service, @Query("time")String Time, @Query("address") String address
    , @Query("amount")String amount,@Query("assignedTo") String assignee,@Query("action")String action,@Query("status")int status,@Query("id")int id, @Query("date")String date, @Query("contact")String contact);
}
