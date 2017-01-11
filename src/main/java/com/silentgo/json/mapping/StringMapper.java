package com.silentgo.json.mapping;

import com.silentgo.json.mapping.inter.JSONMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * Project : json
 * Package : com.silentgo.json.mapping
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/7.
 */
public class StringMapper  implements JSONMapper<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(StringMapper.class);


    @Override
    public <T> Collection<T> mapCollection(String json, Class<T> tClass, String name) {
        return null;
    }

    @Override
    public <T> Collection<T> mapCollection(String json, Class<T> tClass) {
        return null;
    }

    @Override
    public <T> T map(String json, Class<T> tClass, String name) {
        return null;
    }

    @Override
    public <T> T map(String json, T entity, String name) {
        return null;
    }
}
