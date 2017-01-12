package com.silentgo.json.mapping.getter;

import com.silentgo.json.model.JSONEntity;
import com.silentgo.utils.reflect.SGField;

/**
 * Project : json
 * Package : com.silentgo.json.mapping.getter
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/11.
 */
public interface ValueGetter<T> {

    public T getObject(JSONEntity jsonEntity, SGField sgField);
}
