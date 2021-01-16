package com.barbera.barberaserviceapp.ui.bookings;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.barbera.barberaserviceapp.R;
import com.barbera.barberaserviceapp.network.JsonPlaceHolderApi;
import com.barbera.barberaserviceapp.network.RetrofitClientInstance;
import com.barbera.barberaserviceapp.ui.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BookingFragment extends Fragment {

    private Button start;
    private Retrofit retrofit;
    private List<BookingItem> itemList;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_bookings,container,false);

        start =(Button)view.findViewById(R.id.btn_start_day);
        itemList = new ArrayList<BookingItem>();
        recyclerView = view.findViewById(R.id.recycler_booking);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBookingList();
            }
        });
        return view;
    }

    private void getBookingList() {
        retrofit = RetrofitClientInstance.getRetrofitInstance();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        final ProgressDialog progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Fetching Today's Bookings");
        progressDialog.show();
        progressDialog.setCancelable(false);
        Call<BookingList> call =jsonPlaceHolderApi.getBookings();
        call.enqueue(new Callback<BookingList>() {
            @Override
            public void onResponse(Call<BookingList> call, Response<BookingList> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(),"Cannot find Bookings",Toast.LENGTH_SHORT).show();
                    return;
                }
                List<BookingItem> bookingList = response.body().getBookingItemList();
                
                if(bookingList == null){
                    Toast.makeText(getContext(),"Cannot find any bookings",Toast.LENGTH_SHORT).show();
                }else{
                    for(BookingItem bookingItem: bookingList){
                        itemList.add(new BookingItem(bookingItem.getName(),bookingItem.getService(),bookingItem.getDate(),bookingItem.getTime(),
                                bookingItem.getAddress(),bookingItem.getAmount(),bookingItem.getAssignee(),bookingItem.getStatus()));
                    }
                    progressDialog.dismiss();
                    attach_adapter();
                }
                
            }

            @Override
            public void onFailure(Call<BookingList> call, Throwable t) {
                Toast.makeText(getContext(),"Cannot find any bookings",Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });

    }

    private void attach_adapter() {
        BookingItemAdapter adapter = new BookingItemAdapter(itemList,getActivity());
        recyclerView.setAdapter(adapter);
    }
}
