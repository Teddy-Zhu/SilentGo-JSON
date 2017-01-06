package com.silentgo.json.common;

import com.silentgo.json.parser.JSONReader;
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
            constructorMap.put(JSONDouble.class, JSONDouble.class.getConstructor(JSONReader.class));
            constructorMap.put(JSONLong.class, JSONLong.class.getConstructor(JSONReader.class));
            constructorMap.put(JSONObject.class, JSONObject.class.getConstructor(JSONReader.class));
            constructorMap.put(JSONArray.class, JSONArray.class.getConstructor(JSONReader.class));
            constructorMap.put(JSONBool.class, JSONBool.class.getConstructor(JSONReader.class));
            constructorMap.put(JSONString.class, JSONString.class.getConstructor(JSONReader.class));
            constructorMap.put(JSONNull.class, JSONNull.class.getConstructor(JSONReader.class));

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static Map<Class<? extends JSONEntity>, Constructor> getConstructorMap() {
        return constructorMap;
    }
}
