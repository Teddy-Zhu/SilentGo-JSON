package com.silentgo.json.serializer;

import com.silentgo.utils.inter.ITypeConvertor;

/**
 * Project : json
 * Package : com.silentgo.json.serializer
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/24.
 */
public class DefaultToStringConvertor implements ITypeConvertor<Object, String> {
    @Override
    public String convert(Object o, Object... objects) {
        return String.valueOf(o);
    }
}
