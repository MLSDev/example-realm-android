package com.cook.simplerealmandroid.presenters.impl;

import com.cook.simplerealmandroid.model.Student;
import com.cook.simplerealmandroid.presenters.IStudentPresenter;
import com.cook.simplerealmandroid.realm.repository.IStudentRepository;
import com.cook.simplerealmandroid.realm.repository.impl.StudentRepository;
import com.cook.simplerealmandroid.view.activity.StudentsActivity;

import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by roma on 03.11.15.
 */
public class StudentPresnter implements IStudentPresenter {

    private StudentsActivity view;

    private IStudentRepository.OnDeleteStudentCallback onDeleteStudentCallback;
    private IStudentRepository.OnSaveStudentCallback onSaveStudentCallback;
    private IStudentRepository.OnGetAllStudentsCallback onGetAllStudentsCallback;
    private IStudentRepository.OnGetStudentByIdCallback onGetStudentByIdCallback;
    private IStudentRepository.OnGetStudentsCallback onGetStudentsCallback;


    private IStudentRepository studentRepository;

    public StudentPresnter(StudentsActivity view) {
        this.view = view;
        studentRepository = new StudentRepository();
    }

    @Override
    public void addStudent(Student student) {
        studentRepository.saveStudent(student, onSaveStudentCallback);
    }

    @Override
    public void addStudentByUniversityId(Student student, String universityId) {
        studentRepository.addStudentByUniversityId(student, universityId, onSaveStudentCallback);
    }

    @Override
    public void deleteStudent(int position) {
        studentRepository.deleteStudentByPosition(position, onDeleteStudentCallback);
    }

    @Override
    public void getAllStudents() {
        studentRepository.getAllStudents(onGetAllStudentsCallback);
    }

    @Override
    public void getAllStudentsByUniversityId(String id) {
        studentRepository.getAllStudentsByUniversityId(id, onGetStudentsCallback);
    }

    @Override
    public void getSpecialStudentById(String id) {
        studentRepository.getStudentById(id, onGetStudentByIdCallback);
    }

    @Override
    public void subscribeCallbacks() {
        onSaveStudentCallback = new IStudentRepository.OnSaveStudentCallback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(String message) {
                view.showMessage(message);
            }
        };
        onDeleteStudentCallback = new IStudentRepository.OnDeleteStudentCallback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(String message) {
                view.showMessage(message);
            }
        };
        onGetAllStudentsCallback = new IStudentRepository.OnGetAllStudentsCallback() {
            @Override
            public void onSuccess(RealmResults<Student> students) {

            }

            @Override
            public void onError(String message) {
                view.showMessage(message);
            }
        };
        onGetStudentByIdCallback = new IStudentRepository.OnGetStudentByIdCallback() {
            @Override
            public void onSuccess(Student student) {

            }

            @Override
            public void onError(String message) {

            }
        };
        onGetStudentsCallback = new IStudentRepository.OnGetStudentsCallback() {
            @Override
            public void onSuccess(RealmList<Student> students) {
                view.showStudents(students);
            }

            @Override
            public void onError(String message) {
                view.showMessage(message);
            }
        };

    }

    @Override
    public void unSubscribeCallbacks() {
        onDeleteStudentCallback = null;
        onSaveStudentCallback = null;
        onGetAllStudentsCallback = null;
        onGetStudentByIdCallback = null;
    }
}
