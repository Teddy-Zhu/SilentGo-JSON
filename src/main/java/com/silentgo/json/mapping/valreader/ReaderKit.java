package com.silentgo.json.mapping.valreader;

import com.silentgo.json.JSON;
import com.silentgo.json.exception.DeserializerException;
import com.silentgo.json.model.JSONEntity;
import com.silentgo.json.model.JSONLazy;

/**
 * Project : json
 * Package : com.silentgo.json.mapping.valreader
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/11.
 */
public class ReaderKit {

    public static <T extends JSONEntity> T getTarget(JSONEntity jsonEntity, Class<T> target, String msg) {
        T t;
        if (jsonEntity instanceof JSONLazy && target.equals(((JSONLazy) jsonEntity).getType())) {
            t = (T) jsonEntity.get(JSON.oneDepthConfig);
        } else if (jsonEntity.getClass().equals(target)) {
            t = (T) jsonEntity;
        } else {
            throw new DeserializerException(msg);
        }
        return t;
    }

}
