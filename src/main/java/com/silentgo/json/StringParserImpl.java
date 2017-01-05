package com.silentgo.json;

import com.silentgo.json.model.JSONEntity;

/**
 * Project : SilentGo
 * Package : com.silentgo.json
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/4.
 */
public class StringParserImpl extends ByteParserImpl implements StringParser {
    @Override
    public <T> T parse(String input, Class<T> tClass) {
        return parse(input.getBytes(), tClass);
    }

    @Override
    public JSONEntity parse(String input) {
        return parse(input.getBytes());
    }


}
