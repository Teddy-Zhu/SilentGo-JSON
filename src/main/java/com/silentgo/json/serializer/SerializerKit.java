package com.silentgo.json.serializer;

import com.silentgo.json.deserializer.CollectionDeserializer;
import com.silentgo.json.deserializer.Deserializer;
import com.silentgo.json.deserializer.JavaBeanDeserializer;
import com.silentgo.json.deserializer.ValueGetterDeserializer;
import com.silentgo.json.exception.DeserializerException;
import com.silentgo.json.exception.SerializerException;
import com.silentgo.json.mapping.getter.ValueGetKit;
import com.silentgo.json.mapping.getter.ValueGetter;
import com.silentgo.json.model.JSONEntity;
import com.silentgo.utils.*;
import com.silentgo.utils.inter.ITypeConvertor;
import com.silentgo.utils.log.Log;
import com.silentgo.utils.log.LogFactory;
import com.silentgo.utils.reflect.SGClass;
import com.silentgo.utils.reflect.SGField;

import java.lang.reflect.Type;
import java.util.Collection;
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
public class SerializerKit {

    private static final Log LOGGER = LogFactory.get();

    public static final Serializer stringSerializer = new StringSerializer();
    private static final Serializer baseTypeSerializer = new BaseTypeSerializer();
    private static final Serializer jsonEntitySerializer = new JSONEntitySerializer();
    public static final Serializer dynamicSerializer = new DynamicSerializer();
    public static final Serializer defaultArraySerilizer = new ArraySerializer(dynamicSerializer);
    public static final Serializer defaultMapSerializer = new MapSerializer(dynamicSerializer);
    public static final Serializer defaultCollectionSerializer = new CollectionSerializer(dynamicSerializer);

    private static final Map<Class<?>, Serializer> serializerMap = new HashMap<>();

    static {
        serializerMap.put(String.class, stringSerializer);
    }


    public static Serializer createSerializer(Class<?> clz) {
        return createSerializer(clz, null);
    }

    public static Serializer createSerializer(Class<?> clz, SGField sgField) {
        Serializer serializer = serializerMap.get(clz);
        if (serializer == null) {
            if (clz.isArray()) {
                Class<?> type = clz.getComponentType();

                return Object.class.equals(type) ? defaultArraySerilizer : new ArraySerializer(createSerializer(type));

            } else if (Collection.class.isAssignableFrom(clz)) {
                Class<?> target = Object.class;
                if (sgField != null) {
                    if (sgField.getField() == null) {
                        if (sgField.getGetMethod() == null) {
                            throw new SerializerException("the field does not have getter method");
                        } else {
                            Type type = sgField.getGetMethod().getMethod().getGenericReturnType();
                            target = ClassKit.getActualType(type);
                        }
                    } else {
                        Type type = sgField.getField().getGenericType();
                        target = ClassKit.getActualType(type);
                    }
                }
                return Object.class.equals(target) ? defaultCollectionSerializer : new CollectionSerializer(createSerializer(target));
            } else if (Map.class.isAssignableFrom(clz)) {
                Class<?> target = Object.class;
                if (sgField != null) {
                    if (sgField.getField() == null) {
                        if (sgField.getGetMethod() == null) {
                            throw new SerializerException("the field does not have getter method");
                        } else {
                            Type type = sgField.getGetMethod().getMethod().getGenericReturnType();
                            target = ClassKit.getActualType(type, 1);
                        }
                    } else {
                        Type type = sgField.getField().getGenericType();
                        target = ClassKit.getActualType(type, 1);
                    }
                }
                return Object.class.equals(target) ? defaultMapSerializer : new MapSerializer(createSerializer(target));

            } else if (JSONEntity.class.isAssignableFrom(clz)) {
                return jsonEntitySerializer;
            } else if (TypeConvertKit.isBaseType(clz)) {
                return baseTypeSerializer;
            } else {
                //java bean or other java type
                ITypeConvertor typeConvertor = ConvertKit.getTypeConvert(clz, String.class, null);

                if (typeConvertor == null) {
                    if (clz.getClassLoader() == null)
                        throw new SerializerException(StringKit.format("do not support the type {}  ,please implement typeConvert", clz.getName()));
                } else {
                    Serializer deserializer = new ConvertSerializer(typeConvertor);
                    serializerMap.put(clz, deserializer);
                    return deserializer;
                }

                SGClass sgClass = ReflectKit.getSGClass(clz);

                JavaBeanSerializer javaBeanSerializer = new JavaBeanSerializer();

                serializerMap.put(clz, javaBeanSerializer);
                javaBeanSerializer.init(sgClass);
                return javaBeanSerializer;
            }
        } else {
            return serializer;
        }
    }

}
