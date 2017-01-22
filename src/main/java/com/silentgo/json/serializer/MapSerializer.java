package com.silentgo.json.serializer;

import java.util.Map;

/**
 * Project : json
 * Package : com.silentgo.json.serializer
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/20.
 */
public class MapSerializer implements Serializer {
    public Serializer child;

    public MapSerializer(Serializer child) {
        this.child = child;
    }

    @Override
    public String serialize(Object object) {
        if (object instanceof Map) {
            SerializerBuilder stringBuilder = new SerializerBuilder().appendObjectStart();
            Map<Object, Object> objectMap = (Map<Object, Object>) object;
            for (Map.Entry<Object, Object> entry : objectMap.entrySet()) {
                stringBuilder.append(SerializerKit.stringSerializer.serialize(entry.getKey())).appendObjectInterval()
                        .append(child.serialize(entry.getValue()))
                        .appendInterval();
            }
            return stringBuilder.deleteLastChar().toString();
        } else {
            return SerializerBuilder.NULL;
        }
    }
}
