package com.silentgo.json.deserializer;

import com.silentgo.json.model.JSONEntity;
import com.silentgo.utils.reflect.SGField;

/**
 * Project : json
 * Package : com.silentgo.json.deserializer
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/11.
 */
public interface Deserializer {

    public Object getObject(JSONEntity entity, SGField sgField, Object key, Object target);
}
