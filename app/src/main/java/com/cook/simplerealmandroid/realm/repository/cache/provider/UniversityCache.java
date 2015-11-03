package com.cook.simplerealmandroid.realm.repository.cache.provider;

import android.content.Context;
import android.content.SharedPreferences;

import com.cook.simplerealmandroid.model.University;

/**
 * Created by roma on 03.11.15.
 */
public class UniversityCache implements ICache<University> {

    private static final String UNIVERSITIES_CACHE_PREFS_KEY = "UniversityCache";
    private static final String UNIVERSITIES_PREFS_KEY = "university";

    private final SharedPreferences preferences;
    private final UniversitySerializer serializer;

    public UniversityCache(Context context, UniversitySerializer serializer) {
        this.serializer = serializer;
        this.preferences = context.getSharedPreferences(UNIVERSITIES_CACHE_PREFS_KEY, Context.MODE_PRIVATE);
    }

    @Override
    public University get() {
        if(isCached())
            return serializer.deserialize(preferences.getString(UNIVERSITIES_PREFS_KEY, null));
        return null;
    }

    @Override
    public void put(University university) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(UNIVERSITIES_PREFS_KEY, serializer.serialize(university));
        editor.apply();
    }

    @Override
    public void clear() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(UNIVERSITIES_PREFS_KEY);
        editor.apply();
    }

    @Override
    public boolean isCached() {
        return preferences.getString(UNIVERSITIES_PREFS_KEY, null) != null;
    }
}
