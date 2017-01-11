package com.silentgo.json.mapping.valgetter;

import com.silentgo.json.model.JSONEntity;
import com.silentgo.json.model.JSONLazy;
import com.silentgo.utils.reflect.SGEntity;
import com.silentgo.utils.reflect.SGField;

/**
 * Project : json
 * Package : com.silentgo.json.mapping.valgetter
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/9.
 */
public interface ValueGetter<T> {
    public T get(Object key, String value, JSONEntity current, JSONEntity parentJSON, JSONEntity entireJSON, SGEntity field);
}
