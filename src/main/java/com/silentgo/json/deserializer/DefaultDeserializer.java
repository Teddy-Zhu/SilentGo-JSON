package com.silentgo.json.deserializer;

import com.silentgo.json.model.JSONArray;
import com.silentgo.json.model.JSONEntity;
import com.silentgo.json.model.JSONObject;
import com.silentgo.utils.ClassKit;

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
    public Object getObject(JSONEntity entity, Object key) {
        if (entity instanceof JSONArray) {
            List<Object> list = (List<Object>) ClassKit.createCollection(List.class);
            List<JSONEntity> jsonEntities = (List<JSONEntity>) entity.get();
            for (JSONEntity jsonEntity : jsonEntities) {
                list.add(this.getObject(jsonEntity, null));
            }
            return list;
        }
        if (entity instanceof JSONObject) {
            Map<String, Object> map = new HashMap<>();
            Map<String, JSONEntity> entityMap = (Map<String, JSONEntity>) entity.get();
            entityMap.forEach((k, v) -> {
                map.put(k, this.getObject(v, k));
            });
            return map;
        }
        return entity.get();
    }
}
