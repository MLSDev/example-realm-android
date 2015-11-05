package com.cook.simplerealmandroid.presenters;

/**
 * Created by roma on 19.10.15.
 */
public interface IUniversityPresenter extends IBasePresenter {

    void addUniversity(String universityName);

    void deleteUniversity(int position);

    void deleteUniversityById(String Id);

    void getUniversityById(String id);

    void getAllUniversities();

}
