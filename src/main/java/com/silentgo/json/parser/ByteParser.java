package com.silentgo.json.parser;

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
public interface ByteParser extends Parser<byte[]> {

    public <T> T parse(byte[] bytes, Class<T> tClass, JSONConfig jsonConfig);

    public JSONEntity parse(byte[] bytes, JSONConfig jsonConfig);

}
