package com.silentgo.json.parser;

import com.silentgo.json.configuration.JSONConfig;
import com.silentgo.json.mapping.JSONEntityMapper;
import com.silentgo.json.mapping.inter.JSONMapper;
import com.silentgo.json.model.JSONEntity;
import com.silentgo.json.model.JSONLazy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final JSONMapper mapper = new JSONEntityMapper();

    @Override
    public <T> T parse(byte[] bytes, Class<T> tClass, JSONConfig jsonConfig) {
        return (T) mapper.map(parse(bytes, jsonConfig), tClass, null);
    }

    @Override
    public JSONEntity parse(byte[] bytes, JSONConfig jsonConfig) {
        JSONReader reader = new JSONReader(bytes, -1, bytes.length - 1);
        JSONEntity entity = JSONReaderKit.getReader(JSONEntity.class).readValue(reader, jsonConfig, null, 0);
        return entity;
    }

}
