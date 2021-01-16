package com.barbera.barberaserviceapp.ui.bookings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.barbera.barberaserviceapp.R;
import com.google.gson.internal.$Gson$Preconditions;

import org.w3c.dom.Text;

import java.util.List;

public class BookingItemAdapter extends RecyclerView.Adapter<BookingItemAdapter.BookingItemHolder> {

    private List<BookingItem> bookingItemList;
    private  Context context;


    public BookingItemAdapter(List<BookingItem> booking, Context context){
        this.bookingItemList = booking;
        this.context = context;
    }

    @NonNull
    @Override
    public BookingItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_item,parent,false);
        return new BookingItemHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingItemAdapter.BookingItemHolder holder, int position) {
        BookingItem bookingItem = bookingItemList.get(position);

        holder.address.setText("Address: "+bookingItem.getAddress());
        holder.service.setText(bookingItem.getService());
        holder.amount.setText(bookingItem.getService());

    }

    @Override
    public int getItemCount() {
        return bookingItemList.size();
    }

    public class BookingItemHolder extends RecyclerView.ViewHolder {
        private TextView address;
        private TextView service;
        private TextView amount;
        public BookingItemHolder(@NonNull View itemView) {
            super(itemView);
            address = itemView.findViewById(R.id.add);
            service = itemView.findViewById(R.id.service);
            amount = itemView.findViewById(R.id.amt);
        }
    }
}
