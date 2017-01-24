package com.silentgo.json.serializer;

import com.silentgo.json.JSONGlobalConfig;
import com.silentgo.utils.log.Log;
import com.silentgo.utils.log.LogFactory;

import java.util.Collection;
import java.util.Iterator;

/**
 * Project : json
 * Package : com.silentgo.json.deserializer
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/11.
 */
public class CollectionSerializer implements Serializer {

    private static final Log LOGGER = LogFactory.get();

    private Serializer child;


    public CollectionSerializer(Serializer child) {
        this.child = child;
    }

    public Serializer getChild() {
        return child;
    }

    public void setChild(Serializer child) {
        this.child = child;
    }


    @Override
    public String serialize(Object object) {
        if (object instanceof Collection) {
            Iterator iterator = ((Collection) object).iterator();

            SerializerBuilder stringBuilder = new SerializerBuilder("[");
            int i = 0;
            while (iterator.hasNext()) {
                Object obj = iterator.next();
                if (obj == null && !JSONGlobalConfig.showNullField) {
                    continue;
                }
                stringBuilder.append(child.serialize(obj)).appendInterval();
                i++;
            }
            if (i > 0) {
                stringBuilder.deleteLastChar();
            }
            return stringBuilder.appendArrayEnd().toString();
        } else {
            LOGGER.error("the object is not collection ");
            return SerializerBuilder.NULL;
        }
    }
}
