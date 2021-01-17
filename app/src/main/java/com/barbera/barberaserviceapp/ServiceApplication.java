package com.barbera.barberaserviceapp;

import android.app.Application;

import io.realm.Realm;

public class ServiceApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
