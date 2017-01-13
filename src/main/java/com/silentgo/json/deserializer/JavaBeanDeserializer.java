package com.silentgo.json.deserializer;

import com.silentgo.json.annotation.JSONConstructor;
import com.silentgo.json.annotation.JSONField;
import com.silentgo.json.exception.DeserializerException;
import com.silentgo.json.mapping.valreader.ReaderKit;
import com.silentgo.json.model.JSONEntity;
import com.silentgo.json.model.JSONObject;
import com.silentgo.utils.common.Const;
import com.silentgo.utils.reflect.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Project : json
 * Package : com.silentgo.json.deserializer
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/11.
 */
public class JavaBeanDeserializer implements Deserializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(JavaBeanDeserializer.class);

    private SGClass sgClass;

    private SGConstructor sgConstructor;

    private String[] constructorParameterFieldNames;

    private Map<String, SGEntity> setterMap;

    private Map<String, Deserializer> fieldDeserializer;

    public JavaBeanDeserializer(SGClass sgClass) {
        this.sgClass = sgClass;
        this.fieldDeserializer = new HashMap<>();
        this.setterMap = new HashMap<>();
        if (sgClass.getDefaultConstructor() != null)
            this.sgConstructor = sgClass.getDefaultConstructor();
        for (SGConstructor constructor : sgClass.getConstructors()) {
            if (constructor.getAnnotationMap().get(JSONConstructor.class) != null) {
                this.sgConstructor = constructor;
                break;
            }
        }
        if (sgConstructor == null) {
            this.constructorParameterFieldNames = new String[0];
        } else {
            this.constructorParameterFieldNames = new String[sgConstructor.getParameterNames().length];
            String[] parameters = sgConstructor.getParameterNames();
            for (int i = 0; i < parameters.length; i++) {
                String parameterName = parameters[i];
                SGParameter sgParameter = sgConstructor.getParameterMap().get(parameterName);
                JSONField jsonField = (JSONField) sgParameter.getAnnotationMap().get(JSONField.class);
                if (Const.EmptyString.equals(jsonField.value())) {
                    this.constructorParameterFieldNames[i] = parameterName;
                } else {
                    this.constructorParameterFieldNames[i] = jsonField.value();
                }
            }
        }

    }


    public void addDeserializer(String name, Deserializer deserializer) {
        SGField sgField = sgClass.getFieldMap().get(name);
        if (sgField.getSetMethod() != null) {
            setterMap.put(name, sgField.getSetMethod());
        } else {
            setterMap.put(name, sgField);
        }
        fieldDeserializer.put(name, deserializer);
    }

    public Map<String, Deserializer> getFieldDeserializer() {
        return fieldDeserializer;
    }

    public void setFieldDeserializer(Map<String, Deserializer> fieldDeserializer) {
        this.fieldDeserializer = fieldDeserializer;
    }

    @Override
    public Object getObject(JSONEntity entity, SGField sgField, Object key, Object target) {
        JSONObject jsonObject = ReaderKit.getTarget(entity, JSONObject.class, "json can not be transformed to object");

        if (target == null && sgConstructor.isDefault()) {
            if (!sgConstructor.getConstructor().isAccessible())
                sgConstructor.getConstructor().setAccessible(true);
            try {
                target = sgConstructor.getConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        Map<String, Object> fieldMap = new HashMap<>();

        Object finalTarget = target;
        fieldDeserializer.forEach((s, deserializer) -> {
            JSONEntity entity1 = jsonObject.get(s);
            if (entity1 == null) return;
            Object object = deserializer.getObject(jsonObject.get(s), sgClass.getField(s), s, null);
            if (finalTarget == null) {
                fieldMap.put(s, object);
            } else {
                SGEntity sgEntity = setterMap.get(s);
                if (sgEntity == null) return;
                if (sgEntity instanceof SGField) {
                    ((SGField) sgEntity).set(finalTarget, object);
                    return;
                }
                if (sgEntity instanceof SGMethod) {
                    ((SGMethod) sgEntity).invoke(finalTarget, object);
                    return;
                }
            }
        });

        if (target == null && !sgConstructor.isDefault()) {
            Object[] obj = new Object[sgConstructor.getParameterNames().length];
            for (int i = 0; i < this.constructorParameterFieldNames.length; i++) {
                obj[i] = fieldMap.get(constructorParameterFieldNames[i]);
            }
            try {
                if (!sgConstructor.getConstructor().isAccessible()) {
                    sgConstructor.getConstructor().setAccessible(true);
                }
                target = sgConstructor.getConstructor().newInstance(obj);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                LOGGER.error("type :" + sgClass.getClz().getName() + " instance failed by instance : " + sgConstructor.getConstructor(), e);
                throw new DeserializerException("type :" + sgClass.getClz().getName() + " instance failed by instance : " + sgConstructor.getConstructor(), e);
            }
        }
        return target;
    }


}
