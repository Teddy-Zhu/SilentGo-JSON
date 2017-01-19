package com.silentgo.json.deserializer;

import com.silentgo.json.model.JSONEntity;
import com.silentgo.utils.log.Log;
import com.silentgo.utils.log.LogFactory;
import com.silentgo.utils.reflect.SGField;
import com.silentgo.utils.reflect.SGMethod;

import java.util.List;
import java.util.Map;

/**
 * Project : json
 * Package : com.silentgo.json.deserializer
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/19.
 */
public class JSONSetDeserializer implements Deserializer {


    private static final Log LOGGER = LogFactory.get();

    private String[] parameterNames;

    private SGMethod method;


    public JSONSetDeserializer(String[] parameterNames, SGMethod method) {
        this.parameterNames = parameterNames;
        this.method = method;
    }

    @Override
    public Object getObject(JSONEntity entity, SGField sgField, Object key, Object target) {
        if (target == null) {
            LOGGER.warn("target is null");
            return null;
        }
        if (!(key instanceof Map)) {
            LOGGER.warn("get object with JsonSetter annotation error");
            return null;
        }

        Map<String, Object> objectMap = (Map<String, Object>) key;

        Object[] objects = new Object[parameterNames.length];

        for (int i = 0; i < parameterNames.length; i++) {
            objects[i] = objectMap.get(parameterNames[i]);
        }

        return method.invoke(target, objects);
    }
}
