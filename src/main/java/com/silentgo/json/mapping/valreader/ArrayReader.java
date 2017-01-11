package com.silentgo.json.mapping.valreader;

import com.silentgo.json.model.JSONArray;
import com.silentgo.json.model.JSONEntity;
import com.silentgo.utils.reflect.SGEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Project : json
 * Package : com.silentgo.json.mapping.valreader
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/11.
 */
public class ArrayReader implements ValueReader {
    @Override
    public boolean canRead(Class<?> type, JSONEntity jsonEntity, Object fieldKey, JSONEntity parentJSON, JSONEntity jsonLazy, SGEntity currentField, SGEntity parentField) {
        return type.isArray();
    }

    @Override
    public Object readValue(Class<?> type, JSONEntity jsonEntity, Object fieldKey, JSONEntity parentJSON, JSONEntity jsonLazy, SGEntity currentField, SGEntity parentField) {
        JSONArray jsonArray = ReaderKit.getTarget(jsonEntity, JSONArray.class, "json can not be transformed to array");

        Class<?> ctype = type.getComponentType();
        List<Object> result = new ArrayList<>();
        List<JSONEntity> jsonEntities = jsonArray.get();

        for (int i = 0; i < jsonEntities.size(); i++) {
            JSONEntity entity = jsonEntities.get(i);
            result.add(ReaderKit.readValue(ctype, entity, i, jsonArray, jsonLazy, null, currentField));
        }
        return result.toArray();
    }
}
