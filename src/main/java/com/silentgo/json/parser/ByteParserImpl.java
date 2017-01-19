package com.silentgo.json.parser;

import com.silentgo.json.JSON;
import com.silentgo.json.configuration.JSONConfig;
import com.silentgo.json.model.JSONEntity;

import java.util.Collection;

/**
 * Project : SilentGo
 * Package : com.silentgo.json
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/4.
 */
public class ByteParserImpl implements ByteParser {

    @Override
    public <T> T parse(byte[] bytes, Class<T> tClass, JSONConfig jsonConfig) {
        return (T) JSON.mapper.map(parse(bytes, jsonConfig), tClass, null);
    }

    @Override
    public JSONEntity parse(byte[] bytes, JSONConfig jsonConfig) {
        return JSONReaderKit.Entity.readValue(new ByteReader(bytes, -1, bytes.length - 1), jsonConfig, null, 0);
    }

    @Override
    public <T> Collection<T> parseCollection(byte[] input, Class<T> tClass, JSONConfig jsonConfig) {
        return JSON.mapper.mapCollection(parse(input, jsonConfig), tClass, null);
    }

}
