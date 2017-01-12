package com.silentgo.json.parser;

import com.silentgo.json.JSON;
import com.silentgo.json.configuration.JSONConfig;
import com.silentgo.json.model.JSONEntity;

/**
 * Project : SilentGo
 * Package : com.silentgo.json
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/4.
 */
public class StringParserImpl implements StringParser {


    @Override
    public <T> T parse(String input, Class<T> tClass, JSONConfig jsonConfig) {
        return (T) JSON.mapper.map(parse(input, jsonConfig), tClass, null);
    }

    @Override
    public JSONEntity parse(String input, JSONConfig jsonConfig) {
        return JSONReaderKit.Entity.readValue(new StringReader(input), jsonConfig, null, 0);
    }


}
