package com.silentgo.json.serializer;

import com.silentgo.json.JSONGlobalConfig;

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
            int i = 0;
            for (Map.Entry<Object, Object> entry : objectMap.entrySet()) {
                if (entry.getValue() == null && !JSONGlobalConfig.showNullField) {
                    continue;
                }
                stringBuilder.append(SerializerKit.stringSerializer.serialize(entry.getKey())).appendObjectInterval()
                        .append(child.serialize(entry.getValue()))
                        .appendInterval();
                i++;
            }
            if (i > 0) {
                stringBuilder.deleteLastChar();
            }
            return stringBuilder.appendObjectEnd().toString();
        } else {
            return SerializerBuilder.NULL;
        }
    }
}
