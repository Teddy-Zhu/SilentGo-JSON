package com.silentgo.json.serializer;

import com.silentgo.json.JSONGlobalConfig;
import com.silentgo.utils.log.Log;
import com.silentgo.utils.log.LogFactory;

import java.lang.reflect.Array;

/**
 * Project : json
 * Package : com.silentgo.json.serializer
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/19.
 */
public class ArraySerializer implements Serializer {

    private static final Log LOGGER = LogFactory.get();

    private Serializer child;

    public ArraySerializer(Serializer child) {
        this.child = child;
    }

    @Override
    public String serialize(Object object) {
        if (object != null && object.getClass().isArray()) {
            SerializerBuilder stringBuilder = new SerializerBuilder("[");
            int length = Array.getLength(object);
            for (int i = 0, len = length - 1; i < len; i++) {
                Object obj = Array.get(object, i);
                if (obj == null && !JSONGlobalConfig.showNullField) {
                    continue;
                }
                stringBuilder.append(obj).appendInterval();
            }
            if (length > 0) {
                Object obj = Array.get(object, length - 1);
                if (obj == null && !JSONGlobalConfig.showNullField) {

                } else {
                    stringBuilder.append(child.serialize(obj));
                }
            }
            return stringBuilder.appendArrayEnd().toString();
        } else {
            LOGGER.error("the object is not array ");
            return SerializerBuilder.NULL;
        }
    }
}
