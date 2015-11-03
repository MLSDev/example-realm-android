package com.cook.simplerealmandroid.realm.repository.cache.provider;

/**
 * Created by roma on 03.11.15.
 */
public interface ICache<T> {
    T get();
    void put(T t);
    void clear();
    boolean isCached();
}
