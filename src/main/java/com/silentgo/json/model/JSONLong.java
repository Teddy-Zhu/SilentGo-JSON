package com.silentgo.json.model;

import com.silentgo.json.parser.ByteReader;
import com.silentgo.json.parser.Reader;

/**
 * Project : SilentGo
 * Package : com.silentgo.json.model
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/4.
 */
public class JSONLong extends JSONNumber {
    private Long value;

    public JSONLong(String value) {
        super(null);
        setString(value);
        this.value = Long.valueOf(value);
    }

    public JSONLong(Reader value) {
        super(value);
        String val = value.peekRange(value.pos, value.end);
        setString(val);
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
