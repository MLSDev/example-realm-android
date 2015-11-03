package com.cook.simplerealmandroid.realm.repository.impl;

import com.cook.simplerealmandroid.app.SimpleRealmApp;
import com.cook.simplerealmandroid.model.University;
import com.cook.simplerealmandroid.realm.repository.IUniversityRepository;

import java.util.UUID;

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
        realm.beginTransaction();
        RealmQuery<University> query = realm.where(University.class);
        RealmResults<University> results = query.findAll();
        realm.commitTransaction();
        callback.onSuccess(results);
    }

    @Override
    public void onSaveUniversity(University university, OnSaveUniversityCallback callback) {
        Realm realm = Realm.getInstance(SimpleRealmApp.getInstance());
        realm.beginTransaction();
        University u = realm.createObject(University.class);
        u.setId(UUID.randomUUID().toString());
        u.setName(university.getName());
        realm.commitTransaction();
    }

    @Override
    public void getSpecialUniversity(University university, OnGetSpecialUniversityCallback callback) {

    }

    @Override
    public void deleteUniversity(int position, OnDeleteUniversityCallback callback) {
        Realm realm = Realm.getInstance(SimpleRealmApp.getInstance());
        realm.beginTransaction();
        RealmQuery<University> query = realm.where(University.class);
        RealmResults<University> results = query.findAll();
        results.remove(position);
        realm.commitTransaction();
    }
}
