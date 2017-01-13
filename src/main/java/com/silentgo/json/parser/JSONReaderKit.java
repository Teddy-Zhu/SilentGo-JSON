package com.silentgo.json.parser;

import com.silentgo.json.model.*;
import com.silentgo.json.parser.jsonvaluereader.*;

import java.io.IOException;
import java.io.InputStream;
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


    public static final JSONValueReader Number = new NumberValueReader();
    public static final JSONValueReader Bool = new BoolValueReader();
    public static final JSONValueReader Entity = new CommonValueReader();
    public static final JSONValueReader Null = new NullValueReader();
    public static final JSONValueReader String = new StringValueReader();
    public static final JSONValueReader Object = new ObjectValueReader();
    public static final JSONValueReader Array = new ArrayValueReader();


    private static final Map<Class<? extends JSONEntity>, JSONValueReader> map = new HashMap<Class<? extends JSONEntity>, JSONValueReader>() {
        {
            put(JSONDouble.class, Number);
            put(JSONLong.class, Number);
            put(JSONBool.class, Bool);
            put(JSONEntity.class, Entity);
            put(JSONNull.class, Null);
            put(JSONString.class, String);
            put(JSONObject.class, Object);
            put(JSONArray.class, Array);
        }
    };

    public static JSONValueReader get(Class<? extends JSONEntity> clz) {
        return map.get(clz);
    }

    public static char nextWord(Reader reader) {

        while (reader.hasNext()) {
            char b = reader.next();
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
        return ByteReader.NULL;
    }

    public static String getString(Reader reader) {
        return reader.peekRange(reader.pos, reader.end - reader.pos);
    }

}
