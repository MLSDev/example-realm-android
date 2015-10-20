package com.cook.simplerealmandroid.presenters.impl;

import com.cook.simplerealmandroid.model.University;
import com.cook.simplerealmandroid.presenters.IUniversityPresenter;
import com.cook.simplerealmandroid.realm.repository.IUniversityRepository;
import com.cook.simplerealmandroid.realm.repository.impl.UniversityRepository;
import com.cook.simplerealmandroid.view.activity.UniversityActivity;

import io.realm.RealmResults;

/**
 * Created by roma on 19.10.15.
 */
public class UniversityPresenter implements IUniversityPresenter {

    private UniversityActivity view;

    private IUniversityRepository repository = new UniversityRepository();

    private IUniversityRepository.OnGetAllUniversityCallback getAllUniversityCallback;
    private IUniversityRepository.OnSaveUniversityCallback saveUniversityCallback;
    private IUniversityRepository.OnGetSpecialUniversityCallback getSpecialUniversityCallback;

    public UniversityPresenter(UniversityActivity view) {
        this.view = view;
    }

    @Override
    public void getAllUniversities() {
        repository.getAllUniversities(getAllUniversityCallback);
    }

    @Override
    public void addUniversity(String universityName) {
        University university = new University(universityName);
        repository.onSaveUniversity(university, saveUniversityCallback);
    }

    @Override
    public void getSpecialUniversity(University university) {
        repository.getSpecialUniversityCallback(university, getSpecialUniversityCallback);
    }

    @Override
    public void subscribeCallbacks() {
        getAllUniversityCallback = new IUniversityRepository.OnGetAllUniversityCallback() {
            @Override
            public void onSuccess(RealmResults<University> universities) {
                view.showUniversities(universities);
            }

            @Override
            public void onError() {
                view.showMessage("Error");
            }
        };
        saveUniversityCallback = new IUniversityRepository.OnSaveUniversityCallback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {
                view.showMessage("Error");
            }
        };
        getSpecialUniversityCallback = new IUniversityRepository.OnGetSpecialUniversityCallback() {
            @Override
            public void onSuccess(University university) {

            }

            @Override
            public void onError() {
                view.showMessage("Error");
            }
        };
    }

    @Override
    public void unSubscribeCallbacks() {
        getAllUniversityCallback = null;
        saveUniversityCallback = null;
        getSpecialUniversityCallback = null;
    }
}
