package com.silentgo.json.serializer;

import com.silentgo.json.model.JSONEntity;

/**
 * Project : json
 * Package : com.silentgo.json.serializer
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/20.
 */
public class JSONEntitySerializer implements Serializer {
    @Override
    public String serialize(Object object) {
        if (object instanceof JSONEntity) {
            return ((JSONEntity) object).getString();
        } else {
            return SerializerBuilder.NULL;
        }
    }
}
