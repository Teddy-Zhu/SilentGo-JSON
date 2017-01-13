package com.silentgo.json.parser;

import com.silentgo.json.JSON;
import com.silentgo.json.configuration.JSONConfig;
import com.silentgo.json.mapping.JSONEntityMapper;
import com.silentgo.json.mapping.inter.JSONMapper;
import com.silentgo.json.model.JSONEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger logger = LoggerFactory.getLogger(ByteParserImpl.class);

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
