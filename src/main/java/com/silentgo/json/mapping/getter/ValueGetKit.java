package com.silentgo.json.mapping.getter;

import com.silentgo.json.annotation.JSONField;
import com.silentgo.json.model.JSONEntity;
import com.silentgo.utils.ConvertKit;
import com.silentgo.utils.TypeConvertKit;
import com.silentgo.utils.convertor.StringToDateConvertor;
import com.silentgo.utils.reflect.SGField;

import java.util.Date;
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
            valueGetterMap.put(clz, (jsonEntity, sgField) -> convertor.convert(jsonEntity.getString()));
        });
        valueGetterMap.put(Date.class, (jsonEntity, sgField) -> {
            JSONField jsonField = sgField == null ? null : (JSONField) sgField.getAnnotation(JSONField.class);
            String format = sgField == null ? null : (jsonField == null ? null : jsonField.value());
            return ConvertKit.getTypeConvert(String.class, Date.class).convert(jsonEntity.getString(), format);
        });
    }

    public static Map<Class<?>, ValueGetter> getValueGetterMap() {
        return valueGetterMap;
    }

    public static ValueGetter getValueGetter(Class<?> clz) {
        return valueGetterMap.get(TypeConvertKit.wrapperType(clz));
    }
}
