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
public class JSONArrayReader implements ValueReader {
    @Override
    public boolean canRead(Class<?> type, JSONEntity jsonEntity, Object fieldKey, JSONEntity parentJSON, JSONEntity jsonLazy, SGEntity currentField, SGEntity parentField) {
        return JSONArray.class.equals(type);
    }

    @Override
    public Object readValue(Class<?> type, JSONEntity jsonEntity, Object fieldKey, JSONEntity parentJSON, JSONEntity jsonLazy, SGEntity currentField, SGEntity parentField) {
        List<Object> ret = new ArrayList<>();
        List<JSONEntity> entities = ((JSONArray) jsonEntity).get();
        for (int i = 0; i < entities.size(); i++) {
            JSONEntity entity = entities.get(i);
            ret.add(ReaderKit.readValue(entity.getClass(), entity, i, jsonEntity, jsonLazy, null, parentField));
        }
        return ret;
    }
}
