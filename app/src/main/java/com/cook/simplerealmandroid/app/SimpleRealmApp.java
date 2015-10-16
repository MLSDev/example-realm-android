package com.cook.simplerealmandroid.app;

import android.app.Application;

import com.cook.simplerealmandroid.realm.module.SimpleRealmModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by roma on 15.10.15.
 */
public class SimpleRealmApp extends Application {

    private static SimpleRealmApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        RealmConfiguration config = new RealmConfiguration.Builder(getApplicationContext()).setModules(new SimpleRealmModule()).build();
        Realm.setDefaultConfiguration(config);
    }

    public static SimpleRealmApp getInstance() {
        return instance;
    }
}
