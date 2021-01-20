package com.barbera.barberaserviceapp.ui.mybookings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.barbera.barberaserviceapp.R;
import com.barbera.barberaserviceapp.ui.bookings.BookingItem;
import com.barbera.barberaserviceapp.ui.bookings.BookingItemAdapter;

import java.util.List;

public class MyBookingsAdapter extends RecyclerView.Adapter<MyBookingsAdapter.MyBookingItemHolder> {
    private List<BookingItem> bookingItemList;
    private Context context;

    public MyBookingsAdapter(List<BookingItem> booking, Context context){
        this.bookingItemList = booking;
        this.context = context;
    }

    @NonNull
    @Override
    public MyBookingItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.mybooking_item,parent,false);
        return new MyBookingItemHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyBookingItemHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return  bookingItemList.size();
    }

    public class MyBookingItemHolder extends RecyclerView.ViewHolder{
        private TextView address;
        private TextView service;
        private TextView amount;

        public MyBookingItemHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
