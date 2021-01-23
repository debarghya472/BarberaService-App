package com.barbera.barberaserviceapp.ui.bookings;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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
        SharedPreferences sharedPreferences = context.getSharedPreferences("ServiceList",Context.MODE_PRIVATE);
        String services[] = bookingItem.getService().split(" ");
        String Servicenames ="";
        for(int i=0;i<services.length;i++){
            Servicenames = Servicenames + sharedPreferences.getString(services[i],"")+",";
        }
        String t =bookingItem.getTime();
        String t1 = t.substring(t.indexOf('T') + 1, t.indexOf('.'));
        String time = null;
        try {
            time = convertTime(t1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.address.setText(bookingItem.getAddress());
        holder.service.setText(Servicenames);
        holder.amount.setText(bookingItem.getAmount());
        holder.time.setText(time);

        holder.start.setOnClickListener(v -> {
            bookingItemList.remove(position);
            notifyDataSetChanged();
            updateAssigneeInDb(bookingItem.getName(),bookingItem.getService(),bookingItem.getTime(),bookingItem.getAddress(),bookingItem.getAmount(),
                    bookingItem.getId(),bookingItem.getDate(),bookingItem.getContact());
        });
    }

    private void updateAssigneeInDb(String name, String service, String time, String address, String amount, int id,String date, String contact) {
        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        final ProgressDialog progressDialog=new ProgressDialog(context);
        progressDialog.setMessage("Adding Booking!!");
        progressDialog.show();
        progressDialog.setCancelable(true);
        Call<String> call = jsonPlaceHolderApi.updateAssignee(name,service,time,address,amount,"debarghya","update",1,id,date,contact);
//        call.enqueue(new Callback<BookingList>() {
//            @Override
//            public void onResponse(Call<BookingList> call, Response<BookingList> response) {
//                if(response.code() == 200){
//                    Toast.makeText(context,"Added booking",Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(context,"Something is wrong",Toast.LENGTH_SHORT).show();
//                }
//                progressDialog.dismiss();
//            }
//
//            @Override
//            public void onFailure(Call<BookingList> call, Throwable t) {
////                Toast.makeText(context,"Something went wrong",Toast.LENGTH_SHORT).show();
//                Toast.makeText(context,"Added booking",Toast.LENGTH_SHORT).show();
//                progressDialog.dismiss();
//            }
//        });
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.code() == 200){
                    Toast.makeText(context,"Added booking",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context,"Something is wrong",Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
//                Toast.makeText(context,"Something went wrong",Toast.LENGTH_SHORT).show();
                Toast.makeText(context,"Added booking",Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
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
    private String convertTime(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss", Locale.ENGLISH);
        Date date = sdf.parse(time);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        String formattedDate = sdf.format(date);
        return  formattedDate;
    }
}
