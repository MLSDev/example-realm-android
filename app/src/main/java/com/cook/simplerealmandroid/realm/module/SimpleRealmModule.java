package com.cook.simplerealmandroid.realm.module;

import com.cook.simplerealmandroid.model.Student;
import com.cook.simplerealmandroid.model.University;

import io.realm.annotations.RealmModule;

/**
 * Created by roma on 15.10.15.
 */
@RealmModule(classes = {Student.class, University.class})
public class SimpleRealmModule {

}
