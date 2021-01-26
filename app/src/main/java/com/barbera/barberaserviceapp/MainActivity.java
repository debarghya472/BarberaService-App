package com.barbera.barberaserviceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.barbera.barberaserviceapp.ui.bookings.BookingFragment;
import com.barbera.barberaserviceapp.ui.bookings.BookingItem;
import com.barbera.barberaserviceapp.ui.home.HomeFragment;
import com.barbera.barberaserviceapp.ui.mybookings.MyBookingFragment;
import com.barbera.barberaserviceapp.ui.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static List<BookingItem> itemList;
    public static  List<BookingItem> myBookingItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemList =new ArrayList<BookingItem>();
        myBookingItemList = new ArrayList<BookingItem>();

        Fragment fragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment).commit();

        BottomNavigationView bottomNavigationView =findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment =null;
            switch (item.getItemId()){
                case R.id.nav_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.nav_bookings:
                    selectedFragment = new BookingFragment();
                    break;
                case R.id.profile:
                    selectedFragment = new ProfileFragment();
                    break;
                case R.id.nav_mybookings:
                    selectedFragment = new MyBookingFragment();
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        
    }
}