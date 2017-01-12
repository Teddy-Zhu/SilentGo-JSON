package com.silentgo.json.mapping;

import com.silentgo.json.JSON;
import com.silentgo.json.deserializer.Deserializer;
import com.silentgo.json.deserializer.DeserializerKit;
import com.silentgo.json.exception.DeserializerException;
import com.silentgo.json.mapping.inter.JSONMapper;
import com.silentgo.json.model.JSONEntity;
import com.silentgo.json.model.JSONLazy;
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
        return null;
    }

    @Override
    public <T> Collection<T> mapCollection(JSONEntity json, Class<T> tClass) {
        return null;
    }

    @Override
    public <T> T map(JSONEntity jsonLazy, Class<T> tClass, String name) {
        JSONEntity current = jsonLazy;
        if (StringKit.isNotBlank(name)) {
            if (jsonLazy instanceof JSONLazy && JSONObject.class.equals(((JSONLazy) jsonLazy).getType())) {
                JSONObject jsonObject = (JSONObject) jsonLazy.get(JSON.oneDepthConfig);
                current = jsonObject.get(name);
            } else if (jsonLazy instanceof JSONObject) {
                // current = jsonLazy;
            } else {
                throw new DeserializerException("json can not be case to object");
            }
        }
        Deserializer deserializer = DeserializerKit.createDeserializer(tClass);

        return (T) deserializer.getObject(current, null, name);
    }

    @Override
    public <T> T map(JSONEntity jsonLazy, T entity, String name) {
        return null;
    }

}
