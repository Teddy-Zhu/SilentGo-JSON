package com.silentgo.json.deserializer;

import com.silentgo.json.model.JSONEntity;
import com.silentgo.utils.reflect.SGField;

import java.util.Collection;
import java.util.List;

/**
 * Project : json
 * Package : com.silentgo.json.deserializer
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/11.
 */
public class ArrayDeserializer extends CollectionDeserializer {

    public ArrayDeserializer(Deserializer child) {
        super(child);
    }

    @Override
    public Object getObject(JSONEntity entity, SGField sgField, Object key, Object target) {
        Collection<Object> list = (Collection<Object>) super.getObject(entity, sgField, key, target);
        return list.toArray();
    }
}
