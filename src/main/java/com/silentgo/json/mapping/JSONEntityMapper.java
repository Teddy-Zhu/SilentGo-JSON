package com.silentgo.json.mapping;

import com.silentgo.json.deserializer.CollectionDeserializer;
import com.silentgo.json.deserializer.Deserializer;
import com.silentgo.json.deserializer.DeserializerKit;
import com.silentgo.json.mapping.inter.JSONMapper;
import com.silentgo.json.model.JSONArray;
import com.silentgo.json.model.JSONEntity;
import com.silentgo.json.model.JSONObject;
import com.silentgo.utils.StringKit;

import java.util.Collection;

/**
 * Project : json
 * Package : com.silentgo.json.mapping
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/7.
 */
public class JSONEntityMapper implements JSONMapper<JSONEntity> {


    @Override
    public <T> Collection<T> mapCollection(JSONEntity json, Class<T> tClass, String name) {
        if (json == null) return null;
        JSONEntity current = json;
        if (StringKit.isNotBlank(name)) {
            current = ReaderKit.getTarget(json, JSONObject.class, "json entity must be object");
        }
        return mapCollection(current, tClass);
    }

    @Override
    public <T> Collection<T> mapCollection(JSONEntity json, Class<T> tClass) {
        if (json == null) return null;
        JSONArray jsonArray = ReaderKit.getTarget(json, JSONArray.class, "json must be array");
        return (Collection<T>) new CollectionDeserializer(DeserializerKit.createDeserializer(tClass)).getObject(jsonArray, null, null, null);
    }

    @Override
    public <T> T map(JSONEntity jsonLazy, Class<T> tClass, String name) {
        if (jsonLazy == null) return null;
        JSONEntity current = jsonLazy;
        if (StringKit.isNotBlank(name)) {
            current = ReaderKit.getTarget(jsonLazy, JSONObject.class, "json entity must be object");
        }
        Deserializer deserializer = DeserializerKit.createDeserializer(tClass);

        return (T) deserializer.getObject(current, null, name, null);
    }

    @Override
    public <T> T map(JSONEntity jsonLazy, T entity, String name) {
        if (jsonLazy == null) return null;
        JSONEntity current = jsonLazy;
        if (StringKit.isNotBlank(name)) {
            current = ReaderKit.getTarget(jsonLazy, JSONObject.class, "json entity must be object");
        }
        Deserializer deserializer = DeserializerKit.createDeserializer(entity.getClass());

        return (T) deserializer.getObject(current, null, name, entity);
    }

}
