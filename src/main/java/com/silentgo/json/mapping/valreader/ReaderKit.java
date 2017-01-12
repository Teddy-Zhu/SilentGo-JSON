package com.silentgo.json.mapping.valreader;

import com.silentgo.json.JSON;
import com.silentgo.json.exception.DeserializerException;
import com.silentgo.json.model.JSONEntity;
import com.silentgo.json.model.JSONLazy;
import com.silentgo.utils.reflect.SGEntity;
import com.silentgo.utils.reflect.SGField;
import com.silentgo.utils.reflect.SGMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Project : json
 * Package : com.silentgo.json.mapping.valreader
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/11.
 */
public class ReaderKit {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReaderKit.class);


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
