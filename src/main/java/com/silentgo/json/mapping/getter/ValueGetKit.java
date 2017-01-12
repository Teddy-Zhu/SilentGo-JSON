package com.silentgo.json.mapping.getter;

import com.silentgo.json.model.JSONEntity;
import com.silentgo.utils.ConvertKit;
import com.silentgo.utils.TypeConvertKit;

import java.util.HashMap;
import java.util.Map;

/**
 * Project : json
 * Package : com.silentgo.json.mapping.getter
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/11.
 */
public class ValueGetKit {

    private static final Map<Class<?>, ValueGetter> valueGetterMap = new HashMap<>();

    static {
        ConvertKit.getConvertMap().get(String.class).forEach((clz, convertor) -> {
            valueGetterMap.put(clz, jsonEntity -> convertor.convert(jsonEntity.getString()));
        });
    }

    public static ValueGetter getValueGetter(Class<?> clz) {
        return valueGetterMap.get(TypeConvertKit.wrapperType(clz));
    }
}
