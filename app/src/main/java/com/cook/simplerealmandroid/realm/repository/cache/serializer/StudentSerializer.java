package com.cook.simplerealmandroid.realm.repository.cache.serializer;

import com.cook.simplerealmandroid.model.Student;
import com.google.gson.Gson;

/**
 * Created by roma on 03.11.15.
 */
public class StudentSerializer implements ISerializer<Student> {

    private Gson gson = new Gson();

    @Override
    public String serialize(Student student) {
        return gson.toJson(student);
    }

    @Override
    public Student deserialize(String json) {
        return gson.fromJson(json, Student.class);
    }
}
