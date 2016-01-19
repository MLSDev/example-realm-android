package com.cook.simplerealmandroid.realm.repository.impl;

import com.cook.simplerealmandroid.app.SimpleRealmApp;
import com.cook.simplerealmandroid.model.Student;
import com.cook.simplerealmandroid.model.University;
import com.cook.simplerealmandroid.realm.repository.IStudentRepository;
import com.cook.simplerealmandroid.realm.table.RealmTable;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by roma on 16.10.15.
 */
public class StudentRepository implements IStudentRepository {

    @Override
    public void addStudent(Student student, OnSaveStudentCallback callback) {
        Realm realm = Realm.getInstance(SimpleRealmApp.getInstance());
        realm.beginTransaction();
        Student realmStudent = realm.createObject(Student.class);
        realmStudent.setId(UUID.randomUUID().toString());
        realmStudent.setName(student.getName());
        realmStudent.setBirthday(student.getBirthday());
        realmStudent.setEmail(student.getEmail());
        realm.commitTransaction();

        if (callback != null)
            callback.onSuccess();
    }

    @Override
    public void addStudentByUniversityId(Student student, String universityId, OnSaveStudentCallback callback) {
        Realm realm = Realm.getInstance(SimpleRealmApp.getInstance());
        realm.beginTransaction();

        Student realmStudent = realm.createObject(Student.class);
        realmStudent.setId(UUID.randomUUID().toString());
        realmStudent.setName(student.getName());
        realmStudent.setEmail(student.getEmail());
        realmStudent.setBirthday(student.getBirthday());

        University university = realm.where(University.class).equalTo(RealmTable.ID, universityId).findFirst();
        university.getStudents().add(realmStudent);

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
        RealmResults<Student> results = realm.where(Student.class).findAll();

        if (callback != null)
            callback.onSuccess(results);
    }

    @Override
    public void getAllStudentsByUniversityId(String id, OnGetStudentsCallback callback) {
        Realm realm = Realm.getInstance(SimpleRealmApp.getInstance());
        University university = realm.where(University.class).equalTo(RealmTable.ID, id).findFirst();
        RealmList<Student> students = university.getStudents();

        if (callback != null)
            callback.onSuccess(students);

    }

    @Override
    public void getStudentById(String id, OnGetStudentByIdCallback callback) {
        Realm realm = Realm.getInstance(SimpleRealmApp.getInstance());
        Student student = realm.where(Student.class).equalTo(RealmTable.ID, id).findFirst();

        if (callback != null)
            callback.onSuccess(student);
    }
}
