package com.silentgo.json.mapping.valreader;

import com.silentgo.json.configuration.JSONConfig;
import com.silentgo.json.exception.DeserializerException;
import com.silentgo.json.model.JSONArray;
import com.silentgo.json.model.JSONEntity;
import com.silentgo.json.model.JSONLazy;
import com.silentgo.utils.reflect.SGEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Project : json
 * Package : com.silentgo.json.mapping.valreader
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/11.
 */
public class ReaderKit {

    private static final List<ValueReader> reader = new ArrayList<>();
    private static final Map<Class<? extends ValueReader>, ValueReader> readerMap = new HashMap<>();

    static {
        reader.add(new ArrayReader());
        reader.add(new CollectionReader());
        reader.add(new MapReader());
        reader.add(new JSONArrayReader());
        reader.add(new JSONObjectReader());
        reader.add(new JSONEntityReader());
        reader.add(new CommonReader());
        for (ValueReader valueReader : reader) {
            readerMap.put(valueReader.getClass(), valueReader);
        }
    }

    public static ValueReader getReader(Class<? extends ValueReader> clz) {
        return readerMap.get(clz);
    }

    public static <T extends JSONEntity> T getTarget(JSONEntity jsonEntity, Class<T> target, String msg) {
        T t;
        if (jsonEntity instanceof JSONLazy && JSONArray.class.equals(((JSONLazy) jsonEntity).getType())) {
            t = (T) jsonEntity.get(new JSONConfig(1));
        } else if (jsonEntity instanceof JSONArray) {
            t = (T) jsonEntity;
        } else {
            throw new DeserializerException(msg);
        }
        return t;
    }

    public static Object readValue(Class<?> type, JSONEntity jsonEntity, Object fieldKey, JSONEntity parentJSON, JSONEntity jsonLazy, SGEntity currentField, SGEntity parentField) {
        for (ValueReader valueReader : reader) {
            if (valueReader.canRead(type, jsonEntity, fieldKey, parentJSON, jsonLazy, currentField, parentField)) {
                return valueReader.readValue(type, jsonEntity, fieldKey, parentJSON, jsonLazy, currentField, parentField);
            }
        }
        return null;
    }
}
