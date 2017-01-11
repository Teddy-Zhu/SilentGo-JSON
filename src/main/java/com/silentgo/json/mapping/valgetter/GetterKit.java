package com.silentgo.json.mapping.valgetter;

import com.silentgo.utils.ClassKit;
import com.silentgo.utils.ConvertKit;
import com.silentgo.utils.TypeConvertKit;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Project : json
 * Package : com.silentgo.json.mapping.valgetter
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/9.
 */
public class GetterKit {

    private static Map<Class<?>, ValueGetter> typeGetter = new HashMap<>();

    static {
        for (Class<?> aClass : TypeConvertKit.getType()) {
            typeGetter.put(aClass, (key, value, current, parentJSON, jsonLazy, field) -> ConvertKit.getTypeConvert(String.class, aClass).convert(value));
        }
    }

    public static void addGetter(ValueGetter valueGetter) {
        Class<?> clz = ClassKit.getGenericClass(valueGetter.getClass(), 0);
        typeGetter.put(clz, valueGetter);
    }

    public static ValueGetter getValueGetter(Class<?> type) {
        type = TypeConvertKit.wrapperType(type);
        return typeGetter.get(type);
    }
}
