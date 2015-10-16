package com.cook.simplerealmandroid.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by roma on 14.10.15.
 */
public class Book extends RealmObject {

    @PrimaryKey
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
