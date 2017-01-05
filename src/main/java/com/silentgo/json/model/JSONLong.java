package com.silentgo.json.model;

import com.silentgo.json.JSONReader;

/**
 * Project : SilentGo
 * Package : com.silentgo.json.model
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/4.
 */
public class JSONLong extends JSONEntity {
    private Long value;

    public JSONLong(String value) {
        super(null);
        this.value = Long.valueOf(value);
    }

    public JSONLong(JSONReader value) {
        super(value);
        String val = new String(value.getData(), value.getPos(), value.getEnd());
        this.value = Long.valueOf(val);
    }

    @Override
    public Object get() {
        return value;
    }

    @Override
    public String toString() {
        return value == null ? "" : value.toString();
    }
}
