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

    private IUniversityRepository repository;

    private IUniversityRepository.OnGetAllUniversityCallback getAllUniversityCallback;
    private IUniversityRepository.OnSaveUniversityCallback saveUniversityCallback;
    private IUniversityRepository.OnGetSpecialUniversityCallback getSpecialUniversityCallback;
    private IUniversityRepository.OnDeleteUniversityCallback deleteUniversityCallback;

    public UniversityPresenter(UniversityActivity view) {
        this.view = view;
        repository = new UniversityRepository();
    }

    @Override
    public void getAllUniversities() {
        repository.getAllUniversities(getAllUniversityCallback);
    }

    @Override
    public void addUniversity(String universityName) {
        University university = new University(universityName);
        repository.saveUniversity(university, saveUniversityCallback);
    }

    @Override
    public void getSpecialUniversityById(String id) {
        repository.getUniversityById(id, getSpecialUniversityCallback);
    }

    @Override
    public void deleteUniversity(int position) {
        repository.deleteUniversityByPosition(position, deleteUniversityCallback);
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
        deleteUniversityCallback = new IUniversityRepository.OnDeleteUniversityCallback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(String message) {
                view.showMessage(message);
            }
        };

    }

    @Override
    public void unSubscribeCallbacks() {
        getAllUniversityCallback = null;
        saveUniversityCallback = null;
        getSpecialUniversityCallback = null;
        deleteUniversityCallback = null;
    }
}
