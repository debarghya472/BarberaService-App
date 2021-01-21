package com.barbera.barberaserviceapp.network;

import com.barbera.barberaserviceapp.ui.bookings.BookingList;

import java.util.Date;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {

    @GET("exec")
    Call<BookingList> getBookings();

    @POST("exec?name={name}&service={service}&time={time}&address={address}&amount={amount}&assignedTo=debarghya&action=update&id={id}&status=1&date={date}&contact={contact}")
    Call<BookingList> updateAssignee(@Path("name")String name, @Path("service")String service, @Path("time")String Time, @Path("addredd") String address
    , @Path("amount")String amount, @Path("id")int id, @Path("date")Date date,@Path("contact")String contact);
}
