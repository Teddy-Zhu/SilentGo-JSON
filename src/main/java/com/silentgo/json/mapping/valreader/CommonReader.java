package com.silentgo.json.mapping.valreader;

import com.silentgo.json.JSON;
import com.silentgo.json.annotation.JSONConstructor;
import com.silentgo.json.annotation.JSONField;
import com.silentgo.json.exception.DeserializerException;
import com.silentgo.json.mapping.valgetter.GetterKit;
import com.silentgo.json.mapping.valgetter.ValueGetter;
import com.silentgo.json.model.JSONEntity;
import com.silentgo.json.model.JSONLazy;
import com.silentgo.json.model.JSONObject;
import com.silentgo.utils.ReflectKit;
import com.silentgo.utils.common.Const;
import com.silentgo.utils.reflect.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Project : json
 * Package : com.silentgo.json.mapping.valreader
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/11.
 */
public class CommonReader implements ValueReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonReader.class);


    @Override
    public boolean canRead(Class<?> type, JSONEntity jsonEntity, Object fieldKey, JSONEntity parentJSON, JSONEntity jsonLazy, SGEntity currentField, SGEntity parentField) {
        return true;
    }

    @Override
    public Object readValue(Class<?> type, JSONEntity jsonEntity, Object fieldKey, JSONEntity parentJSON, JSONEntity jsonLazy, SGEntity currentField, SGEntity parentField) {
        ValueGetter valueGetter = GetterKit.getValueGetter(type);

        if (valueGetter == null) {
            if (type.getClassLoader() == null)
                throw new DeserializerException("not found ValueGetter for type :" + type.getName() + " please implement ValueGetter");
            else {

                JSONObject jsonObject = null;
                if (jsonEntity instanceof JSONLazy && JSONObject.class.equals(((JSONLazy) jsonEntity).getType())) {
                    jsonObject = (JSONObject) jsonEntity.get(JSON.oneDepthConfig);
                } else if (jsonEntity instanceof JSONObject) {
                    jsonObject = (JSONObject) jsonEntity;
                } else {
                    throw new DeserializerException("json can not to be transformed to type:" + type.getName());
                }

                SGClass sgClass = ReflectKit.getSGClass(type);

                Object result = null;

                SGConstructor constructor = getConstructor(sgClass.getDefaultConstructor(), sgClass.getConstructors());
                if (constructor == null)
                    return new DeserializerException("can not found constructor for type : " + type);

                if (constructor.isDefault()) {
                    try {
                        if (!constructor.getConstructor().isAccessible()) {
                            constructor.getConstructor().setAccessible(true);
                        }
                        result = constructor.getConstructor().newInstance();
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        LOGGER.error("type :" + type.getName() + " instance failed by default", e);
                        throw new DeserializerException("type :" + type.getName() + " instance failed by default", e);
                    }
                }
                Map<String, Object> fieldMap = new HashMap<>();

                Object finalResult = result;
                JSONObject finalJsonObject = jsonObject;
                sgClass.getFieldMap().forEach(((s, field) -> {
                    if (!hasSet(field)) return;
                    JSONEntity entity = finalJsonObject.get(s);
                    if (entity == null) return;
                    if (finalResult != null)
                        setValue(finalResult, ReaderKit.readValue(field.getType(), entity, s, finalJsonObject, jsonLazy, field, parentField), field);
                    else
                        fieldMap.put(s, ReaderKit.readValue(field.getType(), entity, s, finalJsonObject, jsonLazy, field, parentField));
                }));

                if (!constructor.isDefault()) {
                    Object[] obj = new Object[constructor.getParameterNames().length];
                    String[] parameters = constructor.getParameterNames();
                    for (int i = 0; i < parameters.length; i++) {
                        String parameterName = parameters[i];
                        SGParameter sgParameter = constructor.getParameterMap().get(parameterName);
                        JSONField jsonField = (JSONField) sgParameter.getAnnotationMap().get(JSONField.class);
                        if (Const.EmptyString.equals(jsonField.value())) {
                            obj[i] = fieldMap.get(parameterName);
                        } else {
                            obj[i] = fieldMap.get(jsonField.value());
                        }
                    }
                    try {
                        if (!constructor.getConstructor().isAccessible()) {
                            constructor.getConstructor().setAccessible(true);
                        }
                        result = constructor.getConstructor().newInstance(obj);
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        LOGGER.error("type :" + type.getName() + " instance failed by instance : " + constructor.getConstructor(), e);
                        throw new DeserializerException("type :" + type.getName() + " instance failed by instance : " + constructor.getConstructor(), e);
                    }

                }
                return result;

            }
        } else {
            return valueGetter.get(fieldKey, jsonEntity.getString(), jsonEntity, parentJSON, jsonLazy, parentField);
        }
    }

    private SGConstructor getConstructor(SGConstructor defaultConstrictor, List<SGConstructor> constructors) {
        if (defaultConstrictor != null) return defaultConstrictor;
        for (SGConstructor constructor : constructors) {
            if (constructor.getAnnotationMap().get(JSONConstructor.class) != null) {
                return constructor;
            }
        }
        return null;
    }

    private boolean hasSet(SGField field) {
        return field.getSetMethod() != null || field.getField() != null;
    }

    private void setValue(Object target, Object object, SGField field) {
        SGMethod method = field.getSetMethod();
        if (method == null) {
            if (field.getField() == null) {
                return;
            } else {
                if (!field.getField().isAccessible()) {
                    field.getField().setAccessible(true);
                }
                try {
                    field.getField().set(target, object);
                } catch (IllegalAccessException e) {
                    LOGGER.error("invoke field :" + field.getField().getName() + " failed");
                    throw new DeserializerException("invoke field :" + field.getField().getName() + " failed");
                }
                return;
            }
        } else {
            try {
                if (!method.getMethod().isAccessible()) {
                    method.getMethod().setAccessible(true);
                }
                method.getMethod().invoke(target, object);
            } catch (IllegalAccessException | InvocationTargetException e) {
                LOGGER.error("call field setMethod :" + method.getFullName() + " failed");
                throw new DeserializerException("call field setMethod :" + method.getFullName() + " failed");
            }
            return;
        }
    }

}
