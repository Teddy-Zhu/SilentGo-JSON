package com.silentgo.json.serializer;

import com.silentgo.json.JSONGlobalConfig;
import com.silentgo.json.annotation.JSONIgnore;
import com.silentgo.json.deserializer.Deserializer;
import com.silentgo.json.deserializer.JavaBeanDeserializer;
import com.silentgo.utils.reflect.SGClass;
import com.silentgo.utils.reflect.SGField;
import com.silentgo.utils.reflect.SGMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * Project : json
 * Package : com.silentgo.json.serializer
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/20.
 */
public class JavaBeanSerializer implements Serializer {

    private Map<SGField, Serializer> fieldSerializer;


    public JavaBeanSerializer() {
        fieldSerializer = new HashMap<>();
    }

    public void init(SGClass sgClass) {
        sgClass.getFieldMap().forEach((name, sgField) -> {
            if (sgField.getType().equals(sgClass.getClz())) {
                fieldSerializer.put(sgField, this);
                return;
            }
            JSONIgnore jsonIgnore = (JSONIgnore) sgField.getAnnotation(JSONIgnore.class);
            if (jsonIgnore != null && !jsonIgnore.deserialize()) {
                return;
            }
            fieldSerializer.put(sgField, SerializerKit.createSerializer(sgField.getType(), sgField));
        });
    }

    public JavaBeanSerializer(SGClass sgClass) {
        fieldSerializer = new HashMap<>();
        init(sgClass);
    }

    @Override
    public String serialize(Object object) {
        if (object == null) return SerializerBuilder.NULL;
        SerializerBuilder serializerBuilder = new SerializerBuilder();
        serializerBuilder.appendObjectStart();
        int i = 0;
        for (Map.Entry<SGField, Serializer> stringSerializerEntry : fieldSerializer.entrySet()) {
            SGField field = stringSerializerEntry.getKey();
            String name = field.getName();

            Object obj = null;
            if (field.getGetMethod() != null) {
                obj = field.invokeGetMethod(object);
            } else if (field.getField() != null) {
                obj = field.get(object);
            }

            if (obj == null && !JSONGlobalConfig.showNullField) {
                continue;
            }
            serializerBuilder.append(SerializerKit.stringSerializer.serialize(name))
                    .appendObjectInterval().append(stringSerializerEntry.getValue().serialize(obj)).
                    appendInterval();
            i++;
        }
        if (i > 0) {
            serializerBuilder.deleteLastChar();
        }
        return serializerBuilder.appendObjectEnd().toString();
    }
}
