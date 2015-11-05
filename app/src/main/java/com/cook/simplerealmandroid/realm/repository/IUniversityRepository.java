package com.cook.simplerealmandroid.realm.repository;

import com.cook.simplerealmandroid.model.University;

import io.realm.RealmResults;

/**
 * Created by roma on 16.10.15.
 */
public interface IUniversityRepository extends IBaseRepository {

    interface OnAddUniversityCallback {
        void onSuccess();
        void onError();
    }

    interface OnGetAllUniversityCallback {
        void onSuccess(RealmResults<University> universities);
        void onError();
    }

    interface OnGetUniversityByIdCallback {
        void onSuccess(University university);
        void onError();
    }

    interface OnDeleteUniversityCallback {
        void onSuccess();
        void onError(String message);
    }

    void addUniversity(University university, OnAddUniversityCallback callback);

    void deleteUniversityById(String Id, OnDeleteUniversityCallback callback);

    void deleteUniversityByPosition(int position, OnDeleteUniversityCallback callback);

    void getAllUniversities(OnGetAllUniversityCallback callback);

    void getUniversityById(String id, OnGetUniversityByIdCallback callback);
}
