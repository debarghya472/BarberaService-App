package com.barbera.barberaserviceapp.ui.bookings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.barbera.barberaserviceapp.R;
import com.barbera.barberaserviceapp.ui.service.ServiceActivity;
import com.google.gson.internal.$Gson$Preconditions;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;

public class BookingItemAdapter extends RecyclerView.Adapter<BookingItemAdapter.BookingItemHolder> {

    private List<BookingItem> bookingItemList;
    private  Context context;
    private Address address;


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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull BookingItemAdapter.BookingItemHolder holder, int position) {
        BookingItem bookingItem = bookingItemList.get(position);

        holder.address.setText(bookingItem.getAddress());
        holder.service.setText(bookingItem.getService());
        holder.amount.setText(bookingItem.getAmount());
        holder.direction.setOnClickListener(v -> {
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("google.navigation:q="+bookingItem.getAddress()));
            context.startActivity(intent);
        });
        holder.cancel.setOnClickListener(v -> {
            bookingItemList.remove(position);
            notifyDataSetChanged();
        });

        holder.start.setOnClickListener(v -> {
            Intent intent = new Intent(context,ServiceActivity.class);
            intent.putExtra("kwy","123");
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return bookingItemList.size();
    }

    public class BookingItemHolder extends RecyclerView.ViewHolder {
        private TextView address;
        private TextView service;
        private TextView amount;
        private ImageView direction;
        private Button cancel;
        private Button start;
        public BookingItemHolder(@NonNull View itemView) {
            super(itemView);
            address = itemView.findViewById(R.id.add);
            service = itemView.findViewById(R.id.service);
            amount = itemView.findViewById(R.id.amt);
            direction = itemView.findViewById(R.id.direction);
            cancel = itemView.findViewById(R.id.cancel_button);
            start  = itemView.findViewById(R.id.start_btn);
        }
    }
}
