package com.cook.simplerealmandroid.realm.repository.impl;

import com.cook.simplerealmandroid.app.SimpleRealmApp;
import com.cook.simplerealmandroid.model.Student;
import com.cook.simplerealmandroid.realm.repository.IStudentRepository;
import com.cook.simplerealmandroid.realm.table.RealmTable;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by roma on 16.10.15.
 */
public class StudentRepository implements IStudentRepository {

    @Override
    public void saveStudent(Student student, OnSaveStudentCallback callback) {
        Realm realm = Realm.getInstance(SimpleRealmApp.getInstance());
        realm.beginTransaction();
        Student realmStudent = realm.createObject(Student.class);
        realmStudent.setId(UUID.randomUUID().toString());
        realmStudent.setName(student.getName());
        realmStudent.setAge(student.getAge());
        realmStudent.setEmail(student.getEmail());
        realmStudent.setBooks(student.getBooks());
        realmStudent.setLessons(student.getLessons());
        realm.commitTransaction();

        if (callback != null)
            callback.onSuccess();
    }

    @Override
    public void deleteStudentById(String id, OnDeleteStudentCallback callback) {
        Realm realm = Realm.getInstance(SimpleRealmApp.getInstance());
        realm.beginTransaction();
        Student result = realm.where(Student.class).equalTo(RealmTable.ID, id).findFirst();
        result.removeFromRealm();
        realm.commitTransaction();

        if (callback != null)
            callback.onSuccess();
    }

    @Override
    public void deleteStudentByPosition(int position, OnDeleteStudentCallback callback) {
        Realm realm = Realm.getInstance(SimpleRealmApp.getInstance());
        realm.beginTransaction();
        RealmQuery<Student> query = realm.where(Student.class);
        RealmResults<Student> results = query.findAll();
        results.remove(position);
        realm.commitTransaction();

        if (callback != null)
            callback.onSuccess();
    }

    @Override
    public void getAllStudents(OnGetAllStudentsCallback callback) {
        Realm realm = Realm.getInstance(SimpleRealmApp.getInstance());
        realm.beginTransaction();
        RealmResults<Student> results = realm.where(Student.class).findAll();
        realm.commitTransaction();

        if (callback != null)
            callback.onSuccess(results);
    }

    @Override
    public void getStudentById(String id, OnGetStudentByIdCallback callback) {
        Realm realm = Realm.getInstance(SimpleRealmApp.getInstance());
        realm.beginTransaction();
        Student student = realm.where(Student.class).equalTo(RealmTable.ID, id).findFirst();
        realm.commitTransaction();

        if (callback != null)
            callback.onSuccess(student);
    }
}
