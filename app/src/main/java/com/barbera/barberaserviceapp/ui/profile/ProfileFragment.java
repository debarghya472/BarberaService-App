package com.barbera.barberaserviceapp.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.barbera.barberaserviceapp.R;

public class ProfileFragment extends Fragment {

    private TextView name;
    private TextView email;
    private TextView phone;
    private TextView earnings;
    private TextView points;
    private  TextView trips;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.fragment_profile,container,false);
       name = (TextView)view.findViewById(R.id.NameInProfile);
       email = (TextView)view.findViewById(R.id.EmailInProfile);
       phone =(TextView)view.findViewById(R.id.PhoneInProfile);
       earnings =(TextView)view.findViewById(R.id.earning);
       points =(TextView)view.findViewById(R.id.points);
       trips =(TextView)view.findViewById(R.id.trip);
       
       displayProfile();
       return view;
    }

    private void displayProfile() {
    }

}
