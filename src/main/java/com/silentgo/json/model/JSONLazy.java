package com.silentgo.json.model;

import com.silentgo.json.JSON;
import com.silentgo.json.configuration.JSONConfig;
import com.silentgo.json.parser.JSONReader;
import com.silentgo.json.parser.JSONReaderKit;
import com.silentgo.utils.StringKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Project : SilentGo
 * Package : com.silentgo.json.model
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/5.
 */
public class JSONLazy extends JSONEntity {

    private static final Logger LOGGER = LoggerFactory.getLogger(JSONLazy.class);

    private JSONEntity value;
    private Class<? extends JSONEntity> type;
    private JSONReader reader;

    public JSONLazy(JSONReader reader, Class<? extends JSONEntity> type) {
        super(null);
        this.reader = reader;
        this.type = type;
    }

    @Override
    public JSONEntity get() {
        return get(JSON.oneDepthConfig);
    }

    @Override
    public JSONEntity get(JSONConfig jsonConfig) {
        if (value == null) {
            value = JSONReaderKit.getReader(type).readValue(reader, jsonConfig, null, 0);
        }
        return value;
    }

    @Override
    public String getString() {
        if (super.getString() == null) {
            setString(new String(reader.data, reader.pos, reader.end - reader.pos));
        }
        return super.getString();
    }

    public Class<? extends JSONEntity> getType() {
        return type;
    }
}
