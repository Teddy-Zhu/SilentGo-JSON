package com.silentgo.json.deserializer;

import com.silentgo.json.exception.DeserializerException;
import com.silentgo.json.mapping.getter.ValueGetKit;
import com.silentgo.json.mapping.getter.ValueGetter;
import com.silentgo.json.model.JSONEntity;
import com.silentgo.utils.ClassKit;
import com.silentgo.utils.ReflectKit;
import com.silentgo.utils.reflect.SGClass;
import com.silentgo.utils.reflect.SGField;

import java.lang.reflect.Type;
import java.util.Collection;
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
public class DeserializerKit {

    private static final Map<Class<?>, Deserializer> typeDeserializer = new HashMap<>();

    public static final Deserializer defaultDeserializer = new DefaultDeserializer();

    private static final Deserializer defaultArrayDeserializer = new ArrayDeserializer(DeserializerKit.defaultDeserializer);

    private static final Deserializer defaultCollectionDeserializer = new CollectionDeserializer(DeserializerKit.defaultDeserializer);

    private static final Deserializer defaultMapDeserializer = new MapDeserializer(DeserializerKit.defaultDeserializer);

    static {
        ValueGetKit.getValueGetterMap().forEach(((aClass, valueGetter) -> typeDeserializer.put(aClass, new ValueGetterDeserializer(valueGetter))));
    }

    public static Deserializer createDeserializer(Class<?> tClass) {
        return createDeserializer(tClass, null);
    }

    public static Deserializer createDeserializer(Class<?> tClass, SGField sgField) {

        if (typeDeserializer.containsKey(tClass)) return typeDeserializer.get(tClass);

        if (tClass.isArray()) {
            Class<?> type = tClass.getComponentType();
            return Object.class.equals(type) ? defaultArrayDeserializer : createDeserializer(type, null);
        } else if (Map.class.isAssignableFrom(tClass)) {
            Class<?> target = Object.class;
            if (sgField != null) {
                Type type = sgField.getField().getGenericType();
                target = ClassKit.getActualType(type, 1);
            }
            return Object.class.equals(target) ? defaultMapDeserializer : new MapDeserializer(createDeserializer(target, null));
        } else if (Collection.class.isAssignableFrom(tClass)) {
            Class<?> target = Object.class;
            if (sgField != null) {
                Type type = sgField.getField().getGenericType();
                target = ClassKit.getActualType(type);
            }
            return Object.class.equals(target) ? defaultCollectionDeserializer : new CollectionDeserializer(createDeserializer(target, null));
        } else if (Object.class.equals(tClass) || JSONEntity.class.isAssignableFrom(tClass)) {
            return DeserializerKit.defaultDeserializer;
        } else {

            ValueGetter valueGetter = ValueGetKit.getValueGetter(tClass);

            if (valueGetter == null) {
                if (tClass.getClassLoader() == null)
                    throw new DeserializerException("do not support the type " + tClass.getName() + " ,please implement ValueGetter");
            } else {
                Deserializer deserializer = sgField == null ? typeDeserializer.get(tClass) : new ValueGetterDeserializer(valueGetter);
                typeDeserializer.put(tClass, deserializer);
                return deserializer;
            }

            //only java bean should be created
            SGClass sgClass = ReflectKit.getSGClass(tClass);
            JavaBeanDeserializer deserializer1 = new JavaBeanDeserializer(sgClass);

            sgClass.getFieldMap().forEach((k, v) -> {
                Deserializer deserializer2 = v.getType().equals(tClass) ? deserializer1 : createDeserializer(v.getType(), v);
                deserializer1.addDeserializer(k, deserializer2);
            });
            typeDeserializer.put(tClass, deserializer1);
            return deserializer1;

        }
    }

}
