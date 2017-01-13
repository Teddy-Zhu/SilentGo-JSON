package com.silentgo.json.parser;

import com.silentgo.json.configuration.JSONConfig;
import com.silentgo.json.model.JSONEntity;

import java.util.Collection;

/**
 * Project : json
 * Package : com.silentgo.json.parser
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/13.
 */
public interface Parser<C> {

    public <T> T parse(C input, Class<T> tClass, JSONConfig jsonConfig);

    public JSONEntity parse(C input, JSONConfig jsonConfig);

    public <T> Collection<T> parseCollection(C input, Class<T> tClass, JSONConfig jsonConfig);

}
