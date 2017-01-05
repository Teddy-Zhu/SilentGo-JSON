package com.silentgo.json.model;

import com.silentgo.json.JSON;
import com.silentgo.json.JSONReader;
import com.silentgo.json.JSONReaderKit;
import com.silentgo.json.configuration.JSONConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Project : SilentGo
 * Package : com.silentgo.json
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/4.
 */
public class JSONArray extends JSONEntity {

    private List<JSONEntity> list;

    public JSONArray() {
        super(null);
        this.list = new ArrayList<>();
    }

    public JSONArray(JSONReader reader) {
        super(null);
        list = new ArrayList<>();

        JSONReaderKit.readJSONArray(reader, new JSONConfig(), this);
    }

    @Override
    public Object get() {
        return list;
    }

    public Object get(int i) {
        return list.get(i);
    }

    public boolean push(JSONEntity object) {
        return list.add(object);
    }

    @Override
    public String toString() {
        return "JSONArray{" +
                "list=" + list +
                '}';
    }
}
