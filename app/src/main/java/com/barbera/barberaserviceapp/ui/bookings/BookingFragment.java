package com.barbera.barberaserviceapp.ui.bookings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.barbera.barberaserviceapp.R;
import com.barbera.barberaserviceapp.network.JsonPlaceHolderApi;
import com.barbera.barberaserviceapp.network.RetrofitClientInstance;

import retrofit2.Retrofit;

public class BookingFragment extends Fragment {

    private Button start;
    private Retrofit retrofit;
    private JsonPlaceHolderApi jsonPlaceHolderApi;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_bookings,container,false);

        start =(Button)view.findViewById(R.id.btn_start_day);

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

    }
}
