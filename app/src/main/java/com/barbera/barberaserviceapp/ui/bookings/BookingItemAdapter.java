package com.barbera.barberaserviceapp.ui.bookings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.barbera.barberaserviceapp.R;
import com.barbera.barberaserviceapp.network.JsonPlaceHolderApi;
import com.barbera.barberaserviceapp.network.RetrofitClientInstance;
import com.barbera.barberaserviceapp.ui.service.ServiceActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Retrofit;

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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull BookingItemAdapter.BookingItemHolder holder, int position) {
        BookingItem bookingItem = bookingItemList.get(position);
        String time =convertTime(bookingItem.getTime());
        holder.address.setText(bookingItem.getAddress());
        holder.service.setText(bookingItem.getService());
        holder.amount.setText(bookingItem.getAmount());
        holder.time.setText(time.substring(time.lastIndexOf("1899")+4));
//        holder.direction.setOnClickListener(v -> {
//            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
//                    Uri.parse("google.navigation:q="+bookingItem.getAddress()));
//            context.startActivity(intent);
//        });
//        holder.cancel.setOnClickListener(v -> {
//            bookingItemList.remove(position);
//            notifyDataSetChanged();
//        });

        holder.start.setOnClickListener(v -> {
            bookingItemList.remove(position);
            notifyDataSetChanged();
            updateAssigneeInDb();
        });
    }

    private void updateAssigneeInDb() {
        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Toast.makeText(context," ",Toast.LENGTH_SHORT).show();
//        Call<BookingList> call = jsonPlaceHolderApi.updateAssignee();
    }

    @Override
    public int getItemCount() {
        return bookingItemList.size();
    }

    public static class BookingItemHolder extends RecyclerView.ViewHolder {
        private TextView address;
        private TextView service;
        private TextView amount;
        private TextView time;
//        private Button cancel;
        private Button start;
        public BookingItemHolder(@NonNull View itemView) {
            super(itemView);
            address = itemView.findViewById(R.id.add);
            service = itemView.findViewById(R.id.service);
            amount = itemView.findViewById(R.id.amt);
//            cancel = itemView.findViewById(R.id.cancel_button);
            time = itemView.findViewById(R.id.Time1);
            start  = itemView.findViewById(R.id.accept_btn);
        }
    }
    private String convertTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE,MMMM d,yyyy h:mm,a", Locale.ENGLISH);
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        String formattedDate = sdf.format(date);
        return  formattedDate;
    }
}
