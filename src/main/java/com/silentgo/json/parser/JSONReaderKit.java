package com.silentgo.json.parser;

import com.silentgo.json.model.*;
import com.silentgo.json.parser.jsonvaluereader.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Project : SilentGo
 * Package : com.silentgo.json
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/4.
 */
public class JSONReaderKit {

    private static final Map<Class<? extends JSONEntity>, JSONValueReader> valueReaderMap;

    static {
        valueReaderMap = new HashMap<>();
        valueReaderMap.put(JSONEntity.class, new CommonValueReader());
        valueReaderMap.put(JSONBool.class, new BoolValueReader());
        valueReaderMap.put(JSONNumber.class, new NumberValueReader());
        valueReaderMap.put(JSONNull.class, new NullValueReader());
        valueReaderMap.put(JSONString.class, new StringValueReader());
        valueReaderMap.put(JSONObject.class, new ObjectValueReader());
        valueReaderMap.put(JSONArray.class, new ArrayValueReader());

    }

    public static JSONValueReader getReader(Class<? extends JSONEntity> clz) {
        return valueReaderMap.get(clz);
    }


    public static byte nextWord(JSONReader reader) {
        while (reader.hasNext()) {
            byte b = reader.next();
            switch (b) {
                case ' ':
                case '\t':
                case '\n':
                case '\r':
                    continue;
                default:
                    return b;
            }
        }
        return JSONReader.BYTE_NULL;
    }

    public static String getString(JSONReader reader) {
        return new String(reader.data, reader.pos, reader.end - reader.pos);
    }

}
