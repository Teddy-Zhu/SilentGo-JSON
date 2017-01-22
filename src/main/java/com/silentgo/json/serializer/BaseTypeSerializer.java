package com.silentgo.json.serializer;

import com.silentgo.utils.TypeConvertKit;

/**
 * Project : json
 * Package : com.silentgo.json.serializer
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/20.
 */
public class BaseTypeSerializer implements Serializer {
    @Override
    public String serialize(Object object) {
        //except string
        return String.valueOf(object);
    }
}
