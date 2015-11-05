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
    private IUniversityRepository.OnAddUniversityCallback addUniversityCallback;
    private IUniversityRepository.OnGetUniversityByIdCallback getSpecialUniversityCallback;
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
        repository.addUniversity(university, addUniversityCallback);
    }

    @Override
    public void getUniversityById(String id) {
        repository.getUniversityById(id, getSpecialUniversityCallback);
    }

    @Override
    public void deleteUniversity(int position) {
        repository.deleteUniversityByPosition(position, deleteUniversityCallback);
    }

    @Override
    public void deleteUniversityById(String Id) {
        repository.deleteUniversityById(Id, deleteUniversityCallback);
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
        addUniversityCallback = new IUniversityRepository.OnAddUniversityCallback() {
            @Override
            public void onSuccess() {
                view.showMessage("Added");
            }

            @Override
            public void onError() {
                view.showMessage("Error");
            }
        };
        getSpecialUniversityCallback = new IUniversityRepository.OnGetUniversityByIdCallback() {
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
        addUniversityCallback = null;
        getSpecialUniversityCallback = null;
        deleteUniversityCallback = null;
    }
}
