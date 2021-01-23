package com.barbera.barberaserviceapp.ui.home;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.barbera.barberaserviceapp.R;

import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {

    private TextView trips;
    private TextView earnings;
    private  TextView cancelled;
    private TextView points;
    private SharedPreferences sharedPreferences;
    private Toolbar toolbar;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences= Objects.requireNonNull(getActivity()).getSharedPreferences("ServiceInfo",MODE_PRIVATE);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_home,container,false);
        toolbar= view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        trips= view.findViewById(R.id.tripps);
        earnings = view.findViewById(R.id.earning);
        cancelled = view.findViewById(R.id.cancelled);
        points = view.findViewById(R.id.points);

        trips.setText(sharedPreferences.getInt("trips",0)+"");
        earnings.setText("Rs. "+sharedPreferences.getInt("payment",0));
        cancelled.setText(sharedPreferences.getInt("cancel",0)+"");
        points.setText(sharedPreferences.getInt("points",0)+"");

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.top_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
