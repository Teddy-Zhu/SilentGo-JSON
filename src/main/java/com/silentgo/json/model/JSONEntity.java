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
public abstract class JSONEntity {

    private String string;

    public JSONEntity(ByteReader value) {
    }

    public String getString() {
        return string;
    }

    public abstract Object get();

    public Object get(JSONConfig jsonConfig) {
        return get();
    }

    public void setString(String string) {
        this.string = string;
    }
}
