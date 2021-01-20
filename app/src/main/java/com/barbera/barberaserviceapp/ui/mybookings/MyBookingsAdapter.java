package com.barbera.barberaserviceapp.ui.mybookings;

import android.content.Context;
import android.content.Intent;
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
import com.barbera.barberaserviceapp.ui.bookings.BookingItem;
import com.barbera.barberaserviceapp.ui.bookings.BookingItemAdapter;
import com.barbera.barberaserviceapp.ui.service.ServiceActivity;

import org.w3c.dom.Text;

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
        BookingItem bookingItem = bookingItemList.get(position);

        holder.address.setText(bookingItem.getAddress());
        holder.name.setText(bookingItem.getName());
        holder.time.setText(bookingItem.getTime());
        holder.service.setText(bookingItem.getService());
        holder.amount.setText(bookingItem.getAmount());

        holder.direction.setOnClickListener(v -> {
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("google.navigation:q="+bookingItem.getAddress()));
            context.startActivity(intent);
        });

        holder.start.setOnClickListener(v -> {
            Intent intent = new Intent(context, ServiceActivity.class);
            intent.putExtra("amount",bookingItem.getAmount());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return  bookingItemList.size();
    }

    public class MyBookingItemHolder extends RecyclerView.ViewHolder{
        private TextView address;
        private ImageView direction;
        private TextView name;
        private TextView contact;
        private TextView time;
        private TextView service;
        private TextView amount;
        private Button start;


        public MyBookingItemHolder(@NonNull View itemView) {
            super(itemView);

            address = itemView.findViewById(R.id.address);
            direction = itemView.findViewById(R.id.direction);
            name = itemView.findViewById(R.id.nameIn);
            contact = itemView.findViewById(R.id.contactIn);
            time = itemView.findViewById(R.id.time1);
            service =itemView.findViewById(R.id.serviceadd);
            amount = itemView.findViewById(R.id.amt123);
            start =itemView.findViewById(R.id.start);
        }
    }
}
