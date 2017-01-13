package com.silentgo.json.model;

import com.silentgo.json.parser.ByteReader;
import com.silentgo.json.parser.Reader;
import com.silentgo.json.report.JSONReport;

/**
 * Project : SilentGo
 * Package : com.silentgo.json.model
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/4.
 */
public class JSONDouble extends JSONNumber {

    private Double value;

    public JSONDouble(String value) {
        super(null);
        int d = value.indexOf('.');
        if (d == 0 || d == value.length() - 1) {
            new JSONReport().report(new ByteReader(value.getBytes(), d, value.length()), "the number has error format");
        }
        setString(value);
        this.value = Double.valueOf(value);
    }

    public JSONDouble(Reader value) {
        super(value);
        String val = value.peekRange(value.pos, value.end);
        setString(val);
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
