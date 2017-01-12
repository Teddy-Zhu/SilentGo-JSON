package com.silentgo.json.common;

import com.silentgo.json.parser.ByteReader;
import com.silentgo.json.model.*;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * Project : SilentGo
 * Package : com.silentgo.json.model
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/5.
 */
public class JSONConstructor {

    private static Map<Class<? extends JSONEntity>, Constructor> constructorMap;

    static {
        constructorMap = new HashMap<>();
        try {
            constructorMap.put(JSONDouble.class, JSONDouble.class.getConstructor(ByteReader.class));
            constructorMap.put(JSONLong.class, JSONLong.class.getConstructor(ByteReader.class));
            constructorMap.put(JSONObject.class, JSONObject.class.getConstructor(ByteReader.class));
            constructorMap.put(JSONArray.class, JSONArray.class.getConstructor(ByteReader.class));
            constructorMap.put(JSONBool.class, JSONBool.class.getConstructor(ByteReader.class));
            constructorMap.put(JSONString.class, JSONString.class.getConstructor(ByteReader.class));
            constructorMap.put(JSONNull.class, JSONNull.class.getConstructor(ByteReader.class));

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static Map<Class<? extends JSONEntity>, Constructor> getConstructorMap() {
        return constructorMap;
    }
}
