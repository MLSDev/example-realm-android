package com.cook.simplerealmandroid.presenters;

import com.cook.simplerealmandroid.model.Student;

/**
 * Created by roma on 03.11.15.
 */
public interface IStudentPresenter extends IBasePresenter{

    void addStudent(Student student);

    void addStudentByUniversityId(Student student, String universityId);

    void deleteStudent(int position);

    void getAllStudents();

    void getAllStudentsByUniversityId(String id);

    void getSpecialStudentById(String id);

}
