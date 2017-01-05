package com.silentgo.json.model;

import com.silentgo.json.JSON;
import com.silentgo.json.JSONReader;
import com.silentgo.json.JSONReaderKit;
import com.silentgo.json.JSONReport;

/**
 * Project : SilentGo
 * Package : com.silentgo.json.model
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/4.
 */
public class JSONDouble extends JSONEntity {

    private Double value;

    public JSONDouble(String value) {
        super(null);
        int d = value.indexOf('.');
        if (d == 0 || d == value.length() - 1) {
            throw JSONReport.error(new JSONReader(value.getBytes(), d, value.length()), "the number has error format");
        }
        this.value = Double.valueOf(value);
    }

    public JSONDouble(JSONReader value) {
        super(value);
        String val = new String(value.getData(), value.getPos(), value.getEnd());
        this.value = Double.valueOf(val);
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
