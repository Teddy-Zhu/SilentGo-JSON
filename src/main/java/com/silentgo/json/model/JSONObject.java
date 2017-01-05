package com.silentgo.json.model;

import com.silentgo.json.JSON;
import com.silentgo.json.JSONReader;
import com.silentgo.json.JSONReaderKit;
import com.silentgo.json.configuration.JSONConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * Project : SilentGo
 * Package : com.silentgo.json
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/4.
 */
public class JSONObject extends JSONEntity {

    private Map<String, JSONEntity> map;

    public JSONObject() {
        super(null);
        map = new HashMap<>();
    }

    public JSONObject(JSONReader value) {
        super(null);
        this.map = new HashMap<>();

        JSONReaderKit.readJSONObject(value, new JSONConfig(), this);
    }

    @Override
    public Object get() {
        return map;
    }


    public Object get(String key) {
        return map.get(key);
    }

    public boolean put(String name, JSONEntity jsonEntity) {
        map.put(name, jsonEntity);
        return true;
    }

    @Override
    public String toString() {
        return "JSONObject{" +
                "map=" + map +
                '}';
    }
}
