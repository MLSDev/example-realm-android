package com.cook.simplerealmandroid.realm.table;

/**
 * Created by roma on 16.10.15.
 */
public interface RealmTable {

    String ID = "id";

    interface University {
        String STUDENTS = "students";
        String NAME = "name";
    }

    interface Student{
        String NAME = "name";
        String AGE = "age";
        String EMAIL = "email";
        String BOOKS = "books";
        String LESSONS = "lessons";
    }
}
