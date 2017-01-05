package com.silentgo.json;

import com.silentgo.json.model.JSONArray;
import com.silentgo.json.model.JSONEntity;
import com.silentgo.json.model.JSONObject;

/**
 * Project : SilentGo
 * Package : com.silentgo.json
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/4.
 */
public interface StringParser extends ByteParser {
    public <T> T parse(String input, Class<T> tClass);

    public JSONEntity parse(String input);

}
