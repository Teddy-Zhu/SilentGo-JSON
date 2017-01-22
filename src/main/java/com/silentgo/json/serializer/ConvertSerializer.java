package com.silentgo.json.serializer;

import com.silentgo.utils.inter.ITypeConvertor;

/**
 * Project : json
 * Package : com.silentgo.json.serializer
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/20.
 */
public class ConvertSerializer implements Serializer {

    private ITypeConvertor convertor;

    public ConvertSerializer(ITypeConvertor convertor) {
        this.convertor = convertor;
    }

    @Override
    public String serialize(Object object) {
        return convertor.convert(object).toString();
    }
}
