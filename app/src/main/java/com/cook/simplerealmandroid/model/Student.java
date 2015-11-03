package com.cook.simplerealmandroid.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by roma on 14.10.15.
 */
public class Student extends RealmObject {

    @PrimaryKey
    private String id;
    private int age;
    private String name;
    private String email;
    private RealmList<Book> books;
    private RealmList<Lesson> lessons;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RealmList<Book> getBooks() {
        return books;
    }

    public void setBooks(RealmList<Book> books) {
        this.books = books;
    }

    public RealmList<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(RealmList<Lesson> lessons) {
        this.lessons = lessons;
    }
}
