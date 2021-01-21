package com.barbera.barberaserviceapp.ui.bookings;

import android.app.ProgressDialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.barbera.barberaserviceapp.R;
import com.barbera.barberaserviceapp.network.JsonPlaceHolderApi;
import com.barbera.barberaserviceapp.network.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;


import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.barbera.barberaserviceapp.MainActivity.itemList;

public class BookingFragment extends Fragment {

    private Retrofit retrofit;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private RecyclerView recyclerView;
    private Toolbar toolbar;

    private BookingItemAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new BookingItemAdapter(itemList,getActivity());
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_bookings,container,false);

        toolbar= view.findViewById(R.id.tool);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        recyclerView = view.findViewById(R.id.recycler_booking);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        if(itemList.size() != 0 ){
            attach_adapter();
        }else {
            getBookingList();
        }

        return view;
    }

    private void getBookingList() {
        retrofit = RetrofitClientInstance.getRetrofitInstance();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        final ProgressDialog progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Fetching Today's Bookings");
        progressDialog.show();
        progressDialog.setCancelable(true);
        if(itemList!=null){
            itemList.clear();
            adapter.notifyDataSetChanged();
        }
        Call<BookingList> call =jsonPlaceHolderApi.getBookings();
        call.enqueue(new Callback<BookingList>() {
            @Override
            public void onResponse(Call<BookingList> call, Response<BookingList> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(),"Cannot find Bookings",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }
                List<BookingItem> bookingList = response.body().getBookingItemList();
                
                if(bookingList == null){
                    Toast.makeText(getContext(),"Cannot find any bookings",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }else{
                    for(BookingItem bookingItem: bookingList){
                        if(bookingItem.getStatus() == 0)
                        itemList.add(new BookingItem(bookingItem.getId(),bookingItem.getName(),bookingItem.getService(),bookingItem.getDate(),bookingItem.getTime(),
                                bookingItem.getAddress(),bookingItem.getAmount(),bookingItem.getAssignee(),bookingItem.getStatus(),bookingItem.getContact()));
                    }
                    progressDialog.dismiss();
                    attach_adapter();
//                    addToLocalDb();
                }
                
            }

            @Override
            public void onFailure(Call<BookingList> call, Throwable t) {
                Toast.makeText(getContext(),"Cannot find any bookings",Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.refresh_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.refresh) {
            getBookingList();
        }
        return super.onOptionsItemSelected(item);
    }

    private void attach_adapter() {
        recyclerView.setAdapter(adapter);
    }
    private void addToLocalDb() {
        Realm realm =Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insert(itemList);
        realm.commitTransaction();
    }
}
