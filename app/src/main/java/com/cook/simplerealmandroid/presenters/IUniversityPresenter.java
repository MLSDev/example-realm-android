package com.cook.simplerealmandroid.presenters;

import com.cook.simplerealmandroid.model.University;

/**
 * Created by roma on 19.10.15.
 */
public interface IUniversityPresenter {

    void getAllUniversities();

    void addUniversity(String universityName);

    void getSpecialUniversity(University university);

    void deleteUniversity(int position);

    void subscribeCallbacks();

    void unSubscribeCallbacks();
}
