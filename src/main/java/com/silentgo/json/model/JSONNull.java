package com.silentgo.json.model;

import com.silentgo.json.JSONGlobalConfig;
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
public class JSONNull extends JSONEntity {

    private static final String NullString = "null";

    public JSONNull() {
        super(null);
    }

    public JSONNull(String value) {
        super(null);
        if (!"null".equals(value.trim().toLowerCase())) {
            new JSONReport().report(new ByteReader(value.getBytes(), 0, value.length()), "the value can not parsed");
        }
    }

    public JSONNull(Reader value) {
        super(null);
    }

    @Override
    public String getString() {
        return NullString;
    }

    @Override
    public Object get() {
        return null;
    }

    @Override
    public String toString() {
        return JSONGlobalConfig.replaceNullWithEmptyString ? "\"\"" : NullString;
    }
}
