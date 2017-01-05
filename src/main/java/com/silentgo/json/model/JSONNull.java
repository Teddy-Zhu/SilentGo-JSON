package com.silentgo.json.model;

import com.silentgo.json.JSONReader;
import com.silentgo.json.JSONReport;

import static com.sun.corba.se.spi.activation.IIOP_CLEAR_TEXT.value;

/**
 * Project : SilentGo
 * Package : com.silentgo.json.model
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/4.
 */
public class JSONNull extends JSONEntity {

    public JSONNull() {
        super(null);
    }

    public JSONNull(String value) {
        super(null);
        if (!"null".equals(value.trim().toLowerCase())) {
            throw JSONReport.error(new JSONReader(value.getBytes(), 0, value.length()), "the value can not parsed");
        }
    }

    public JSONNull(JSONReader value) {
        super(null);
    }

    @Override
    public Object get() {
        return null;
    }

    @Override
    public String toString() {
        return "null";
    }
}
