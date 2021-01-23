package com.barbera.barberaserviceapp;

import android.app.Application;
import android.content.SharedPreferences;

import io.realm.Realm;

public class ServiceApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        Realm.init(this);
        SharedPreferences sharedPreferences = getSharedPreferences("ServiceList",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("1","Simple hair cut");
        editor.putString("2","Stylish hair cut");
        editor.putString("3","Child hair cut");
        editor.putString("4","hair cut with hair wash");
        editor.putString("5","simple shaving");
        editor.putString("6","beard trimming");
        editor.putString("7","denim shave");
        editor.putString("8","foam shave");
        editor.putString("9","simple hair color");
        editor.putString("10","loreal hair color");
        editor.putString("11","garnier hair color");
        editor.putString("12","matrix hair color");
        editor.putString("13","simple facial ");
        editor.putString("14","gold facial");
        editor.putString("15","o3 facial");
        editor.putString("16","fruit facial");
        editor.putString("17","aroma facial");
        editor.putString("18","aroma cleanup");
        editor.putString("19","fruit face cleanup");
        editor.putString("20","o3 face cleanup");
        editor.putString("21","gold face cleanup");
        editor.putString("22","");
        editor.putString("23","");
        editor.putString("24","");
        editor.putString("25","");
        editor.putString("26","");
        editor.putString("27","");
        editor.putString("28","");
        editor.putString("29","");
        editor.putString("30","");
        editor.putString("31","");
        editor.putString("32","");
        editor.putString("33","");
        editor.putString("34","");
        editor.putString("35","");
        editor.putString("36","");
        editor.putString("37","");
        editor.putString("38","");
        editor.putString("39","");
        editor.putString("40","");
        editor.putString("41","");
        editor.putString("42","");
        editor.putString("43","");
        editor.putString("44","");
        editor.putString("45","");
        editor.putString("46","");
        editor.putString("47","");
        editor.putString("48","");
        editor.putString("49","");
        editor.putString("50","");
        editor.putString("51","");
        editor.putString("52","");
        editor.putString("53","");
        editor.putString("54","");
        editor.putString("55","");
        editor.putString("56","");
        editor.putString("57","");
        editor.putString("58","");
        editor.putString("59","");
        editor.putString("60","");
        editor.putString("61","");
        editor.putString("62","");
        editor.putString("63","");
        editor.putString("64","");
        editor.putString("65","");
        editor.putString("66","");
        editor.putString("67","");
        editor.putString("68","");
        editor.putString("69","");
        editor.putString("70","");


    }
}
