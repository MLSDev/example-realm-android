package com.cook.simplerealmandroid.presenters;

import com.cook.simplerealmandroid.model.Student;

/**
 * Created by roma on 03.11.15.
 */
public interface IStudentPresenter extends IBasePresenter{

    void addStudent(Student student);

    void deleteStudent(int position);

    void getAllStudents();

    void getSpecialStudentById(String id);

}
