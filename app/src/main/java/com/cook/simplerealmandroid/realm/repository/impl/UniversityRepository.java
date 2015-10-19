package com.cook.simplerealmandroid.realm.repository.impl;

import com.cook.simplerealmandroid.model.University;
import com.cook.simplerealmandroid.realm.repository.IUniversityRepository;

import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by roma on 16.10.15.
 */
public class UniversityRepository implements IUniversityRepository {

    @Override
    public void onSaveUniversity(University university, OnSaveUniversityCallback callback) {
        realm.beginTransaction();
        University univer = realm.createObject(University.class);
        univer.setName(univer.getName());
        realm.commitTransaction();
    }

    @Override
    public void getAllUniversities(OnGetAllUniversityCallback callback) {
        RealmQuery<University> query = realm.where(University.class);
        RealmResults<University> results = query.findAll();
        callback.onSuccess(results);
    }

    @Override
    public void getSpecialUniversityCallback(University university, OnGetSpecialUniversityCallback callback) {

    }
}
