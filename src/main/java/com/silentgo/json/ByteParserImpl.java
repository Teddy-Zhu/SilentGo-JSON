package com.silentgo.json;

import com.silentgo.json.model.JSONArray;
import com.silentgo.json.model.JSONEntity;
import com.silentgo.json.model.JSONObject;

import java.util.Stack;

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
    public <T> T parse(byte[] bytes, Class<T> tClass) {

        return null;
    }

    @Override
    public JSONEntity parse(byte[] bytes) {
        JSONReader reader = new JSONReader(bytes, -1, bytes.length - 1);

        JSONEntity entity = JSONReaderKit.readJSONValue(reader, JSON.config);

        return entity;
    }

}
