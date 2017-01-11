package com.silentgo.json.mapping.valreader;

import com.silentgo.json.model.JSONEntity;
import com.silentgo.json.model.JSONObject;
import com.silentgo.utils.reflect.SGEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Project : json
 * Package : com.silentgo.json.mapping.valreader
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/11.
 */
public class JSONObjectReader implements ValueReader{
    @Override
    public boolean canRead(Class<?> type, JSONEntity jsonEntity, Object fieldKey, JSONEntity parentJSON, JSONEntity jsonLazy, SGEntity currentField, SGEntity parentField) {
        return JSONObject.class.equals(type);
    }

    @Override
    public Object readValue(Class<?> type, JSONEntity jsonEntity, Object fieldKey, JSONEntity parentJSON, JSONEntity jsonLazy, SGEntity currentField, SGEntity parentField) {

        Map<String, Object> ret = new HashMap<>();
        ((JSONObject) jsonEntity).get().forEach((k, v) -> ret.put(k, ReaderKit.readValue(v.getClass(), v, k, jsonEntity, jsonLazy, null, parentField)));
        return ret;
    }
}
