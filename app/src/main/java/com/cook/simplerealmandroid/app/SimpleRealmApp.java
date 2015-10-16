package com.cook.simplerealmandroid.app;

import android.app.Application;

import com.cook.simplerealmandroid.realm.SimpleRealmModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by roma on 15.10.15.
 */
public class SimpleRealmApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration config = new RealmConfiguration.Builder(getApplicationContext()).setModules(new SimpleRealmModule()).build();
        Realm.setDefaultConfiguration(config);
    }
}
