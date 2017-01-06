package com.silentgo.json.model;

import com.silentgo.json.parser.JSONReader;

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
        this.value = Boolean.valueOf(value);
    }

    public JSONBool(JSONReader reader) {
        super(null);
        String value = new String(reader.getData(), reader.getPos(), reader.getEnd());
        this.value = Boolean.valueOf(value);
    }

    @Override
    public Object get() {
        return value;
    }

    @Override
    public String toString() {
        return value == null ? "false" : value.toString();
    }
}
