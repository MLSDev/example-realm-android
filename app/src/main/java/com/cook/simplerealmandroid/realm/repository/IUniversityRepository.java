package com.cook.simplerealmandroid.realm.repository;

import com.cook.simplerealmandroid.model.University;

import io.realm.RealmResults;

/**
 * Created by roma on 16.10.15.
 */
public interface IUniversityRepository extends IBaseRepository {

    interface OnSaveUniversityCallback {
        void onSuccess();

        void onError();
    }

    interface OnGetAllUniversityCallback {
        void onSuccess(RealmResults<University> universities);

        void onError();
    }

    interface OnGetSpecialUniversityCallback {
        void onSuccess(University university);

        void onError();
    }
    void getAllUniversities(OnGetAllUniversityCallback callback);

    void onSaveUniversity(University university, OnSaveUniversityCallback callback);

    void getSpecialUniversityCallback(University university, OnGetSpecialUniversityCallback callback);
}
