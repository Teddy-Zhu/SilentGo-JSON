package com.silentgo.json.mapping.valreader;

import com.silentgo.json.model.JSONEntity;
import com.silentgo.utils.reflect.SGEntity;

/**
 * Project : json
 * Package : com.silentgo.json.mapping.valreader
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/11.
 */
public interface ValueReader {
    public boolean canRead(Class<?> type, JSONEntity jsonEntity, Object fieldKey, JSONEntity parentJSON, JSONEntity jsonLazy, SGEntity currentField, SGEntity parentField);

    public Object readValue(Class<?> type, JSONEntity jsonEntity, Object fieldKey, JSONEntity parentJSON, JSONEntity jsonLazy, SGEntity currentField, SGEntity parentField);
}
