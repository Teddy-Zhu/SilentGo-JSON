package com.silentgo.json.mapping.valreader;

import com.silentgo.json.model.JSONEntity;
import com.silentgo.json.model.JSONObject;
import com.silentgo.json.parser.Reader;
import com.silentgo.utils.ClassKit;
import com.silentgo.utils.reflect.SGEntity;
import com.silentgo.utils.reflect.SGField;

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
public class MapReader implements ValueReader {
    @Override
    public boolean canRead(Class<?> type, JSONEntity jsonEntity, Object fieldKey, JSONEntity parentJSON, JSONEntity jsonLazy, SGEntity currentField, SGEntity parentField) {
        return Map.class.isAssignableFrom(type);
    }

    @Override
    public Object readValue(Class<?> type, JSONEntity jsonEntity, Object fieldKey, JSONEntity parentJSON, JSONEntity jsonLazy, SGEntity currentField, SGEntity parentField) {
        JSONObject jsonObject = ReaderKit.getTarget(jsonEntity, JSONObject.class, "json can not be transformed to object");

        Map<String, Object> map = new HashMap<>();
        Map<String, JSONEntity> objectMap = jsonObject.get();

        Class<?> ctype = JSONEntity.class;
        if (currentField instanceof SGField) {
            Class<?> tmpType = (Class<?>) ((SGField) currentField).getField().getGenericType();
            ctype = ClassKit.getActualType(tmpType, 1);
        }
        Class<?> finalCtype = ctype;
        objectMap.forEach((k, v) -> {
            map.put(k, ReaderKit.readValue(finalCtype, jsonObject.get(k), k, jsonEntity, jsonLazy, null, parentField));
        });
        return map;
    }
}
