package com.silentgo.json.model;

import com.silentgo.json.configuration.JSONConfig;
import com.silentgo.json.parser.ByteReader;

/**
 * Project : SilentGo
 * Package : com.silentgo.json.model
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/4.
 */
public class JSONBool extends JSONEntity {
    private Boolean value;

    public JSONBool(String value) {
        super(null);
        setString(value);
        this.value = Boolean.valueOf(value);
    }

    public JSONBool(ByteReader reader) {
        super(null);
        String value = new String(reader.data, reader.pos, reader.end);
        setString(value);
        this.value = Boolean.valueOf(value);
    }

    @Override
    public Object get() {
        return value;
    }

    @Override
    public Object get(JSONConfig jsonConfig) {
        return get();
    }

    @Override
    public String toString() {
        return value == null ? "false" : value.toString();
    }
}
