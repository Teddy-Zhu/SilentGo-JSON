package com.silentgo.json.parser.jsonvaluereader;

import com.silentgo.json.configuration.JSONConfig;
import com.silentgo.json.model.JSONEntity;
import com.silentgo.json.parser.Reader;

/**
 * Project : json
 * Package : com.silentgo.json.parser
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/7.
 */
public interface JSONValueReader<T extends JSONEntity> {

    public JSONEntity readValue(Reader reader, JSONConfig jsonConfig, T outJsonObject, int depth);

}
