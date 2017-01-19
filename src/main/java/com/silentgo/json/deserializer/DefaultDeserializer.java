package com.silentgo.json.deserializer;

import com.silentgo.json.model.JSONArray;
import com.silentgo.json.model.JSONEntity;
import com.silentgo.json.model.JSONObject;
import com.silentgo.utils.ClassKit;
import com.silentgo.utils.reflect.SGField;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Project : json
 * Package : com.silentgo.json.deserializer
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/11.
 */
public class DefaultDeserializer implements Deserializer {

    @Override
    public Object getObject(JSONEntity entity, SGField sgField, Object key, Object target) {
        if (entity instanceof JSONArray) {
            Collection<Object> list = target == null ? ClassKit.createCollection(Collection.class) : (Collection<Object>) target;
            List<JSONEntity> jsonEntities = (List<JSONEntity>) entity.get();
            for (JSONEntity jsonEntity : jsonEntities) {
                list.add(this.getObject(jsonEntity, null, null, null));
            }
            return list;
        }
        if (entity instanceof JSONObject) {
            Map<String, Object> map = new HashMap<>();
            Map<String, JSONEntity> entityMap = (Map<String, JSONEntity>) entity.get();

            for (Map.Entry<String, JSONEntity> stringJSONEntityEntry : entityMap.entrySet()) {
                map.put(stringJSONEntityEntry.getKey(), this.getObject(stringJSONEntityEntry.getValue(), null, stringJSONEntityEntry.getKey(), null));
            }
            return map;
        }
        return entity.get();
    }
}
