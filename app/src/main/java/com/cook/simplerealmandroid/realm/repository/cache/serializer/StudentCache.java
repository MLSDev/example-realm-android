package com.cook.simplerealmandroid.realm.repository.cache.serializer;

import android.content.Context;
import android.content.SharedPreferences;

import com.cook.simplerealmandroid.model.Student;
import com.cook.simplerealmandroid.realm.repository.cache.provider.ICache;

/**
 * Created by roma on 03.11.15.
 */
public class StudentCache implements ICache<Student>{

    private static final String STUDENTS_CACHE_PREFS_KEY = "StudentCache";
    private static final String STUDENTS_PREFS_KEY = "student";

    private SharedPreferences preferences;
    private StudentSerializer serializer;

    public StudentCache(Context context, StudentSerializer serializer) {
        this.serializer = serializer;
        this.preferences = context.getSharedPreferences(STUDENTS_CACHE_PREFS_KEY, Context.MODE_PRIVATE);
    }

    @Override
    public Student get() {
        if(isCached())
            return serializer.deserialize(preferences.getString(STUDENTS_PREFS_KEY, null));
        return null;
    }

    @Override
    public void put(Student student) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(STUDENTS_PREFS_KEY, serializer.serialize(student));
        editor.apply();
    }

    @Override
    public void clear() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(STUDENTS_PREFS_KEY);
        editor.apply();
    }

    @Override
    public boolean isCached() {
        return preferences.getString(STUDENTS_PREFS_KEY, null) != null;
    }
}
