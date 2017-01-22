package com.silentgo.json.deserializer;

import com.silentgo.json.mapping.ReaderKit;
import com.silentgo.json.model.JSONEntity;
import com.silentgo.json.model.JSONObject;
import com.silentgo.utils.reflect.SGField;

import java.util.HashMap;
import java.util.Map;

/**
 * Project : json
 * Package : com.silentgo.json.deserializer
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/11.
 */
public class MapDeserializer implements Deserializer {

    private Deserializer child;

    public MapDeserializer() {
    }

    public MapDeserializer(Deserializer child) {
        this.child = child;
    }

    public Deserializer getChild() {
        return child;
    }

    public void setChild(Deserializer child) {
        this.child = child;
    }

    @Override
    public Object getObject(JSONEntity entity, SGField sgField, Object key, Object target) {
        JSONObject jsonObject = ReaderKit.getTarget(entity, JSONObject.class, "json can not be transformed to json object");

        Map<String, Object> map = target == null ? new HashMap<>() : (Map<String, Object>) target;
        Map<String, JSONEntity> objectMap = jsonObject.get();

        objectMap.forEach((k, v) -> map.put(k, child.getObject(v, null, k, null)));
        return map;
    }
}
