package com.cook.simplerealmandroid.realm.repository;

import com.cook.simplerealmandroid.app.SimpleRealmApp;

import io.realm.Realm;

/**
 * Created by roma on 16.10.15.
 */
public interface IBaseRepository {
    Realm realm = Realm.getInstance(SimpleRealmApp.getInstance());
}
