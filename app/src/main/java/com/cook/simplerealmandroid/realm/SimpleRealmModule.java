package com.cook.simplerealmandroid.realm;

import com.cook.simplerealmandroid.model.Book;
import com.cook.simplerealmandroid.model.Lesson;
import com.cook.simplerealmandroid.model.Student;
import com.cook.simplerealmandroid.model.University;

import io.realm.annotations.RealmModule;

/**
 * Created by roma on 15.10.15.
 */
@RealmModule(classes = {Student.class, Lesson.class, Book.class, University.class})
public class SimpleRealmModule {

}
