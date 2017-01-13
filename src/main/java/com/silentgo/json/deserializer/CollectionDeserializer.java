package com.silentgo.json.deserializer;

import com.silentgo.json.mapping.valreader.ReaderKit;
import com.silentgo.json.model.JSONArray;
import com.silentgo.json.model.JSONEntity;
import com.silentgo.utils.ClassKit;
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
public class CollectionDeserializer implements Deserializer {

    private Deserializer child;


    public CollectionDeserializer() {
    }

    public CollectionDeserializer(Deserializer child) {
        this.child = child;
    }

    public Deserializer getChild() {
        return child;
    }

    public void setChild(Deserializer child) {
        this.child = child;
    }

    @Override
    public Object getObject(JSONEntity entity, SGField sgField, Object key, Object target) {
        JSONArray jsonArray = ReaderKit.getTarget(entity, JSONArray.class, "json can not be transformed to array");

        Collection<Object> ret = target == null ? ClassKit.createCollection(Collection.class) : (Collection<Object>) target;
        List<JSONEntity> entities = jsonArray.get();

        for (int i = 0; i < entities.size(); i++) {
            JSONEntity jsonEntity = entities.get(i);
            ret.add(child.getObject(jsonEntity, null, null, null));
        }
        return ret;
    }
}
