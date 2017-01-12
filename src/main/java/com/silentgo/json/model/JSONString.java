package com.silentgo.json.model;

import com.silentgo.json.parser.ByteReader;

/**
 * Project : SilentGo
 * Package : com.silentgo.json.model
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/4.
 */
public class JSONString extends JSONEntity {

    private String value;

    public JSONString(String value) {
        super(null);
        this.value = value;
    }

    public JSONString(ByteReader value) {
        super(value);
        this.value = new String(value.data, value.pos, value.end - value.pos);
    }

    @Override
    public String getString() {
        return value;
    }

    public String get() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
