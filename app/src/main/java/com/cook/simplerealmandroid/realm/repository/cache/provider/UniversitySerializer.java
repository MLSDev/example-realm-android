package com.cook.simplerealmandroid.realm.repository.cache.provider;

import com.cook.simplerealmandroid.model.University;
import com.cook.simplerealmandroid.realm.repository.cache.serializer.ISerializer;
import com.google.gson.Gson;

/**
 * Created by roma on 03.11.15.
 */
public class UniversitySerializer implements ISerializer<University> {

    private final Gson gson = new Gson();

    @Override
    public String serialize(University university) {
        return gson.toJson(university);
    }

    @Override
    public University deserialize(String json) {
        return gson.fromJson(json, University.class);
    }
}
