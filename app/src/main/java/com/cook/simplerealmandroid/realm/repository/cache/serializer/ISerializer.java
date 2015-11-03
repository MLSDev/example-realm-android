package com.cook.simplerealmandroid.realm.repository.cache.serializer;

/**
 * Created by roma on 03.11.15.
 */
public interface ISerializer<T> {
    String serialize(T t);

    T deserialize(String json);
}
