package com.cook.simplerealmandroid.model;


import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by roma on 14.10.15.
 */
public class University extends RealmObject {

    @PrimaryKey
    private String id;
    private String name;
    private RealmList<Student> students;

    public University() {
    }

    public University(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<Student> getStudents() {
        return students;
    }

    public void setStudents(RealmList<Student> students) {
        this.students = students;
    }

}
