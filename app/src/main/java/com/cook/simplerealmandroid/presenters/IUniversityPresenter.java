package com.cook.simplerealmandroid.presenters;

/**
 * Created by roma on 19.10.15.
 */
public interface IUniversityPresenter extends IBasePresenter {



    void getAllUniversities();

    void addUniversity(String universityName);

    void getSpecialUniversityById(String id);

    void deleteUniversity(int position);

}
