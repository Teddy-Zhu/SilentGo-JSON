package com.silentgo.json.mapping.valreader;

import com.silentgo.json.model.JSONArray;
import com.silentgo.json.model.JSONEntity;
import com.silentgo.json.parser.Reader;
import com.silentgo.utils.ClassKit;
import com.silentgo.utils.reflect.SGEntity;
import com.silentgo.utils.reflect.SGField;

import java.util.Collection;
import java.util.List;

/**
 * Project : json
 * Package : com.silentgo.json.mapping.valreader
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/11.
 */
public class CollectionReader implements ValueReader {
    @Override
    public boolean canRead(Class<?> type, JSONEntity jsonEntity, Object fieldKey, JSONEntity parentJSON, JSONEntity jsonLazy, SGEntity currentField, SGEntity parentField) {
        return Collection.class.isAssignableFrom(type);
    }

    @Override
    public Object readValue(Class<?> type, JSONEntity jsonEntity, Object fieldKey, JSONEntity parentJSON, JSONEntity jsonLazy, SGEntity currentField, SGEntity parentField) {
        JSONArray jsonArray = ReaderKit.getTarget(jsonEntity, JSONArray.class, "json can not be transformed to array");

        Collection result = ClassKit.createCollection(type);
        List<JSONEntity> jsonEntities = jsonArray.get();

        Class<?> ctype = JSONEntity.class;
        if (currentField instanceof SGField) {
            java.lang.reflect.Type tmpType = ((SGField) currentField).getField().getGenericType();
            ctype = ClassKit.getActualType(tmpType);
        }
        for (int i = 0; i < jsonEntities.size(); i++) {
            JSONEntity entity = jsonEntities.get(i);
            result.add(ReaderKit.readValue(ctype, entity, i, jsonArray, jsonLazy, null, currentField));
        }
        return result;
    }
}
