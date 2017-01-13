package com.silentgo.json.model;

import com.silentgo.json.parser.ByteReader;
import com.silentgo.json.parser.JSONReaderKit;
import com.silentgo.json.configuration.JSONConfig;
import com.silentgo.json.parser.Reader;

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

    public JSONObject(Reader value) {
        super(null);
        this.map = new HashMap<>();

        JSONReaderKit.Object.readValue(value, new JSONConfig(false), this, 0);
    }

    @Override
    public Map<String, JSONEntity> get() {
        return map;
    }


    public JSONEntity get(String key) {
        return map.get(key);
    }

    public boolean put(String name, JSONEntity jsonEntity) {
        map.put(name, jsonEntity);
        return true;
    }

    @Override
    public String toString() {
        return map.toString();
    }

}
