package com.cook.simplerealmandroid.realm.repository.impl;

import com.cook.simplerealmandroid.app.SimpleRealmApp;
import com.cook.simplerealmandroid.model.University;
import com.cook.simplerealmandroid.realm.repository.IUniversityRepository;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by roma on 16.10.15.
 */
public class UniversityRepository implements IUniversityRepository {

    @Override
    public void getAllUniversities(OnGetAllUniversityCallback callback) {
        Realm realm = Realm.getInstance(SimpleRealmApp.getInstance());
        RealmQuery<University> query = realm.where(University.class);
        RealmResults<University> results = query.findAll();
        callback.onSuccess(results);
    }

    @Override
    public void onSaveUniversity(University university, OnSaveUniversityCallback callback) {
        Realm realm = Realm.getInstance(SimpleRealmApp.getInstance());
        realm.beginTransaction();
        University u = realm.createObject(University.class);
        u.setName(university.getName());
        realm.commitTransaction();
    }

    @Override
    public void getSpecialUniversityCallback(University university, OnGetSpecialUniversityCallback callback) {

    }
}
