package com.cook.simplerealmandroid.realm.repository;

import com.cook.simplerealmandroid.model.Student;

import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by roma on 16.10.15.
 */
public interface IStudentRepository {

    interface OnSaveStudentCallback {
        void onSuccess();
        void onError(String message);
    }

    interface OnDeleteStudentCallback {
        void onSuccess();
        void onError(String message);
    }

    interface OnGetStudentByIdCallback {
        void onSuccess(Student student);
        void onError(String message);
    }

    interface OnGetAllStudentsCallback {
        void onSuccess(RealmResults<Student> students);
        void onError(String message);
    }

    interface OnGetStudentsCallback{
        void onSuccess(RealmList<Student> students);
        void onError(String message);
    }

    void saveStudent(Student student, OnSaveStudentCallback callback);

    void addStudentByUniversityId(Student student, String universityId, OnSaveStudentCallback callback);

    void deleteStudentById(String id, OnDeleteStudentCallback callback);

    void deleteStudentByPosition(int position, OnDeleteStudentCallback callback);

    void getAllStudents(OnGetAllStudentsCallback callback);

    void getAllStudentsByUniversityId(String id, OnGetStudentsCallback callback);

    void getStudentById(String id, OnGetStudentByIdCallback callback);

}
